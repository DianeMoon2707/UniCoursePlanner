async function openModal(action, type)
{
	let url = `/modal/${action}?${action}Type=${type}`;
	
	if(action !== "insert")
    {
		const rowData = encodeURIComponent(document.getElementById("rowData").value);
		url += `&rowData=${rowData}`;
	}
	
	const response = await fetch(url);
	
	if(!response.ok)
	{
		alert("Modal konnte nicht geladen werden.");
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