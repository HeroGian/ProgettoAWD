<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false" %>

<layout:extends name="base.jsp">
	<layout:put block="head">
		<script type="text/javascript" src='<c:url value="/resources/js/signup.js"/>'></script>
	</layout:put>
	<layout:put block="banner-section">
		<div class="sign-up-form">
			<h3 class="tittle">Registration <i class="glyphicon glyphicon-file"></i></h3>
			<p>Having hands on experience in creating innovative designs, I do offer design solutions which harness.</p>
				<div class="sign-up">
					<h3 class="tittle reg">Personal Information <i class="glyphicon glyphicon-user"></i></h3>
					
					<form method="POST" name="signupForm" action="signup">
						<div class="sign-u">
							<div class="sign-up1">
								<h4 class="a">First Name* :</h4>
							</div>
							<div class="sign-up2">
									<input name="name" type="text" class="signup" >
							</div>
							<div class="clearfix"> </div>
						</div>
						
						<div class="sign-u">
							<div class="sign-up1">
								<h4 class="b">Last Name* :</h4>
							</div>
							<div class="sign-up2">
								<input name="surname" type="text" class="signup" >
							</div>
							<div class="clearfix"> </div>
						</div>
						
						<div class="sign-u">
							<div class="sign-up1">
								<h4 class="c">Email Address* :</h4>
							</div>
							<div class="sign-up2">
								<input name="email" type="text" class="signup" >
							</div>
							<div class="clearfix"> </div>
						</div>
						
						<div class="sign-u">
							<div class="sign-up1">
								<h4 class="c">Description* :</h4>
							</div>
							<div class="sign-up2">
								<textarea name="description" class="textareaDescr"></textarea>
							</div>
							<div class="clearfix"> </div>
						</div>
						
						<h3 class="tittle reg">Login Information <i class="glyphicon glyphicon-off"></i></h3>
						
						<div class="sign-u">
							<div class="sign-up1">
								<h4 class="d">Username* :</h4>
							</div>
							<div class="sign-up2">
								<input name="username" type="text" class="signup" >
							</div>
							<div class="clearfix"> </div>
						</div>
						
						<div class="sign-u">
							<div class="sign-up1">
								<h4 class="d">Password* :</h4>
							</div>
							<div class="sign-up2">
								<input name="password" type="password" class="signup">
							</div>
							<div class="clearfix"> </div>
						</div>
						
						<input name="signupBtn" type="submit" value="Submit">
					</form>
										<!-- Result Container  -->
					<br>
					<div id="resultContainer" style="display: none;">
					  <pre style="color: green;">
					    <code>Utente creato con successo</code>
					   </pre>
					</div>
				</div> 
			</div>
	</layout:put>
</layout:extends>