<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false" %>

<layout:extends name="base.jsp">
	<layout:put block="head">
		<script type="text/javascript">
			function clearErrors(){
				$('.error').remove();
				$('input[class=signup]').css('border', '1px solid #333');
			}
		</script>
		<script type="text/javascript">
			function userErrors(){
				
				var err = "<c:out value='${errors.userErr}'/>";
				
				$('input[name=loginUsername]').after('<br><span class="error">'+err+'</span>');
				$('input[name=loginUsername]').css('border', '1px solid red');
			}
		</script>
		<script type="text/javascript">
			function passErrors(){
				var err = "<c:out value='${errors.passErr}'/>";
				
				$('input[name=loginPassword]').after('<br><span class="error">'+err+'</span>');
				$('input[name=loginPassword]').css('border', '1px solid red');
			}
		</script>
	</layout:put>
	<layout:put block="banner-section">
		<div class="login">
		<div class="login-grids">
			<div class="col-md-6 log">
					 <h3 class="tittle">Login <i class="glyphicon glyphicon-lock"></i></h3>
					 <p>Welcome, please enter the following to continue.</p>
					 <form method="POST" name="loginForm" action="login">
						 <h5>User Name:</h5>	
						 <input name="loginUsername" type="text" value="">
						 <c:if test="${not empty errors.userErr}">
						    <script> userErrors() </script>
						 </c:if>
						 <br><br>
						 <h5>Password:</h5>
						 <input name="loginPassword" type="password" value="">
						 <c:if test="${not empty errors.passErr}">
						    <script> passErrors() </script>
						 </c:if>
						 <br><br>
						 <input class=signup type="submit" value="Login">
					 </form>
					<a href="#">Forgot Password ?</a>
			</div>
			<div class="col-md-6 login-right">
					 <h3 class="tittle">New Registration <i class="glyphicon glyphicon-file"></i></h3>
					<p>By creating an account with our store, you will be able to move through the checkout process faster, store multiple shipping addresses, view and track your orders in your account and more.</p>
					<a href="${pageContext.request.contextPath}/signup" class="hvr-bounce-to-bottom button">Create An Account</a>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	</layout:put>
</layout:extends>