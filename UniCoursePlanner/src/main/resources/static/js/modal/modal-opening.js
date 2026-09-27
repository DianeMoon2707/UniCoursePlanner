//Load and display a modal for the specified action and type
async function openModal(action, type, validationFunction = null)
{
	let url = `/modal/${action}?${action}Type=${type}`;
	
	//Load the selected dataset for edit and delete actions
	if(action !== "insert")
    {
		const dataElement = document.getElementById("data");
		
		//Show an error if no dataset has been selected
		if(!dataElement.value)
		{
			showErrorMessage("Bitte wähle zuerst einen Datensatz aus.");
			return;
		}
		
		const data = encodeURIComponent(dataElement.value);
		url += `&data=${data}`;
	}
	
	//Load the modal content from the backend
	const response = await fetch(url);
	
	if(!response.ok)
	{
		showErrorMessage("Modal konnte nicht geladen werden.");
		return;
	}
	
	const html = await response.text();
	
	//Display the modal with its content
	document.getElementById("modalContent").innerHTML = html;
	document.getElementById("modalOverlay").classList.remove("hidden");
	
	initializeModalForms(validationFunction);
}

//Close the modal and clear its content
function closeModal()
{
	document.getElementById("modalOverlay").classList.add("hidden");
	document.getElementById("modalContent").innerHTML = "";
}