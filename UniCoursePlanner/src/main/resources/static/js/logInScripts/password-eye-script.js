//Toggle the password visibility and update the eye button symbol
function changeInputTypeAndSymbol()
{
	const button = document.getElementById("password-eye-button");
	const field = document.getElementById("password-field");
	
	//Hide the password if it is currently visible
	if(field.type === "text")
	{
		field.type = "password";
		button.innerHTML = '<i class="bi bi-eye"></i>';
	}
	else 
	{
		field.type = "text";
		button.innerHTML = '<i class="bi bi-eye-slash"></i>';
	}
}