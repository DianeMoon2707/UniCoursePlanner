function openDay(year, month, day)
{
	console.log("HI");
	console.log(day);
	console.log(month);
	
	const selectedDate = document.getElementById("selectedDate");
	selectedDate.textContent = day + "." + month + "." + year;
	
	const pNotice = document.getElementById("notice-day-schedule");
	pNotice.innerHTML = "";
}