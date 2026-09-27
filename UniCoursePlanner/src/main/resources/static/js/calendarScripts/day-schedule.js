//Open the day schedule for the selected date
async function openDay(year, month, day)
{	
	const calendarView = document.querySelector(".calendar-view");
	const dayPlan = document.querySelector(".day-schedule");
	
	const selectedDate = document.getElementById("selectedDate");
	const selectedDateValue = document.getElementById("selectedDateValue");
	
	const newHeadline = day + "." + (month + 1) + "." + year;
	const isoDate = year + "-" + String(month + 1).padStart(2, "0") + "-" +String(day).padStart(2, "0");
	
	//Close the day schedule if the same day is clicked again
	if(selectedDate.innerHTML === newHeadline)
	{
		calendarView.classList.remove("day-selected");
		selectedDate.textContent = "";
		selectedDateValue.textContent = "";
		dayPlan.classList.remove("visible");
		
		clearEvents();
	}
	else
	{
		calendarView.classList.add("day-selected");
		selectedDate.textContent = newHeadline;
		selectedDateValue.textContent = isoDate;
		dayPlan.classList.add("visible");
		
		await loadEvents(isoDate);
	}
}

//Load all saved events for the selected date from the backend
async function loadEvents(date)
{
	const response = await fetch(`/calendar/events?date=${date}`);
	if(!response.ok)
	{
		console.error("Kalendereinträge konnten nicht geladen werden.");
		clearEvents();
		return;
	}
	
	const events = await response.json();
	displayEvents(events);
}

//Create the event table and display the events for the selected date
function displayEvents(events)
{
	const eventList = document.getElementById("eventList");
	eventList.innerHTML = "";
	
	events.forEach(event => 
	{
		const row = document.createElement("tr");
		
		const id = document.createElement("td");
		id.hidden = true;
		id.textContent = event.id;
		
		const time = document.createElement("td");
		time.textContent = event.time;
		
		const topic = document.createElement("td");
		topic.textContent = event.topic;
		
		const extension = document.createElement("td");
		extension.textContent = event.extension;
		
		row.append(id, time, topic, extension);
		
		//Allow the user to manipulate a specific event by clicking its row
		row.onclick = () => rowClicked(row);
		
		eventList.appendChild(row);
	});
}

//Clear all events from the event table
function clearEvents()
{
	document.getElementById("eventList").innerHTML = "";
}