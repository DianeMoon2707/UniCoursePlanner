async function openModal(action, type)
{
	let url = `/modal/${action}?${action}Type=${type}`;
	
	if(action !== "insert")
    {
		const dataElement = document.getElementById("data");
		
		if(!dataElement.value)
		{
			console.log("Ja");
			showErrorMessage("Bitte wählen Sie zuerst einen Datensatz aus.");
			return;
		}
		
		const data = encodeURIComponent(dataElement.value);
		url += `&data=${data}`;
	}
	
	const response = await fetch(url);
	
	if(!response.ok)
	{
		showErrorMessage("Modal konnte nicht geladen werden.");
		return;
	}
	
	const html = await response.text();
	
	document.getElementById("modalContent").innerHTML = html;
	document.getElementById("modalOverlay").classList.remove("hidden");
	
	initializeModalForms();
}

function closeModal()
{
	document.getElementById("modalOverlay").classList.add("hidden");
	document.getElementById("modalContent").innerHTML = "";
}