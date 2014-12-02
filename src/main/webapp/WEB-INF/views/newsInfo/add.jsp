<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.smart.school.devicemanagement.common.Uploader" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="../shared/taglib.jsp"%>

<html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<head>
   <meta charset="utf-8" />
   <title>Conquer | Form Stuff - Form Controls</title>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <meta name="MobileOptimized" content="320">
   
   <%@ include file="../shared/importCss.jsp"%>
   <%@ include file="../shared/importJs.jsp"%>
   <!-- BEGIN PAGE LEVEL SCRIPTS -->
   <link href="<c:url value='/umeditor/themes/default/css/umeditor.css'/>" type="text/css" rel="stylesheet">
   <script type="text/javascript" charset="utf-8" src="<c:url value='/umeditor/umeditor.config.js'/>"></script> 
   <script type="text/javascript" charset="utf-8" src="<c:url value='/umeditor/umeditor.min.js'/>"></script> 
   <script type="text/javascript" src="<c:url value='/umeditor/lang/zh-cn/zh-cn.js'/>"></script>
   
   <script type="text/javascript" src="<c:url value='/js/jquery.treeLite.js?ver=10'/>"></script>
   <script type="text/javascript" src="<c:url value='/js/app.js'/>"></script> 
   <!-- END PAGE LEVEL SCRIPTS -->

   <link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">
   
   <%@ include file="../shared/pageHeader.jsp"%>
   
   <div class="clearfix"></div>
   <!-- BEGIN CONTAINER -->
   <div class="page-container">
      
      <%@ include file="../shared/sidebarMenu.jsp"%>
      
      <!-- BEGIN PAGE -->  
      <div class="page-content">
         
         <%@ include file="../shared/styleSet.jsp"%>
                    
         <!-- BEGIN PAGE HEADER-->   
         <div class="row">
            <div class="col-md-12">
               <!-- BEGIN PAGE TITLE & BREADCRUMB-->
               <h3 class="page-title">
                  Form Controls <small>form controls and more</small>
               </h3>
               <ul class="page-breadcrumb breadcrumb">
                  <li>
                     <i class="icon-home"></i>
                     <a href="<c:url value='/home/index'/>">首页</a> 
                     <i class="icon-angle-right"></i>
                  </li>
                  <li>
                     <span>${requestScope.permissionMenu.rootName}</span>
                     <i class="icon-angle-right"></i>
                  </li>
                  <li><span>${requestScope.permissionMenu.subName}</span></li>
               </ul>
               <!-- END PAGE TITLE & BREADCRUMB-->
            </div>
         </div>
         <!-- END PAGE HEADER-->
         <!-- BEGIN PAGE CONTENT-->
         <div class="row">
            <div class="col-md-12">
               <!-- BEGIN SAMPLE FORM PORTLET-->   
               <div class="portlet ">
                  <div class="portlet-title">
                     <div class="caption"><i class="icon-edit"></i>公告内容</div>
                  </div>
                  <div class="portlet-body form">
                     <form:form modelAttribute="contentModel" class="form-horizontal" method="POST">
                        <div class="form-body">
                           <div class="form-group">
                              <label  class="col-md-2 control-label">标题</label>
                              <div class="col-md-10">
                                 <form:input path="title" class="form-control" placeholder="名称"/><br/>
                                 <form:errors path="title" class="field-has-error"></form:errors>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">公告类型</label>
                              <div class="col-md-10">
                              	<form:select path="newsTypeModel"  multiple="false" class="form-control" >
                              		<form:options items="${users}" itemLabel="strName" itemValue="pk"/>
                              	</form:select>
                                 <form:errors path="newsTypeModel" class="field-has-error"></form:errors>
                              </div>
                           </div>      
                           <div class="form-group">
                              <label  class="col-md-2 control-label">内容</label>
                              <div class="col-md-10">
                                 <!--style给定宽度可以影响编辑器的最终宽度-->
								<script type="text/plain" id="myEditor" style="width:720px;height:240px;">
    								<p>这里我可以写一些输入提示</p>
								</script>
                                 <br/>
                                 <form:errors path="content" class="field-has-error"></form:errors>
                              </div>
                           </div>                                                                  
                        </div>
                        <div class="form-actions fluid">
                           <div class="col-md-offset-6 col-md-6">
                              <button type="submit" class="btn btn-success">保存</button>                             
                           </div>
                        </div>
                     </form:form>
                  </div>
               </div>
               <!-- END SAMPLE FORM PORTLET-->
            </div>
         </div>
         <!-- END PAGE CONTENT-->    
      </div>
      <!-- END PAGE -->  
   </div>
   <!-- END CONTAINER -->
   <%@ include file="../shared/pageFooter.jsp"%>
     
   <script type="text/javascript">
   	  $(function() {   
         App.init();
      });
   </script>
   <!-- END JAVASCRIPTS -->   
   <script type="text/javascript">
    //实例化编辑器
    var um = UM.getEditor('myEditor');
    </script>
</body>
<!-- END BODY -->
</html>