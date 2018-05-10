$(document).ready(function() {
	
	//
	// Apre e chiude il form di modifica del profilo utente
	//
	$('a[id=editProfile]').click(function(e) {
					
		var descr = $('#labelDescrStandard').text();
				    
		console.log(descr);
										
		$('#submitAvatar') .toggle();
		$('#choseAvatar')  .toggle();				    				    				    
				    
		$(this).text( 
				$(this).text() == 'Edit Profile' ? 'Close' : 'Edit Profile' 
		);
	});
	
	//
	// Permette la modifica del profilo utente
	//
	$('input[id=submitAvatar]').click(function(e){
		
		e.preventDefault();
		
		var form  = $('#fileUploadForm')[0];
	    
	    console.log(window.location.pathname);
		
	    $.ajax({
	    	type: "POST",
	        enctype: 'multipart/form-data',
	        url: window.location.pathname,
	        data: new FormData(form),
	        processData: false,
	        contentType: false,
	        success: function (res) {
	        	
	        	if(res.validated){
	        		
	        		console.log(JSON.stringify(res));
		        	
		        	$('#imgAvatar').attr('src', window.location.origin + res.success.avatarPath);
		        	
		        	if(res.success.avatarResized){
		        		alert('Image has been resized');
		        	}
	        	}
	        	else{
	        		alert(res.error.fileType);   		
	        	}
	        }
	    });
		
	});
	
});