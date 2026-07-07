async function openModal(action, type)
{
	let url = `/modal/${action}?${action}Type=${type}`;
	
	if(action !== "insert")
    {
		const data = encodeURIComponent(document.getElementById("data").value);
		url += `&data=${data}`;
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