function initializeModalForms(validationFunction)
{
	registerForm("insertForm", "/insert", validationFunction);
	registerForm("editForm", "/edit", validationFunction);
	registerForm("deleteForm", "/delete", validationFunction);
}

function registerForm(formId, url, validationFunction = null)
{
	const form = document.getElementById(formId);
	
	if(!form)
	{
		return;
	}
	
	form.addEventListener("submit", async function(e)
	{
		e.preventDefault();
		
		if(validationFunction && !validationFunction())
		{
			return;
		}
		
		const formData = new FormData(form);
		
		try
		{
			const response = await fetch(url,
			{
				method: "POST",
				body: new URLSearchParams(formData)
			});
			
			if(response.ok)
			{
				closeModal();
				window.location.reload();
			}
			else
			{
				const message = await response.text();
				showErrorMessage(message);
			}
		}
		catch(error)
		{
			console.error(error);
			alert("Serverfehler.");
		}
	});
}