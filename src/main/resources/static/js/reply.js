$(document).ready(function() {
	
	$('.reply-link').click(function(){
		
		var idx = $(this).attr('id');
							
	    $('#reply-form-' + idx).toggle();
	    
	    $(this).text( 
	    		$(this).text() == 'Reply' ? 'Close' : 'Reply' 
	    );
	});
	
	$('input[name=replyBtn]').click(function(e) {
		
		e.preventDefault();
		
		var pathList = window.location.pathname.split('/');
		var IdList   = $(this).attr('id').split('-');
		var formIdx  = IdList[IdList.length - 1];
		
		$('.error').remove();
		$('textarea[id=ReplyTxtArea-' + formIdx + ']').css('border', '1px solid #ddd');
				
		$.ajax({
			type: 'POST',
			url : window.location.origin + "/new-reply",
			data : {
				"postid"	: pathList[pathList.length - 1],
				"body"  	: $('textarea[id=ReplyTxtArea-' + formIdx + ']')
								.val(),
				"commentid" : $('#reply-form-' + formIdx)
								.parent()
								.parent()
								.attr('id')
			},
			success : function(res) {
				
				if(res.valid){
					
					$('textarea[id=ReplyTxtArea-' + formIdx + ']').val('');
					
					$('div[id=' + res.success.parentId + ']').find('.response-text-right form').before(
							
							'<div class="media response-info" id="reply-' + res.success.replyid + '">' +
								'<div class="media-left response-text-left">' +
									'<a href="' + window.location.origin + '/user/' + res.success.userid + '">' +
										'<img class="media-object" src="' + window.location.origin + res.success.avatar + '"' + ' height="80" width="80" />' +
									'</a>' +
									'<h5>' +
										'<a href="' + window.location.origin + '/user/' + res.success.userid + '">' + res.success.username + '</a>' +
									'</h5>' +
								'</div>' +
								'<div class="media-body response-text-right">' +
									'<p>' + res.success.body +'</p>' +
									'<ul>' +
										'<li>' + res.success.pubdate +'</li>' +
									'</ul>' +
								'</div>' +
								'<div class="clearfix"> </div>' +
							'</div>'
					);
					
					$('div[id=reply-' + res.success.replyid + ']').css('background-color', '#e9e3f4');
					$(window).scrollTop($('#reply-'+ res.success.replyid).offset().top);
					
					setTimeout(function(){
						$('div[id=reply-' + res.success.replyid + ']').css('background-color', 'white');
					}, 2000);
					
				}
				else{
					$('textarea[id=ReplyTxtArea-' + formIdx + ']').after('<span class="error">' + res.error + '</span>');
					$('textarea[id=ReplyTxtArea-' + formIdx + ']').css('border', '1px solid red');
				}
				
				console.log(JSON.stringify(res));
			},
			error: function(res, error) {
				console.log(JSON.stringify(res));
			}
		});
	});
});