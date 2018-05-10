$(document).ready(function() {
	/*  Submit form using Ajax */
	$('input[name=signupBtn]').click(function(e) {
				   		   			   
		//Prevent default submission of form
		e.preventDefault();
		
		//Remove all errors
		$('.error').remove();
		$('input[class=signup]').css('border', '1px solid #333');
						     				      			      
		$.ajax({
			type: 'POST',
			url : window.location.origin + '/signup',
			data : $('form[name=signupForm]').serialize(),
			success : function(res) {
								         
				if(res.validated){
					//Set response
					$('#resultContainer pre code').text(JSON.stringify(res.employee));
					$('#resultContainer').show();
					
					setTimeout(function(){
						window.location.href = window.location.origin
					}, 2000);
					            
				}else{
					
					console.log(JSON.stringify(res));
					
					//Set error messages
					$.each(res.errorMessages,function(key,value){
						$('input[name='+key+']').after('<span class="error">'+value+'</span>');
						$('input[name='+key+']').css('border', '1px solid red');
					});
				}
			}
		})
	});
});