<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false" %>

<layout:extends name="base.jsp">
	<layout:put block="banner-section">
		<div class="search-box-main">
			<div class="b-search-main">
				<form method="GET" action="search">
					<input name="q" id="textSearch" type="text" autocomplete="off" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
					<input id="submitSearch" type="submit" value="">
				</form>
				<div id="topSearchResultList" class="incrementalSearchResultList" style="display: none;"></div>								
			</div>
		</div>
		<div class="top-news">
			<c:forEach items="${posts}" var="p" varStatus="loop">
				<c:set var = "path" value = "${pageContext.request.contextPath}/user/${p.ownerblog.owner.userid}/blog/post/${p.postid}"/>
				<div class="top-inner">
					<div class="col-md-6 top-text top-search">
						<div class="ser search-container">
							<div class="img-box">
								<a href="${path}">
									<img src="${imgs[loop.index]}" class="img-responsive" alt="">
								</a>
							</div>
							<div class="text-box">
								<h5 class="top">
									<a href="${path}">${p.title}</a>
								</h5>
								<p>${p.smallpost}</p>
								<p>${p.pubdate} 
									<a class="span_link" href="#">
										<span class="glyphicon glyphicon-comment"></span>
										${numComments[loop.index]} 
									</a>
									<a class="span_link" href="#">
										<span class="glyphicon glyphicon-eye-open"></span>
										${p.views} 
									</a>
									<a class="span_link" href='<c:out value = "${path}"/>'>
										<span class="glyphicon glyphicon-circle-arrow-right"></span>
									</a>
								</p>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</layout:put>
</layout:extends>