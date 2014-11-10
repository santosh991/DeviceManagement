package com.smart.school.devicemanagement.auth;

import javax.servlet.http.HttpServletRequest;

import com.smart.school.devicemanagement.models.User;

public class AuthHelper {
	
	public static void setSessionAccountAuth(HttpServletRequest request, AccountAuth accountAuth){
		request.getSession().setAttribute("accountAuth", accountAuth);
	}
	
	public static AccountAuth getSessionAccountAuth(HttpServletRequest request){
		return (AccountAuth)request.getSession().getAttribute("accountAuth");
	}
	
	public static void setRequestPermissionMenu(HttpServletRequest request, PermissionMenu permissionMenu){
		request.setAttribute("permissionMenu", permissionMenu);
	}
	
	public static PermissionMenu getRequestPermissionMenu(HttpServletRequest request){
		return (PermissionMenu)request.getAttribute("permissionMenu");
	}
	
	/**
	 *  获取当前登录用户ID
	 * @param request
	 * @return
	 */
	public static User getCurrUser(HttpServletRequest request){
		AccountAuth accountAuth=AuthHelper.getSessionAccountAuth(request);
		return new User(accountAuth.getPk());
	}
	
}
