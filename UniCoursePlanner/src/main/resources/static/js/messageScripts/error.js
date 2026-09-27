//Display an error message in a message box
function showErrorMessage(message) 
{
	Swal.fire({
		title: "Fehler",
		text: message,
		icon: "error"
	});
}

//Display the message box if a message was provided in the HTML
document.addEventListener("DOMContentLoaded", function () 
{
	const message = document.getElementById("message-box");
	
	if(!message)
	{
		return;
	}
	
	showErrorMessage(message.dataset.message);
});