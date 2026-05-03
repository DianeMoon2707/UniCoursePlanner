function changeInputTypeAndSymbol(){
	const button = document.getElementById("password-eye-button");
	const field = document.getElementById("password-field");
	
	if(field.type === "text"){
		field.type = "password";
		button.innerHTML = '<i class="bi bi-eye"></i>';
	}
	else {
		field.type = "text";
		button.innerHTML = '<i class="bi bi-eye-slash"></i>';
	}
}