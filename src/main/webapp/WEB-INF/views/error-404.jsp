<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false" %>

<layout:extends name="base.jsp">
	<layout:put block="banner-section">
		<div class="main">
			<div class="container">
				<div class="error-404 text-center">
						<!-- <h1>404</h1>
						<p>this link is a dead link</p> -->
						<img src='<c:url value="/resources/images/404.png" />' />
						<br>
						<a class="b-home" href="${pageContext.request.contextPath}/">Back to Home</a>
				</div>
			</div>
		</div>
	</layout:put>
</layout:extends>