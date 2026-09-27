//Open the calendar modal for the specified action
async function openCalendarModal(action)
{
    await openModal(action, "CALENDAR");
    transmitDate();
}

//Set the date selected by the user in the modal field 
function transmitDate()
{
	const chosenDate = document.getElementById("selectedDateValue");
	const dateField = document.getElementById("dateDTO");
	
	dateField.value = chosenDate.textContent;
}