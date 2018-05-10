<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://kwonnam.pe.kr/jsp/template-inheritance" prefix="layout"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>CaneCarroArmato a Blogging Responsive Website</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords" content="Blogger Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android  Compatible web template, 
		Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
		
		<script type="application/x-javascript"> 
			addEventListener("load", function() { 
				setTimeout(hideURLbar, 0); 
			}, false); 
			function hideURLbar(){ 
				window.scrollTo(0,1); 
			} 
		</script>
		
		<link href='<c:url value="/resources/css/bootstrap.css"/>' rel='stylesheet' type='text/css' />
		<link href='//fonts.googleapis.com/css?family=Open+Sans:700,700italic,800,300,300italic,400italic,400,600,600italic' rel='stylesheet' type='text/css'>
		
		<!--Custom-Theme-files-->
		<link href='<c:url value="/resources/css/style.css"/>' rel='stylesheet' type='text/css' />	
		<script src='<c:url value="/resources/js/jquery.min.js"/>'> </script>
			
		<!--/script-->
		<script type="text/javascript" src='<c:url value="/resources/js/move-top.js"/>'></script>
		<script type="text/javascript" src='<c:url value="/resources/js/easing.js"/>'></script>		
		
		<link href='<c:url value="/resources/css/edits.css"/>' rel='stylesheet' type='text/css' />
		
		<script type="text/javascript">
					jQuery(document).ready(function($) {
						$(".scroll").click(function(event){		
							event.preventDefault();
							$('html,body').animate({scrollTop:$(this.hash).offset().top},900);
						});
					});
		</script>
		
		<script type="text/javascript" src='<c:url value="/resources/js/search.js"/>'></script>
		
		<layout:block name="head"></layout:block>
		
	</head>


<body>
	<!-- header-section-starts -->
      <div class="h-top" id="home">
		   <div class="top-header">
				  <ul class="cl-effect-16 top-nag">
				  	<c:choose>
						<c:when test="${empty userSession}">
							<li><a href="${pageContext.request.contextPath}/" data-hover="Home">Home</a></li> 
							<li><a href="${pageContext.request.contextPath}/signup" data-hover="Signup">Signup</a></li> 
							<li><a href="${pageContext.request.contextPath}/login" data-hover="Login">Login</a></li>
						</c:when>    
						<c:otherwise>
							<li>
								<a href="${pageContext.request.contextPath}/user/${userSession.userid}" data-hover="${userSession.username}">${userSession.username}</a>
							</li>
							<li><a href="${pageContext.request.contextPath}/" data-hover="Home">Home</a></li> 
							<li><a href="${pageContext.request.contextPath}/user/${userSession.userid}/blog" data-hover="Blog">Blog</a></li>
							<li><a href="${pageContext.request.contextPath}/logout" data-hover="Logout">Logout</a></li>
						</c:otherwise>
					</c:choose>						
					</ul>
					<div class="search-box">
					    <div class="b-search">
							<form method="GET" action="/search">
								<input name="q" id="textSearch" type="text" autocomplete="off" value="Search" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
								<input id="submitSearch" type="submit" value="">
							</form>
							<div id="topSearchResultList" class="incrementalSearchResultList" style="display: none;"></div>								
						</div>
					</div>

					<div class="clearfix"></div>
				</div>
       </div>
	<div class="full">
			<div class="col-md-3 top-nav" style="display:none;">
				    <div class="logo">
						<a href="${pageContext.request.contextPath}/">
							<h1>Blogger</h1>
						</a>
					</div>
					<div class="top-menu">
					 <span class="menu"> </span>

					<ul class="cl-effect-16">
						<li><a class="active" href="index.html" data-hover="HOME">Home</a></li> 
						<li><a href="about.html" data-hover="About">About</a></li>
						<li><a href="grid.html" data-hover="Grids">Grids</a></li>
						<li><a href="services.html" data-hover="Services">Services</a></li>
						<li><a href="gallery.html" data-hover="Gallery">Gallery</a></li>
						<li><a href="contact.html" data-hover="CONTACT">Contact</a></li>
					</ul>
					<!-- script-for-nav -->
					<script>
						$( "span.menu" ).click(function() {
						  $( ".top-menu ul" ).slideToggle(300, function() {
							// Animation complete.
						  });
						});
					</script>
				<!-- script-for-nav --> 	
					<ul class="side-icons">
							<li><a class="fb" href="#"></a></li>
							<li><a class="twitt" href="#"></a></li>
							<li><a class="goog" href="#"></a></li>
							<li><a class="drib" href="#"></a></li>
					   </ul>
			    </div>
			</div>
	<div class="col-md-9 main">
	
		<layout:block name="banner-section"></layout:block>
		<layout:block name="banner-right-text"></layout:block>
		
			<!--//banner-section-->
			
			<div class="clearfix"> </div>
		<!--/footer-->
	     <div class="footer">
				 <div class="footer-top">
				    <div class="col-md-4 footer-grid">
					     <h4>Canecarroarmato </h4>
				          <ul class="bottom">
							 <li>A responsive web application</li>
							 <li>for a social blog website.</li>
						 </ul>
				    </div>
					  <div class="col-md-4 footer-grid">
					     <h4>Message Us Now</h4>
				            <ul class="bottom">
						     <li><i class="glyphicon glyphicon-home"></i>Available 24/7 </li>
							 <li><i class="glyphicon glyphicon-envelope"></i><a href="mailto:brilli.gianluca@gmail.com">brilli.gianluca@gmail.com</a></li>
						   </ul>
				    </div>
					<div class="col-md-4 footer-grid">
					     <h4>Developer Informations</h4>
				           <ul class="bottom">
						     <li><i class="glyphicon glyphicon-map-marker"></i>Casalgrande, Emilia Romagna, Italy </li>
							 <li><i class="glyphicon glyphicon-earphone"></i>phone: (888) 123-456-7899 </li>
						   </ul>
				    </div>
					<div class="clearfix"> </div>
				 </div>
	     </div>
		<div class="copy">
		    <p>&copy; 2016 Blogger. All Rights Reserved | Design by <a href="http://w3layouts.com/">W3layouts</a> </p>
		</div>
	 <div class="clearfix"> </div>
	</div>
	<div class="clearfix"> </div>
</div>	
		<!--//footer-->
			<!--start-smooth-scrolling-->
						<script type="text/javascript">
									$(document).ready(function() {
										/*
										var defaults = {
								  			containerID: 'toTop', // fading element id
											containerHoverID: 'toTopHover', // fading element hover id
											scrollSpeed: 1200,
											easingType: 'linear' 
								 		};
										*/
										
										$().UItoTop({ easingType: 'easeOutQuart' });
										
									});
								</script>
		<a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>


</body>
</html>