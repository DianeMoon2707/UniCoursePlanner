const POPUP_OPTIONS = "width=800,height=700";

function openPopup(action, type) 
{
	let url = `/popup/${action}?${action}Type=${type}`;
	
	if(action !== "insert") 
	{
		const rowData = encodeURIComponent(document.getElementById("rowData").value);
		url += `&rowData=${rowData}`;
    }
	
	window.open(url, "_blank", POPUP_OPTIONS);
}