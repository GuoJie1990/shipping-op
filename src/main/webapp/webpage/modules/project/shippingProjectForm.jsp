<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>项目管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		  if(validateForm.form()){
			  $("#inputForm").submit();
			  return true;
		  }
	
		  return false;
		}
		$(document).ready(function() {
			validateForm = $("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
					laydate({
			            elem: '#commencingDay', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
			        });
					laydate({
			            elem: '#cancellingDay', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
			            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
			        });
		});
	</script>
</head>
<body>
		<form:form id="inputForm" modelAttribute="shippingProject" action="${ctx}/project/shippingProject/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<table class="table table-bordered  table-condensed dataTables-example dataTable no-footer">
		   <tbody>
				<tr>
					<td class="width-15 active"><label class="pull-right">备注信息：</label></td>
					<td class="width-35">
						<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">客户id：</label></td>
					<td class="width-35">
						<form:input path="clientId" htmlEscape="false" class="form-control  digits"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">船名：</label></td>
					<td class="width-35">
						<form:input path="shipName" htmlEscape="false" maxlength="64" class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">装港：</label></td>
					<td class="width-35">
						<form:input path="onLoad" htmlEscape="false" class="form-control  digits"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">卸港：</label></td>
					<td class="width-35">
						<form:input path="offLoad" htmlEscape="false" class="form-control  digits"/>
					</td>
					<td class="width-15 active"><label class="pull-right">commencing_day：</label></td>
					<td class="width-35">
						<input id="commencingDay" name="commencingDay" type="text" maxlength="20" class="laydate-icon form-control layer-date "
							value="<fmt:formatDate value="${shippingProject.commencingDay}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">cancelling_day：</label></td>
					<td class="width-35">
						<input id="cancellingDay" name="cancellingDay" type="text" maxlength="20" class="laydate-icon form-control layer-date "
							value="<fmt:formatDate value="${shippingProject.cancellingDay}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
					</td>
					<td class="width-15 active"><label class="pull-right">货物名：</label></td>
					<td class="width-35">
						<form:input path="cargoName" htmlEscape="false" maxlength="64" class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">货量：</label></td>
					<td class="width-35">
						<form:input path="cargoWeight" htmlEscape="false" maxlength="64" class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">运费：</label></td>
					<td class="width-35">
						<form:input path="initCharge" htmlEscape="false" class="form-control  number"/>
					</td>
				</tr>
				<tr>
					<td class="width-15 active"><label class="pull-right">合同电子版：</label></td>
					<td class="width-35">
						<form:input path="contract" htmlEscape="false" maxlength="64" class="form-control "/>
					</td>
					<td class="width-15 active"><label class="pull-right">hire：</label></td>
					<td class="width-35">
						<form:input path="hire" htmlEscape="false" maxlength="64" class="form-control "/>
					</td>
				</tr>
		 	</tbody>
		</table>
	</form:form>
</body>
</html>