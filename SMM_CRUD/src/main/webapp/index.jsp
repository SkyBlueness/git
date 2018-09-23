<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
		http://localhost:3306/crud
 -->
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>

	<!-- 员工编辑的模态框 -->
	<div class="modal fade" id="updateEmp" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工编辑</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="empName_update"></p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_update_input" placeholder="请输入邮箱"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_update_input" value="男"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2_update_input" value="女"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">部门</label>

							<div class="col-sm-4">
								<!-- 部门提交部门id即可 -->
								<select class="form-control" name="dId">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_update_btn">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 员工添加的模态框 -->
	<div class="modal fade" id="addEmp" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工添加</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-10">
								<input type="text" name="empName" class="form-control"
									id="empName_add_input" placeholder="请输入姓名"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">邮箱</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_add_input" placeholder="请输入邮箱"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">性别</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_add_input" value="男"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2_add_input" value="女"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">部门</label>

							<div class="col-sm-4">
								<!-- 部门提交部门id即可 -->
								<select class="form-control" name="dId">
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
				</div>
			</div>
		</div>
	</div>
	

	<!-- 显示标题 -->
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<h1>SSM_CRUD</h1>
			</div>
		</div>
		<!-- 显示按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary" id="addModel">新增</button>
				<button class="btn btn-danger" id="deleteAll">删除</button>
			</div>
		</div>
		<!-- 显示表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-striped" id="tb_emps">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="allCheck"/>
							</th>
							<th>员工编号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>邮箱</th>
							<th>部门</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>
		<!-- 显示分页 -->
		<div class="row">
			<!-- 显示分页文字 -->
			<div class="col-md-6" id="pageInfo"></div>
			<!-- 分页条 -->
			<div class="col-md-6" id="pageNavigate"></div>
		</div>
	</div>
	<script type="text/javascript">
		//更新的记录数
		var superPage;
		//当前页
		var pageNum;
		//打开页面时发送ajsx请求
		$(function() {
			toPage(1);
		});
		//发送ajax请求
		function toPage(pn) {
			$(function() {
				$.ajax({
					url : "${APP_PATH}/emps",//请求路径
					data : "page=" + pn,//发送的数据
					type : "GET",//发送方法
					success : function(result) {//成功后的操作
						//console.log(result);
						//显示员工列表
						tb_emps(result);
						//显示分页信息
						build_page_info(result);
						//显示分页条
						build_page_nav(result);
					}
				});
			});
		}
		
		//给编辑模态框框的保存按钮添加点击事件
		$("#emp_update_btn").click(function(){
			//验证邮箱格式
			var email = $("#email_update_input").val();
			var zzEmail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
			if(!zzEmail.test(email)){
				var ele = "#email_update_input";
				var status = "no";
				var msg = "邮箱格式不正确";
				mhInput(ele,status,msg);
				return false;
			}
			//美化表单并在表单下面显示信息
			var ele = "#email_update_input";
			var status = "yes";
			var msg = "格式正确";
			mhInput(ele,status,msg);
			//从按钮中获取
			var empId = $(this).attr("empId");
			//发生ajax请求
			$.ajax({
				//传递empId
				url:"${APP_PATH}/update/"+empId,
				type:"PUT",
				data:$("#updateEmp form").serialize(),
				success:function(result){
					//隐藏模态框
					$("#updateEmp").modal('hide');
					//当前页
					toPage(pageNum);
				}	
			});
		});
		
		//显示员工信息
		function tb_emps(result) {
			$("#tb_emps tbody").empty();
			var emps = result.map.pageInfo.list;
			//循环遍历emps
			$.each(emps, function(index, emp) {
				var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
				var emp_id = $("<td></td>").append(emp.empId);
				var emp_name = $("<td></td>").append(emp.empName);
				var emp_gender = $("<td></td>").append(emp.gender);
				var emp_email = $("<td></td>").append(emp.email);
				var emp_dept = $("<td></td>").append(emp.department.deptName);
				/**
				<button class="btn btn-success btn-sm">
					<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
				</button>
				 */
				var editBtn = $("<button></button>").addClass(
						"btn btn-success btn-sm editBtn").append(
						$("<span></span>").addClass(
								"glyphicon glyphicon-pencil")).append("编辑");
				 //按钮中添加属性,值为员工id
				 editBtn.attr("empId",emp.empId);
				var delBtn = $("<button></button>").addClass(
						"btn btn-danger btn-sm delete_btn").append(
						$("<span></span>")
								.addClass("glyphicon glyphicon-trash delete_btn")).append(
						"删除");
				delBtn.attr("empId",emp.empId);
				var td_btn = $("<td></td>").append(editBtn).append(delBtn);
				$("<tr></tr>").append(checkBoxTd).append(emp_id).append(emp_name).append(
						emp_gender).append(emp_email).append(emp_dept).append(
						td_btn).appendTo("#tb_emps tbody");
			});
		}
		
		//当allCheck点击时
		$("#allCheck").click(function(){
			$(".check_item").prop("checked",$(this).prop("checked"));
		});
		
		//当所有check_item都点击时
		$(document).on("click",".check_item",function(){
			//判断是否5个全选中
			var b = $(".check_item:checked").length==$(".check_item").length
				$("#allCheck").prop("checked",b);
			
		});
		
		//给全选删除按钮添加事件
		$("#deleteAll").click(function(){
			
			var empName = "";
			var empId = "";
			$.each($(".check_item:checked"),function(){
				empName += $(this).parents("tr").find("td:eq(2)").text()+",";
				empId += $(this).parents("tr").find("td:eq(1)").text()+"-";
			});
				empName = empName.substring(0,empName.length-1);
				empId = empId.substring(0,empId.length-1);
			//先弹出提示框
			if(confirm("确认删除【"+empName+"】吗？")){
				$.ajax({
					url:"${APP_PATH}/delete/"+empId,
					type:"DELETE",
					success:function(result){
						//弹出提示框
						alert(result.xingxi);
						//跳转当前页
						toPage(pageNum);
					}
				});
			} 
			 
		});
		
		//给所有删除按钮添加点击事件
		$(document).on("click",".delete_btn",function(){
			//取出empName值
			var empName = $(this).parents("tr").find("td:eq(2)").text();
			//弹出确认框confirm()方法会弹出确认框，并返回布尔值
			if(!confirm("确认删除【"+empName+"】吗？")){
				return false;
			}
			//发送ajax请求
			var empId = $(this).attr("empId");
			$.ajax({
				url:"${APP_PATH}/delete/"+empId,
				type:"DELETE",
				success:function(result){
					alert(result.xingxi);
					//跳转到当前页
					toPage(pageNum);
				}
			});
		});
		
		//给所有编辑按钮添加点击事件
		$(document).on("click",".editBtn",function(){
			//获取部门名称
			dept("#updateEmp select");
			//点击后添加模态框
			$("#updateEmp").modal({
				backdrop : "static"
				
			});
			//显示员工信息
			getEmp($(this).attr("empId"));
			//将empId赋值给保存编辑员工信息的按钮
			$("#emp_update_btn").attr("empId",$(this).attr("empId"));
		});
		//根据id查询员工
		function getEmp(id){
			$.ajax({
				url:"${APP_PATH}/emp/"+id,
				type:"GET",
				success:function(result){
					$("#empName_update").text(result.map.emp.empName);
					$("#email_update_input").val(result.map.emp.email);
					$("#updateEmp input[name=gender]").val([result.map.emp.gender]);
					$("#updateEmp select").val([result.map.emp.dId]);
				}
			});
		}
		
		//分页信息
		function build_page_info(result) {
			$("#pageInfo").empty();
			$("#pageInfo").append("当前" + result.map.pageInfo.pageNum + "页    ")
					.append("共" + result.map.pageInfo.pages + "页   ").append(
							"共" + result.map.pageInfo.total + "条记录数");
			superPage = result.map.pageInfo.total;
			pageNum = result.map.pageInfo.pageNum;
		}
	
		//构建分页条
		function build_page_nav(result) {
			$("#pageNavigate").empty();
			//创建ul标签
			var ul = $("<ul></ul>").addClass("pagination");
			//创建li标签，加入a标签
			var firstPage = $("<li></li>").append(
					$("<a></a>").append("首页").attr("href", "#"));
			var prePage = $("<li></li>").append($("<a></a>").append("&laquo;"));
			//如果当前页是首页就禁止点击上一页
			if (result.map.pageInfo.isFirstPage == true) {

				prePage.addClass("disabled");
			} else {
				firstPage.click(function() {
					toPage(1);
				});
				prePage.click(function() {
					toPage(result.map.pageInfo.pageNum - 1);
				});
			}
			//if(result.map.pageInfo.)
			ul.append(firstPage).append(prePage);

			//先往ul标签里添加首页和上一页
			ul.append(firstPage).append(prePage);
			//循环遍历pageInfo内的navigatepageNums
			$.each(result.map.pageInfo.navigatepageNums, function(index, item) {
				var numLi = $("<li></li>").append($("<a></a>").append(item));
				//如果当前页是循环遍历的页就设置成高亮
				if (result.map.pageInfo.pageNum == item) {
					numLi.addClass("active");
				} else {
					numLi.click(function() {
						toPage(item);
					});
				}
				ul.append(numLi);
			});
			var nextPage = $("<li></li>")
					.append($("<a></a>").append("&raquo;"));
			var lastPage = $("<li></li>").append(
					$("<a></a>").append("末页").attr("href", "#"));
			//如果当前页是最后一页就紧张点击下一页
			if (result.map.pageInfo.isLastPage == true) {

				nextPage.addClass("disabled");
			} else {
				//点击下一页
				nextPage.click(function() {
					toPage(result.map.pageInfo.pageNum + 1);
				});
				//点击尾页
				lastPage.click(function() {
					toPage(result.map.pageInfo.pages);
				});
			}
			ul.append(nextPage).append(lastPage);
			var navEle = $("<nav></nav>").append(ul);
			ul.appendTo('#pageNavigate');

		}
		<!--添加员工按钮监听器, 弹出添加模态框-->
		$("#addModel").click(function() {
			//重置表单数据和形式
			resetForm();
			//发送ajax请求，获取部门名
			dept();
			//点击后添加模态框
			$("#addEmp").modal({
				backdrop : "static"
			});
		});
		//获取部门名ajax请求
		function dept(ele) {
			$.ajax({
				url:"${APP_PATH}/depts",
				type:"GET",
				success:function(result){	
					//清空select标签中的元素
					$(ele).empty();
					$.each(result.map.dept,function(){
						var option = $("<option></option>")
						option.append(this.deptName).attr("value",this.deptId)
						option.appendTo(ele);
					});
					
				}
			});
		}
		//重置表单
		function resetForm(){
			//重置表单内容
			$("#addEmp form")[0].reset();
			//重置表单样式
			$("#addEmp").find("*").removeClass("has-success has-error");
			//将class为help-block的标签文本内容清空
			$("#addEmp").find(".help-block").text("");
			
		}
		
		//绑定事件当次标签发生改变时
		$("#empName_add_input").change(function(){			
			//发送ajax请求
			var empName = this.value;
			$.ajax({
				url:"${APP_PATH}/empName",
				type:"POST",
				//获取当前对象的值
				data:"empName="+empName,
				success:function(result){
					//100代表用户名没有重复
					if(result.zhuangtai == 100){
						//设置input显示值
						mhInput("#empName_add_input","yes","用户名可用");
						$("#emp_save_btn").attr("zhuangtai","yes");
						
					}
					else{
						mhInput("#empName_add_input","no",result.map.fail);
						//并该添加按钮中中设置属性
						$("#emp_save_btn").attr("zhuangtai","no");
					}
				}
			});
		});
		
		//校验方法
		function jiaoYan(){
			//获取empName_add_input元素的值，输入的empName值
			var empName = $("#empName_add_input").val();
			//使用jquery的正则表达式校验
			var zzName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
			//调用test方法验证
			if(!zzName.test(empName)){
				var ele = "#empName_add_input";
				var status = "no";
				var msg = "用户名可以是2-5位中文或者6-16位英文和数字的组合";
				mhInput(ele,status,msg);
				return false;
			}
			var ele = "#empName_add_input";
			var status = "yes";
			var msg = "用户名可用";
			mhInput(ele,status,msg);
			//校验email信息
			var email = $("#email_add_input").val();
			var zzEmail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
			if(!zzEmail.test(email)){
				var ele = "#email_add_input";
				var status = "no";
				var msg = "邮箱格式不正确";
				mhInput(ele,status,msg);
				return false;
			}
			//美化表单并在表单下面显示信息
			var ele = "#email_add_input";
			var status = "yes";
			var msg = "格式正确";
			mhInput(ele,status,msg);
			return true;
		}
		
		//校验表单信息，美化表单，并在表单下面显示错误信息
		
		function mhInput(ele,status,msg) {
			//首先清空父元素元素之前的class元素
			$(ele).parent().removeClass("has-success has-error");
			//清空span元素的信息
			$(ele).next("span").text("");
			//判断statsu的信息,no表示失败。
			if(status=="no"){
				//往父元素中添加class属性has-error
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
			}
			if(status=="yes"){
				$(ele).parent().addClass("has-success");
				$(ele).next("span").text(msg);
			}
		}
		
		//点击保存按钮时发送ajax请求
		$("#emp_save_btn").click(function(){
			//对表单输入信息进行校验
			if(!jiaoYan()){
				return false;
			}
			//判断姓名是否可用
			if($("#emp_save_btn").attr("zhuangtai")=="no"){
				return false;
			}
			//发送ajax请求
			$.ajax({
				url:"${APP_PATH}/emp",
				type:"POST",
				//serialize()将添加员工的表单内的信息序列化
				data:$("#addEmp form").serialize(),
				success:function(result){
					alert(result.zhuangtai);
					//再进行JSR303校验
					if(result.zhuangtai==200){
						if(result.map.fieldError.empName!=null){
							mhInput("#empName_add_input","no",result.map.fieldError.empName);
						}
						if(result.map.fieldError.email!==null){
							mhInput("#email_add_input","no",result.map.fieldError.email);
						}
						return false;
					}
					//关闭添加员工的模型框
					$("#addEmp").modal('hide');
					//跳转到末页，保存后信息数+1，可能末页数变化，所以穿total
					toPage(superPage);
					//提醒用户操作成功
					alert(result.xingxi);
				}
			});	
		});
	</script>
</body>
</html>