//Validate that at least one checkbox has been selected
function validateAtLeastOneCheckboxChecked()
{
	const checkboxList = document.querySelectorAll("input[type='checkbox']");
	let oneChecked = false;
	
	for(const checkbox of checkboxList)
	{
		if(checkbox.checked === true)
		{
			oneChecked = true;
			console.log("Check");
			break;
		}
		console.log(checkbox.textContent);
	}
	
	if(oneChecked === false)
	{
		console.log("Hi");
		showErrorMessage("Bitte mind. eine Veranstaltungsform auswählen.");
		return false;
	}
	
	return true;
}