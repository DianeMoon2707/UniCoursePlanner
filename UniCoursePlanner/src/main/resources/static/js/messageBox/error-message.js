document.addEventListener('DOMContentLoaded', function() {
	
    document.querySelectorAll('.error').forEach(errorDiv => 
	{
		
        if(errorDiv.textContent.trim() !== '') 
		{
            Swal.fire({
                icon: 'error',
                title: 'Fehler',
                text: errorDiv.textContent
            }).then(() => { 
				errorDiv.textContent = ''; 
			});
        }
    });
});