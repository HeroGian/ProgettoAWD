<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page isELIgnored="false" %>

<layout:extends name="base.jsp">

	<layout:put block="head">
  		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  		<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1/css/froala_editor.pkgd.min.css" rel="stylesheet" type="text/css" />
		<link href="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1/css/froala_style.min.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.5.1//js/froala_editor.pkgd.min.js"></script>
		
		<script>
			$(function() {
				$('textarea').froalaEditor()
			});
		</script>
		
		<script>
			$(document).ready(function() {
				$('input[id=titlePost]').keyup(function(e) {
					if($(this).val() != "") {
						$(this).css('border', '1px solid #ccc');
						$('span[class=error]').hide();
						$('button[id=btnSuccess]').prop("disabled", false);
					}
					else{
						$(this).css('border', '1px solid red');
						$('span[class=error]').show();
						$('button[id=btnSuccess]').prop("disabled", true);
					}
				});
			});
		</script>
		
	</layout:put>

	<layout:put block="banner-section">
			
		<div class="banner-section" id="blog-banner-main">
		   <h3 class="tittle">${blog.title} <i class="glyphicon glyphicon-picture"></i></h3>
		   <h5 class="tittle">${blog.category.title}</h5>	   
		   		<hr>
		   		
		   		<c:if test = "${not empty userSession and userSession.userid == blog.owner.userid}">
				   	<!-- Trigger the modal with a button -->
					<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">New Post</button>
				</c:if>
		   	   		   
		   		<!-- Modal -->
				  <div class="modal fade" id="myModal" role="dialog">
				    <div class="modal-dialog modal-lg">
				      <div class="modal-content">
				        <div class="modal-header">
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				          <h4 class="modal-title">Post Creation</h4>
				        </div>
				        <div class="modal-body">
				        
				        	<form id="froala-form" method="POST" 
								  action="${pageContext.request.contextPath}/user/${userSession.userid}/blog/new-post">
								
				        		<label>Title:</label>
			                	<input style="border: 1px solid red;" type="text" id="titlePost" class="form-control input-lg glyphicon-remove" name="postTitle" value="">
			                	<span class="error">* insert a title for your post.</span>
			                	<br>
			                	<label>Tags:</label>
			                    <input type="text" class="form-control input-lg" name="postTags">
			                    <span class="warning">* use commas between tags.</span>
			                    
			                    <hr>
			                    <textarea name="postBody" id="froala-editor"></textarea>
			                    
			                    <br>           
			                    <button type="submit" id="btnSuccess" class="btn btn-success" disabled="disabled" >Create</button>
			                    <button type="submit" class="btn btn-failure" data-dismiss="modal">Cancel</button>
			                </form>
			                
				        </div>
				      </div>
				    </div>
				  </div>
				  
				<!--/top-news-->
			   <!--/top-news-->
			  <div class="top-news">
			  	<c:forEach items="${posts}" var="p" varStatus="loop">
			  		<c:set var = "path" value = "${pageContext.request.contextPath}/user/${p.ownerblog.owner.userid}/blog/post/${p.postid}"/>
					 <div class="top-inner">
						<div class="col-md-6 top-text">
							 <a href="#"><img src="#" class="img-responsive" alt=""></a>
					          <div class="ser">
							    <h5 class="top">
							    	<a href='<c:out value = "${path}"/>'>${p.smalltitle}</a>
							    </h5>
								<p>${p.smallpost}</p>
							    <p>${p.pubdate} 
							    	<a class="span_link" href="#">
							    		<span class="glyphicon glyphicon-comment"></span>
							    		${comments[loop.index]} 
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
				</c:forEach>
	            </div>
				</div>
	</layout:put>
</layout:extends>