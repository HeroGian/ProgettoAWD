<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false" %>

<layout:extends name="base.jsp">

	<layout:put block="head">
		<script>			
			$(document).ready(function() {				
				$('input[name=skipBtn]').click(function(e) {					
					e.preventDefault();
					window.location.href = '${pageContext.request.contextPath}/';
				})
			});
		</script>
	</layout:put>

	<layout:put block="banner-section">
		<div class="login">
		<div class="login-grids">
			<div class="col-md-6 log" id="new-blog">
				<h3 class="tittle">Login <i class="glyphicon glyphicon-lock"></i></h3>
				<p>Welcome, please enter the following to continue.</p>
				<form id="commentform" method="POST" 
						  action="${pageContext.request.contextPath}/user/${userSession.userid}/new-blog">
					<h5>Title:</h5>	
					<input name="blogTitle" type="text" value="">
					<hr>
					<h5>Category:</h5>
					<select name="blogCategory" class="custom-select" id="inputGroupSelect">
						<c:forEach items="${categories}" var="item" varStatus="loop">
							<option value="${loop.count}">${item.title}</option>
						</c:forEach>
					</select>
					<hr>
					<input class=signup type="submit" value="Done">
					<input name="skipBtn" id="skip-creation" class=signup type="submit" value="Skip">
				</form>
			</div>
		</div>
	</div>
	</layout:put>
</layout:extends>