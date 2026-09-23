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
	
	form.addEventListener("submit", async function(e)
	{
		e.preventDefault();
		
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