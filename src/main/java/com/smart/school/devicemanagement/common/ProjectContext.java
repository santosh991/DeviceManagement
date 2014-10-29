package com.smart.school.devicemanagement.common;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * 在web。xml 初始化spring容器的上下文对象
 * @version 1.0.0
 *
 */
public class ProjectContext extends HttpServlet{

	private static Logger log = LoggerFactory.getLogger(ProjectContext.class);
	
	private static ApplicationContext applicationContext;
	private static Table<String, String, List<Method>> methodCacheTable = HashBasedTable.create();
	
	public void init(){
		ServletContext servletContext = getServletContext();
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}
	
	public static void setAc(ApplicationContext ac){
		applicationContext = ac;
	}

	public static ApplicationContext getApplicationContext(){
		if (applicationContext == null) {
			log.error("applicationContext 为空!!!请检查是否在web.xml中配置了对应工程的ProjectInitServlet子类的初始化");
		}
		return applicationContext;
	}
	
	
	/**
	 * 在当前容器中获取制定的bean
	 * @param requiredType
	 * @return
	 */
	public static <T> T getBean(Class<T> requiredType){
		return (T)getApplicationContext().getBean(requiredType);
	}
	
	/**
	 * 获取当前Spring容器中的Bean
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName){
		Object bean = null;
		if (getApplicationContext().containsBean(beanName)) {
			bean = getApplicationContext().getBean(beanName);
		}else {
			log.warn("请求的Bean不存在+"+beanName);
		}
		return bean;
	}
	
	
//	public static String getFunctionCoord(String functionName,Class[] paramClasses){
//		final char linkMark = '+';
//		Joiner joiner = Joiner.on(linkMark);
//		StringBuilder sbFunctionBase = new StringBuilder(functionName).append(linkMark);					//"functionName+"
//		sbFunctionBase = joiner.appendTo(sbFunctionBase, paramClasses);
//		
//		return sbFunctionBase.toString();
//	}
	
	public static String getFunctionCoord(String functionName,Class[] paramClasses){
		//"functionName+参数个数"
		final char linkMark = '+';
//		Joiner joiner = Joiner.on(linkMark);
//		StringBuilder sbFunctionBase = new StringBuilder(functionName).append(linkMark);					
//		sbFunctionBase = joiner.appendTo(sbFunctionBase, new int[]{paramClasses.length});
		
		return functionName + "+" + paramClasses.length;
	}
	
	
	/**
	 * 获取指定bean的指定函数
	 * @param beanName
	 * @param functionName
	 * @return
	 */
	public static Method getBeanMethods(String beanName,String functionName,Class[] paramClasses){
		String rowCoord = beanName;											//行坐标
		String columnCoord = getFunctionCoord(functionName,paramClasses);	//列坐标
		if (!methodCacheTable.containsRow(beanName) && !methodCacheTable.containsColumn(functionName)) {
			initBeanMethods(beanName);
		}
		List<Method> methods = methodCacheTable.get(rowCoord, columnCoord);

		if (methods == null){
//			log.debug(String.format("%s---%s--不存在", rowCoord, columnCoord));
			return null;
		}
		
		if (methods != null && methods.size() > 1) {
			for (Method method : methods) {
				Class[] methodParamClassArray = method.getParameterTypes();
				if (methodParamClassArray != null && paramClasses != null) {
					boolean exist = true;
					for (int i = 0; i < methodParamClassArray.length; i++) {
						if (methodParamClassArray[i] != paramClasses[i]) {
							exist = false;
							break;
						}
					}
					if (exist) {
						return method;
					}
				}
			}
		}
		return methods.get(0);
	}

	public static void initBeanMethods(String beanName) {
		// log.debug(beanName);
		Object bean = getBean(beanName);
		if (bean != null) {
			Method[] methods = bean.getClass().getMethods();
			if (methods != null) {
				for (Method method : methods) {
					// log.debug(method.getName());
					String methodColumnCoord = getFunctionCoord(method.getName(), method.getParameterTypes());
					List<Method> methodList;
					if (!methodCacheTable.contains(beanName, methodColumnCoord)) {
						methodList = new ArrayList<Method>();
						methodCacheTable.put(beanName, methodColumnCoord, methodList);
					}
					methodCacheTable.get(beanName, methodColumnCoord).add(method);
				}
			}
		}
	}

}
