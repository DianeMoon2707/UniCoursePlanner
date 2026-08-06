function initializeModalForms()
{
	registerForm("insertForm", "/insert");
	registerForm("editForm", "/edit");
	registerForm("deleteForm", "/delete");
}

function registerForm(formId, url)
{
	const form = document.getElementById(formId);
	
	if(!form)
	{
		return;
	}
	
	form.addEventListener("submit", function(e)
	{
		e.preventDefault();
		
		const formData = new FormData(form);
		
		fetch(url,
        {
			method: "POST",
			body: new URLSearchParams(formData)
		})
		.then(response =>
		{
			if(response.ok)
			{
				closeModal();
				location.reload();
			}
			else
			{
				alert("Fehler beim Speichern.");
			}
		})
		.catch(error =>
		{
			console.error(error);
			alert("Serverfehler.");
		});
	});
}