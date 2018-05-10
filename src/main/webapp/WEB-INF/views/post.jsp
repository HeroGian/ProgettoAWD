<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false" %>

<layout:extends name="base.jsp">

	<layout:put block="head">
		
		<script type="text/javascript" src='<c:url value="/resources/js/comment.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/resources/js/reply.js"/>'></script>
		
		<script
		    type="text/javascript"
		    async defer
		    src="//assets.pinterest.com/js/pinit.js"
		></script>
		
	</layout:put>

	<layout:put block="banner-section">
		<div class="banner-section" id="post-main">
			<h3 class="tittle">${post.title} <i class="glyphicon glyphicon-file"></i></h3>
			<div class="single">
			<div class="b-bottom"> 
				<p class="sub">${post.post}</p>
				<br>
			    <p>${post.pubdate} 
			    	<a class="span_link">
			    		<span class="glyphicon glyphicon-comment"></span>
			    		${numComments} 
			    	</a>
			    	<a class="span_link">
			    		<span class="glyphicon glyphicon-eye-open"></span>
			    		${post.views} 
			    	</a>
			    </p>
			</div>
			</div>
			<div class="single-bottom">
				<div class="single-middle">
					<ul class="social-share">
						<li>
							<span>SHARE</span>
						</li>
						<li>
							<a href="https://www.facebook.com/sharer/sharer.php?u=${pageContext.request.contextPath}/user/${post.ownerblog.owner.userid}/blog/post/${post.postid}" class="button button--icon button--facebook">
								<i></i>
							</a>
						</li>						
						<li>
							<a class="twitter-share-button" href="https://twitter.com/intent/tweet?text=${post.title}"> 
								<i class="tin"> </i>
							</a>
						</li>
						<li>
							<a href="https://www.pinterest.com/pin/create/button/" data-pin-do="buttonBookmark">
								<i class="message"> </i>
							</a>
						</li>				
					</ul>
					<a href="#"><i class="arrow"> </i></a>
					<div class="clearfix"> </div>
				</div>
			</div>
			<div class="response">
				<h4 class="responses">Responses</h4>
				
					<!--                           -->
					<!-- Ciclo su tutti i commenti -->
					<!--                           -->
					<c:forEach items="${comments}" var="c" varStatus="loop">
						<div class="media response-info ${loop.index}" id="${c.commentid}">
							<div class="media-left response-text-left">
								<a href="${pageContext.request.contextPath}/user/${c.author.userid}">
									<img class="media-object" src='<c:url value="${c.author.avatar}"/>' height="80" width="80" />
								</a>
								<h5><a href="${pageContext.request.contextPath}/user/${c.author.userid}">${c.author.username}</a></h5>
							</div>
							<div class="media-body response-text-right">
								<p>${c.body}</p>
								<ul>
									<li>${c.pubdate}</li>
									<c:if test = "${not empty userSession}">
										<li><a class="reply-link" id="${loop.index}" style="cursor: pointer;">Reply</a></li>
									</c:if>
								</ul>
								<!--                                     -->
								<!-- Ciclo sulle risposte di un commento -->
								<!--                                     -->
								<c:forEach items="${replies[loop.index]}" var="r">
									<div class="media response-info" >
										<div class="media-left response-text-left">
											<a href="${pageContext.request.contextPath}/user/${r.author.userid}">
												<img class="media-object" src='<c:url value="${r.author.avatar}"/>' height="80" width="80" />
											</a>
											<h5><a href="${pageContext.request.contextPath}/user/${r.author.userid}">${r.author.username}</a></h5>
										</div>
										<div class="media-body response-text-right">
											<p>${r.body}</p>
											<ul>
												<li>${r.pubdate}</li>
											</ul>
										</div>
										<div class="clearfix"> </div>
									</div>
								</c:forEach>
								<!--                                     -->
								<!-- Ciclo sulle risposte di un commento -->
								<!--                                     -->
								
								<!--            -->
								<!-- Form Reply -->
								<!--            -->
								<form id="reply-form-${loop.index}" class="coment-form" method="POST" name="replyForm" style="display: none;">
									<textarea id="ReplyTxtArea-${loop.index}" class="comment" name="body"></textarea>
									<br><br>
									<input id="replyBtn-${loop.index}" name="replyBtn" type="submit" value="Submit Reply" >
								</form>
								<!--            -->
								<!-- Form Reply -->
								<!--            -->
							</div>
							<div class="clearfix"> </div>
						</div>
					</c:forEach>
					<!--                           -->
					<!-- Ciclo su tutti i commenti -->
					<!--                           -->
			</div>
			<!-- New Post -->
			<div class="coment-form">
				<c:if test = "${not empty userSession}">
					<h4>Leave your comment</h4>
					<c:set var = "path" value = "${pageContext.request.contextPath}/user/${post.ownerblog.owner.userid}/blog/post/${post.postid}"/>
					<!--              -->
					<!-- Form Comment -->
					<!--              -->
					<form method="POST" name="commentForm">
						<textarea id="commentTextArea" class="comment" name="body"></textarea>
						<br><br>
						<input name="commentBtn" type="submit" value="Submit Comment">
					</form>
					<!--              -->
					<!-- Form Comment -->
					<!--              -->
				</c:if>
			</div>
			<div class="clearfix"></div>
		</div>
	</layout:put>
	
	<layout:put block="banner-right-text">
		<div class="banner-right-text">
			<h3 class="tittle">Related Posts  <i class="glyphicon glyphicon-facetime-video"></i></h3>
			<div class="general-news">
				<div class="general-inner">
					<div class="edit-pics">
						<!--                        -->
						<!-- Ciclo sui tag del post -->
						<!--                        -->
						<c:forEach items="${tags}" var="t" varStatus="loop01">
							<h4>${t.titleCapitalized}</h4>
							<div class="editor-pics">
								<!--                                 -->
								<!-- Ciclo sui posts del tag i-esimo -->
								<!--                                 -->
								<c:forEach items="${posts_x_tag[loop01.index]}" var="pt" varStatus="loop02">
									<c:set var = "path" value = "${pageContext.request.contextPath}/user/${pt.ownerblog.owner.userid}/blog/post/${pt.postid}"/>
									<div class="col-md-3 item-pic">
										<a href="${path}">
											<img src="${images[loop01.index][loop02.index]}" class="img-responsive" alt="">
										</a>
									</div>
									<div class="col-md-9 item-details">
										<h5 class="inner two"><a href="${path}">${pt.smalltitle}</a></h5>
										<div class="td-post-date two">
											<i class="glyphicon glyphicon-time"></i>
											${pt.pubdate} 
											<a href="#">
												<i class="glyphicon glyphicon-comment"></i>
												${comments_count[loop01.index][loop02.index]} 
											</a>
										</div>
									</div>
								</c:forEach>
								<!--                                 -->
								<!-- Ciclo sui posts del tag i-esimo -->
								<!--                                 -->
								<div class="clearfix"></div>
							</div>
							<br>
						</c:forEach>
						<!--                        -->
						<!-- Ciclo sui tag del post -->
						<!--                        -->
				</div>
			</div>		 
		</div>	
		</div>
	</layout:put>
	
</layout:extends>