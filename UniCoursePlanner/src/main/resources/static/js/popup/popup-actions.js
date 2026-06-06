function submitPopupForm(formId, url) 
{
	const form = document.getElementById(formId);
	
	if(!form) return;
	
	form.addEventListener("submit", function (e) {
		e.preventDefault();
		
		const formData = new FormData(form);
		
		fetch(url, {
			method: "POST",
			body: new URLSearchParams(formData)
		})
		.then(response => {
			if(response.ok) 
			{
				window.close();
				window.opener.location.reload();
			} 
			else 
			{
				alert("Fehler beim Schließen");
			}
		})
		.catch(err => {
			console.error(err);
			alert("Serverfehler");
		});
	});
}