<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false" %>

<layout:extends name="base.jsp">

	<layout:put block="banner-section">
	
		<!--banner-section-->
		<div class="banner-section">
		   <h3 class="tittle">Recent Posts <i class="glyphicon glyphicon-picture"></i></h3>	
			  <!--/top-news-->
			   <!--/top-news-->
			  <div class="top-news">
				<div class="top-inner">
					<c:forEach items="${posts_top_10}" var="p" varStatus="loop">
						<c:set var = "path" value = "${pageContext.request.contextPath}/user/${p.ownerblog.owner.userid}/blog/post/${p.postid}"/>
						 <div class="col-md-6 top-text">
							<a href="${path}">
								<img src='<c:url value="${images[loop.index]}"/>' class="img-responsive" alt="">
							</a>
							<h5 class="top">
								<a href="${path}">${p.smalltitle}</a>
							</h5>
							<p>${p.smallpost}</p>
							<p>${p.pubdate} 
								<a class="span_link">
									<span class="glyphicon glyphicon-comment"></span>
									${numComments[loop.index]} </a>
								<a class="span_link">
									<span class="glyphicon glyphicon-eye-open"></span>
									${p.views} </a>
								<a class="span_link">
									<span class="glyphicon glyphicon-circle-arrow-right"></span>
								</a>
							</p>
						</div>
					</c:forEach>
					 
					 <div class="clearfix"> </div>
				 </div>
	            </div>
		     </div>
	</layout:put>
	
	<layout:put block="banner-right-text">
		<div class="banner-right-text">
			 <h3 class="tittle">Recent Comments  <i class="glyphicon glyphicon-facetime-video"></i></h3>
			 <div class="general-news">
				<div class="general-inner">
					<div class="edit-pics">
					
						<c:forEach items="${comments_top_5}" var="c">
						
							<c:set var = "path" value="${pageContext.request.contextPath}/user/${c.post.ownerblog.owner.userid}/blog/post/${c.post.postid}"/>
							<c:set var = "strred" value = "${c.author.username} @ ${c.post.title}"/>
							
							<div class="editor-pics">
								<div class="col-md-3 item-pic">
									<a href="${pageContext.request.contextPath}/user/${c.author.userid}" >
										<img src='<c:url value="${c.author.avatar}"/>' class="img-responsive" alt="">
									</a>
								</div>
								<div class="col-md-9 item-details">
									<h5 class="inner two">
										<a href="${path}">${strred}</a>
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