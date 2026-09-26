async function openCalendarModal(action)
{
    await openModal(action, "CALENDAR");
    transmitDate();
}

function transmitDate()
{
	const chosenDate = document.getElementById("selectedDateValue");
	const dateField = document.getElementById("dateDTO");
	
	dateField.value = chosenDate.textContent;
}