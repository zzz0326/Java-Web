/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jsite.modules.sys.web;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jsite.common.config.Global;
import com.jsite.common.lang.DateUtils;
import com.jsite.common.lang.StringUtils;
import com.jsite.common.lang.TimeUtils;
import com.jsite.common.security.shiro.session.SessionDAO;
import com.jsite.common.web.BaseController;
import com.jsite.modules.sys.entity.Principal;
import com.jsite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 在线用户Controller
 * @author ThinkGem
 * @version 2016-8-31
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/online")
public class OnlineController extends BaseController {

	@Autowired
	private SessionDAO sessionDAO;

	/**
	 * 在线用户数
	 * @param request
	 * @param response
	 ** @author liuruijun
	 */
	@RequestMapping(value = "count")
	@ResponseBody
	public Integer count(HttpServletRequest request, HttpServletResponse response) {
		return sessionDAO.getActiveSessions(true).size();
	}

	/**
	 * 在线用户列表
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequiresPermissions("sys:online:view")
	@RequestMapping(value = "list")
	public String list(Model model) {
		return "modules/sys/onlineList";
	}
	
	/**
	 * 在线用户列表数据
	 * @param request
	 * @param response
	 ** @author liuruijun
	 */
	@RequiresPermissions("sys:online:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<Map<String, Object>> listData(String isAllOnline, String sessionId,
			String userCode, String userName, String userType, String orderBy) {
		List<Map<String, Object>> list = Lists.newArrayList();
 		Collection<Session> sessions = sessionDAO.getActiveSessions(true);
		long currentTime = System.currentTimeMillis();
		for (Session session : sessions){
			if (StringUtils.isNotBlank(userName) && ((String)session.getAttribute("userName")).contains(userName)){
				continue;
			}
			if (StringUtils.isNotBlank(userType) && ((String)session.getAttribute("userType")).equals(userType)){
				continue;
			}
			Map<String, Object> map = Maps.newLinkedHashMap();
			// 为了安全性，需要有权限的人才能看
			if (UserUtils.getSubject().isPermitted("sys:online:edit")){
				map.put("id", session.getId().toString()); 
			}
			map.put("startTimestamp", DateUtils.formatDateTime(session.getStartTimestamp()));
			map.put("lastAccessTime", DateUtils.formatDateTime(session.getLastAccessTime()));
			map.put("timeout", TimeUtils.formatDateAgo(session.getTimeout()-(currentTime-session.getLastAccessTime().getTime())));
			PrincipalCollection pc = (PrincipalCollection)session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			Principal principal = (pc != null ? (Principal)pc.getPrimaryPrincipal() : null);
			if (principal != null){
				map.put("userCode", session.getAttribute("loginName"));// principal.getId());
				map.put("userName", session.getAttribute("name"));// principal.getName());
				map.put("userType", session.getAttribute("userType"));// ObjectUtils.toString(principal.getParam("userType")));
//				map.put("deviceType", ObjectUtils.toString(principal.getParam("deviceType")));
			}
			map.put("host", session.getHost());
			list.add(map);
		}
		// 本地排序
		if (StringUtils.isNotBlank(orderBy)){
			final String[] ss = orderBy.trim().split(" ");
			if (ss != null && ss.length == 2){
				Collections.sort(list, new Comparator<Map<String, Object>>() {
					@Override
					public int compare(Map<String, Object> o1, Map<String, Object> o2) {
						String s1 = (String)o1.get(ss[0]);
						String s2 = (String)o2.get(ss[0]);
						if ("asc".equals(ss[1])){
							return s1.compareTo(s2);
						}else{
							return s2.compareTo(s1);
						}
					}});
			}
		}
		return list;
	}
	
	/**
	 * 提出在线用户
	 ** @author liuruijun
	 */
	@RequiresPermissions("sys:online:edit")
	@RequestMapping(value = "tickOut")
	@ResponseBody
	public String tickOut(String sessionId) {
		Session session = sessionDAO.readSession(sessionId);
		if (session != null){
			sessionDAO.delete(session);
			return renderResult(Global.TRUE, "踢出已成功！");
		}
		return renderResult(Global.FALSE, "踢出失败，没有找到该在线用户！");
	}
	
}
