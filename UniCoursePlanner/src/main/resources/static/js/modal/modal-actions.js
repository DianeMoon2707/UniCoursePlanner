//Register the submit handler for each modal form
function initializeModalForms(validationFunction)
{
	registerForm("insertForm", "/insert", validationFunction);
	registerForm("editForm", "/edit", validationFunction);
	registerForm("deleteForm", "/delete", validationFunction);
}

//Handle form submission and send the data to the corresponding controller
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
		
		//Validate the form before submitting it
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
				//Close the modal and reload the page after a successful action
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