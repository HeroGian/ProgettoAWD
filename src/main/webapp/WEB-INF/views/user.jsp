<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false" %>

<layout:extends name="base.jsp">

	<layout:put block="head">
		<script type="text/javascript" src='<c:url value="/resources/js/user.js"/>'></script>
	</layout:put>

	<layout:put block="banner-section">
		<div class="banner-section">			
				<div class="about-content">
					<h3 class="tittle">${userModel.username} <i class="glyphicon glyphicon-user"></i></h3>
					<h5>${userModel.email}</h5>
					<br>
					<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
						<div class="about-left">
							<img id="imgAvatar" src='<spring:url value="${userModel.avatar}"/>' alt=" " />
							<input id="choseAvatar" type="file" name="image" style="display: none;">
							<c:if test = "${not empty userSession and userSession.userid == userModel.userid}">
								<h5 class="inner two"><a id="editProfile" style="cursor: pointer;">Edit Profile</a></h5>
							</c:if>
						</div>
						<div class="about-right">
							<h4>${userModel.name} ${userModel.surname}</h4><br>
							<p>${userModel.creation} 
								<a class="span_link" href="#">
									<span class="glyphicon glyphicon-comment"></span>
									${numComments} 
								</a>
							</p>
							<p>
								<label id="labelDescrStandard">${userModel.description}</label>
								<input id="submitAvatar" type="submit" style="display: none;">
							</p>
						</div>
					</form>
					<div class="clearfix"></div>
					<br>	
				</div>
		</div>
	</layout:put>

	<layout:put block="banner-right-text">
		<div class="banner-right-text">
			 <h3 class="tittle">Recent Comments  <i class="glyphicon glyphicon-facetime-video"></i></h3>
			 <div class="general-news">
				<div class="general-inner">
					<div class="edit-pics">
						<c:forEach items="${comments}" var="c" varStatus="loop">
							<c:set var = "path" value = "${pageContext.request.contextPath}/user/${c.post.ownerblog.owner.userid}/blog/post/${c.post.postid}"/>
							<div class="editor-pics">
								<div class="col-md-3 item-pic">
									<a href="${path}" >
										<img src="${images[loop.index]}" class="img-responsive" alt="">
									</a>
								</div>
								<div class="col-md-9 item-details">
									<h5 class="inner two">
										<a href="${path}">${c.post.title}</a>
									</h5>
									<div class="td-post-date two">
										<i class="glyphicon glyphicon-time"></i>
										${c.pubdate} 
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
						</c:forEach>
					</div>
				</div> 
			</div>	
		 </div>
	</layout:put>

</layout:extends>