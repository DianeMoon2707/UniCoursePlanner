function showErrorMessage(message) 
{
	Swal.fire({
		title: "Fehler",
		text: message,
		icon: "error"
	});
}

document.addEventListener("DOMContentLoaded", function () 
{
	const message = document.getElementById("message-box");
		
	if(!message)
	{
		return;
	}
	
	showErrorMessage(message.dataset.message);
});