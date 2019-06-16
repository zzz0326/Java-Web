/**
 * Copyright &copy; 2017-2019 <a href="https://gitee.com/lruijun/JSite">JSite</a> All rights reserved.
 */
package com.jsite.modules.storage.web;

import com.google.common.collect.Maps;
import com.jsite.common.config.Global;
import com.jsite.common.lang.StringUtils;
import com.jsite.common.persistence.Page;
import com.jsite.common.utils.UploadUtils4;
import com.jsite.common.web.BaseController;
import com.jsite.modules.localstorage.dao.StorageDao;
import com.jsite.modules.localstorage.entity.Storage;
import com.jsite.modules.oa_forms.entity.FormsList;
import com.jsite.modules.storage.dao.StorageGetDao;
import com.jsite.modules.sys.entity.User;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import com.jsite.modules.oa_forms.entity.OaFormsList;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.jsite.modules.oa_forms.service.OaFormsListService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jsite.modules.storage.entity.StorageGet;
import com.jsite.modules.storage.service.StorageGetService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;


import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * 仓储流程Controller
 * @author liuruijun
 * @version 2019-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/storage/storageGet")
public class StorageGetController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	StorageGetDao storageGetDao;

	@Resource
	StorageDao storageDao;

	@Autowired
	protected StorageGetService storageGetService;

	@Autowired
	protected OaFormsListService oaFormsListService;

	@ModelAttribute
	public StorageGet get(@RequestParam(required=false) String id){
		StorageGet entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = storageGetService.get(id);
		}
		if (entity == null){
			entity = new StorageGet();
		}
		return entity;
	}

	@RequiresPermissions("storage:storageGet:view")
	@RequestMapping(value = {"form"})
	public String form(StorageGet storageGet, Model model) {
		String view = "storageGetForm";
		if (StringUtils.isNotBlank(storageGet.getId())){
			String taskDefKey = storageGet.getAct().getTaskDefKey();

			// 最后一部 让下载的界面
			//需要的是流程图中的主键id
			if ("workshop".equals(taskDefKey) || "Finance_Department".equals(taskDefKey) ||"Warehouse_keeper".equals(taskDefKey) ) {
				view = "storageGetView";
			}

            // 调整申请
			else if ("modifyApply".equals(taskDefKey)) {
				view = "storageGetForm";
			}

			// 确认报表环节
			else {
				view = "storageGetAudit";
			}
		}

		model.addAttribute("storageGet", storageGet);
		return "modules/storage/" + view;
	}

	@RequiresPermissions("storage:storageGet:edit")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(StorageGet storageGet,HttpServletRequest request, HttpServletResponse response) {

		try {
			Map<String, Object> variables = Maps.newHashMap();

			String message = storageGetService.save(storageGet, variables);

			return renderResult(Global.TRUE, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return renderResult(Global.FALSE, "系统内部错误！");
	}

	@RequiresPermissions("storage:storageGet:edit")
	@RequestMapping(value = "creatExcle")//在html中的action中进行调用
	@ResponseBody
	public String creatExcle(StorageGet storageGet, Model model) {

		File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
		String fileName = desktopDir.getAbsolutePath()+"\\原始表单.xls";
		try {
			WritableWorkbook wwb = null;



			// 创建可写入的Excel工作簿

			File file=new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			//以fileName为文件名来创建一个Workbook
			wwb = Workbook.createWorkbook(file);

			// 创建工作表
			WritableSheet ws = wwb.createSheet("Test Shee 1", 0);

			//查询数据库中所有的数据
			List<Storage> list=new ArrayList<Storage>();
			list = storageGetDao.listTable();
			//要插入到的Excel表格的行号，默认从0开始
			Label labelId= new Label(0, 0, "仓库号");//表示第
			Label labelName= new Label(1, 0, "货物号");
			Label labelSex= new Label(2, 0, "剩余");
			Label labelNum= new Label(3, 0, "需要");

			ws.addCell(labelId);
			ws.addCell(labelName);
			ws.addCell(labelSex);
			ws.addCell(labelNum);
			for (int i = 0; i < list.size(); i++) {

				Label labelId_i= new Label(0, i+1, list.get(i).getWarehouseId()+"");
				Label labelName_i= new Label(1, i+1, list.get(i).getCargoId());
				Label labelSex_i= new Label(2, i+1, list.get(i).getRemaining());
				Label labelNum_i= new Label(3, i+1, list.get(i).getNeed()+"");
				ws.addCell(labelId_i);
				ws.addCell(labelName_i);
				ws.addCell(labelSex_i);
				ws.addCell(labelNum_i);
			}

			//写进文档
			wwb.write();
			// 关闭Excel工作簿对象
			System.out.println("数据导出成功!");
			wwb.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {//新建文件后打开excel
			Runtime.getRuntime().exec("cmd /c start "+fileName);
		} catch (IOException e) {}
        return "modules/storage/storageGetForm";
	}

	@RequiresPermissions("storage:storageGet:edit")
	@RequestMapping(value = "saveAudit")//在html中的action中进行调用
	@ResponseBody
	public String saveAudit(StorageGet storageGet, Model model) {
		File desktopDir = FileSystemView.getFileSystemView() .getHomeDirectory();
		String fileName = desktopDir.getAbsolutePath()+"\\原始表单.xls";

		List<Storage> list = new ArrayList<Storage>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(fileName));
			Sheet rs = rwb.getSheet("Test Shee 1"); //或者rwb.getSheet(0)
			int clos=rs.getColumns();//得到所有的列
			int rows=rs.getRows();//得到所有的行

			for(int i=1;i<rows;i++){
				int j=0;
				//第一个是列数，第二个是行数
				String warehouse_id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
				String cargo_id=rs.getCell(j++, i).getContents();
				String remaining=rs.getCell(j++, i).getContents();
				String need=rs.getCell(j++, i).getContents();

				storageDao.update(new Storage(String.valueOf(i),warehouse_id,cargo_id,Integer.parseInt(remaining),Integer.parseInt(need)));
				//调用StorageDao层中的update函数

			}
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*for (Storage stuEntity : list) {
				String[] str=new String[]{stuEntity.getWarehouseId(),stuEntity.getRemaining(),stuEntity.getNeed()+"",stuEntity.getCargoId()+""};
				storageDao.set(str);//上传到excel中

		}*/

		String message = storageGetService.auditSave(storageGet);
		return renderResult(Global.TRUE, message);
	}

	@RequiresPermissions("storage:storageGet:edit")
	@RequestMapping(value = "uploadFile")
	 public String uoloadFile(HttpServletRequest request, HttpServletResponse response,Model model)
	{
		return "modules/storage/storageGet";
	}

	@RequiresPermissions("storage:storageGet:edit")
	@RequestMapping(value = "up", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String up(StorageGet storageGet, User user, HttpServletRequest request)
	{
		List<UploadUtils4.UploadResult> result = UploadUtils4.getInstance().uploadFile(request);

		for (UploadUtils4.UploadResult uploadResult : result) {
			logger.info("上传结果----uploadResult----" + uploadResult.toString());

			if (!uploadResult.err) {
				OaFormsList entity = new OaFormsList();
				if (StringUtils.isBlank(uploadResult.chunk) || uploadResult.merged) {// 写入数据库

					entity.setFilename(uploadResult.fileName);
					entity.setFilesize(uploadResult.fields.get("size"));
					entity.setPath(uploadResult.fileRltvPath);
					entity.setUploadtime(new Date());
					String taskDefKey = storageGet.getAct().getTaskDefKey();
					if ("upload_data_form".equals(taskDefKey)) entity.setFormType("DATAF");
					else entity.setFormType("NORML");
					entity.setUploader("1");
					oaFormsListService.save(entity);

					return renderResult(Global.TRUE, "上传成功");
				}

			}
		}

		return renderResult(Global.FALSE, "上传失败");
	}
	@RequiresPermissions("storage:storageGet:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<StorageGet> listData(StorageGet storageGet, javax.servlet.http.HttpServletRequest request, HttpServletResponse response) {
		Page<StorageGet> page = storageGetService.findPage(new Page<>(request, response), storageGet);
		return page;
	}

}