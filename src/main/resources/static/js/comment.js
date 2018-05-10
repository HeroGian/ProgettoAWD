$(document).ready(function() {
	
	$('input[name=commentBtn]').click(function(e) {
		
		e.preventDefault();
		
		$('.error').remove();
		$('textarea[id=commentTextArea]').css('border', '1px solid #ddd');
						
		var pathList = window.location.pathname.split('/');
		
		$.ajax({
			type: 'POST',
			url : window.location.origin + "/new-comment",
			data : {
				"body": $('textarea[id=commentTextArea]').val(),
				"postid": pathList[pathList.length - 1]
			},
			success : function(res) {
																			
				if(res.valid){
					$('textarea[id=commentTextArea]').val('');
															
					var username = res.success.username;
					var body	 = res.success.body;
					var pubdate  = res.success.pubdate;
					var avatar   = res.success.avatar;
					var commntid = res.success.commid;
					var userid   = res.success.userid;
										
					var newPost = '<div class="media response-info" id="' + commntid + '">' +
				      			      '<div class="media-left response-text-left">' +
				      			          '<a href="' + window.location.origin + '/user/' + userid + '">' +
				      			              '<img class="media-object" src="' + window.location.origin + avatar + '" height="80" width="80" />' + 
				      			          '</a>' +
				      			          '<h5><a href="' + window.location.origin + '/user/' + userid + '">'+ username + '</a></h5>' +
				      			      '</div>' +
				      			      '<div class="media-body response-text-right">' +
				      			          '<p>' + body + '</p>' +
				      			          '<ul>' +
				      			              '<li>' + pubdate + '</li>' + 
				      			              '<li><a href="#">Reply</a></li>' +
				      			          '</ul>' +
				      			      '</div>' +
				      			      '<div class="clearfix"></div>' +
				      			  '</div>';
					
					$('h4[class=responses]').after(newPost);
					$('div[id=' + commntid + ']').css('background-color', '#e9e3f4');
					$(window).scrollTop($('#'+ commntid).offset().top);
					
					setTimeout(function(){
						$('div[id=' + commntid + ']').css('background-color', 'white');
					}, 2000);
				}
				else{
					$('textarea[id=commentTextArea]').after('<span class="error">' + res.error + '</span>');
					$('textarea[id=commentTextArea]').css('border', '1px solid red');
				}
			},
			error: function(res, error) {
				console.log(JSON.stringify(res));
			}
		});
	}); 
});