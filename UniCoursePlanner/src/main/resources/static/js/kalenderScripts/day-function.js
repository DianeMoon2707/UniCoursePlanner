//Fenster zu Tagesplan öffnen/zeigen
async function openDay(year, month, day)
{	
	const calendarView = document.querySelector(".calender-view");
	const dayPlan = document.querySelector(".day-schedule");
	
	const selectedDate = document.getElementById("selectedDate");
	const selectedDateValue = document.getElementById("selectedDateValue");
	
	const newHeadline = day + "." + (month + 1) + "." + year;
	const isoDate = year + "-" + String(month + 1).padStart(2, "0") + "-" +String(day).padStart(2, "0");
	
	
	// Wenn derselbe Tag erneut angeklickt wird, Tagesplan schließen
	if(selectedDate.innerHTML === newHeadline)
	{
		calendarView.classList.remove("day-selected");
		selectedDate.textContent = "";
		selectedDate.textContentValue = "";
		dayPlan.classList.remove("visible");	
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

//Tagesplan-Daten anzeigen
async function loadEvents(date)
{
	const response = await fetch(`/kalender/events?date=${date}`);
	if(!response.ok)
	{
		console.error("Kalendereinträge konnten nicht geladen werden.");
		clearEvents();
		return;
	}
	
	const events = await response.json();
	displayEvents(events);
}

//Event-Tabelle laden
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
			row.onclick = () => rowClicked(row);
			eventList.appendChild(row);
		}
	);
}

//Event-Tabelle leeren
function clearEvents()
{
	document.getElementById("eventList").innerHTML = "";
}