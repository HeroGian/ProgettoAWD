$(document).ready(function() {
	
	function searchGet(){
						
		$.ajax({
			type: 'GET',
			url : window.location.origin + "/search-asynch",
			data : {
				q: $('input[id=textSearch]').val()
			},
			success : function(res) {
								
				$('#topSearchResultList').empty();
				$('#topSearchResultList').show();
				
				console.log(JSON.stringify(res));
																		
				$.each(res.post, function(key, value){
					
					var title	= value.title;
					var pubdate = value.pubdate;
					var userid  = value.userid;
					var postid  = value.postid;
					var image   = value.img;
					
					var path	= window.location.origin + "/user/" + userid + "/blog/post/" + postid;
					
					var searchElement = '<div class="searchElement">' +
											'<div class="searchImg">' +
												'<a href="' + path + '">' +
													'<img src="' + image + '" class="img-responsive" alt="">' +
												'</a>' +
											'</div>' +
											'<div class="searchText">' +
												'<a href="' + path + '">' + title +'</a>' +
												'<br>' +
												'<span>' + pubdate +'</span>' +
											'</div>' +
										'</div>' +
										'<br>';
					
					$('div[id=topSearchResultList]').append(searchElement);					
				});
			},
			error: function(res, error) {
				console.log(JSON.stringify(res));
			}
		});
	}
	
	$(document).click(function(e) {
		if(!$(event.target).closest('#topSearchResultList').length) {
	        if($('#topSearchResultList').is(":visible")) {
	            $('#topSearchResultList').hide();
	        }
	    }	
	});
	
	$('#textSearch').keyup(function() {
		searchGet();
	});
});