const date = new Date();

const kalender = document.getElementById("days");
const monthElement = document.getElementById("month");

const months = ['Januar','Februar','März','April','Mai','Juni','Juli','August','September','Oktober','November','Dezember'];

let currentMonth = date.getMonth();
let currentYear = date.getFullYear();

let daysWithEvents = [];
loadDaysWithEvents(currentYear, currentMonth);

//Event-Tage des Monats vom Backend laden
async function loadDaysWithEvents(year, month)
{
	const response = await fetch(`/kalender/dates?year=${year}&month=${month + 1}`);
	if(!response.ok)
	{
		console.error("Event-Tage konnten nicht geladen werden.");
		daysWithEvents = [];
	}
	else
	{
		daysWithEvents = await response.json();
	}
	
	createCalender(year, month);
}

//Aktuellen Monat zeigen
function showMonth()
{
	monthElement.innerHTML = months[currentMonth] + " " + currentYear;
}

//Kalenderblatt erstellen
function createCalender(year, month)
{
	showMonth();	
	
	let amountDays = 0;
	
	amountDays += createPreviousMonthDays(year, month);
	amountDays += createCurrentMonthDays(year, month);
	createNextMonthDays(amountDays);
}

//Erstelle Tage vom vorherigen Monat
function createPreviousMonthDays(year, month)
{
	const firstDayOfMonth = new Date(currentYear, currentMonth, 1);
	const weekday = firstDayOfMonth.getDay();
	const daysBefore = weekday === 0 ? 6 : weekday - 1;

	const lastDayOfLastMonth = new Date(currentYear, currentMonth, 0);
	
	const prevMonth = lastDayOfLastMonth.getMonth();
	const prevYear = lastDayOfLastMonth.getFullYear();
	
	for(let i = daysBefore; i > 0; i--)
	{
		createDay(lastDayOfLastMonth.getDate() - i + 1, prevMonth, prevYear, "other-month");
	}
	
	return daysBefore;
}

//Tage vom aktuellen Monat erstellen
function createCurrentMonthDays(year, month)
{
	const amountDays = daysInMonth(year, month);
		
	for(let i = 1; i <= amountDays; i++)
	{
		//Aktuellen Tag und sonstige Tage erstellen
		if(date.getDate() === i && month === date.getMonth() && year === date.getFullYear())
		{
			createDay(i, month, year, "today");
		}
		else
		{
			createDay(i, month, year);
		}
	}
	
	return amountDays;
}

//Wochenreihe beenden
function createNextMonthDays(amountDays)
{
	let day = 1;
	
	while(amountDays % 7 !== 0)
	{
		createDay(day, currentMonth + 1, currentYear, "other-month");
		
		day++;
		amountDays++;
	}
}

//Hilfsmethode zur Erstellung eines Tages
function createDay(number, month, year, additionalClass = "")
{
	const day = document.createElement("div");
	day.classList.add("raster-cell");
	
	if(additionalClass)
	{
		day.classList.add(additionalClass);
	}
	
	//Tag markieren, wenn Events vorhanden
	const currentDay = `${year}-${String(month + 1).padStart(2, "0")}-${String(number).padStart(2, "0")}`;
	if(daysWithEvents.includes(currentDay))
	{
		day.classList.add("has-events");
	}
	
	day.textContent = number;
	
	day.onclick = function()
	{
		openDay(year, month, number);
	}
	
	kalender.appendChild(day);
}

//Tage eines bestimmten Monats ermitteln
function daysInMonth(year, month)
{
	return new Date(year, month + 1, 0).getDate();
}

//Monat ändern
function previousMonth()
{
	kalender.innerHTML = "";
	
	currentMonth--;
	
	if(currentMonth < 0)
	{
		currentMonth = 11;
		currentYear--;
	}
	
	loadDaysWithEvents(currentYear, currentMonth);
}

function nextMonth()
{
	kalender.innerHTML = "";
	
	currentMonth++;
	
	if(currentMonth > 11)
	{
		currentMonth = 0;
		currentYear++;
	}
	
	loadDaysWithEvents(currentYear, currentMonth);
}