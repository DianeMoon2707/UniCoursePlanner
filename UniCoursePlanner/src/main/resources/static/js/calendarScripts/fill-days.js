const date = new Date();

const calendar = document.getElementById("days");
const monthElement = document.getElementById("month");

const months = ['Januar','Februar','März','April','Mai','Juni','Juli','August','September','Oktober','November','Dezember'];

let currentMonth = date.getMonth();
let currentYear = date.getFullYear();

let daysWithEvents = [];
loadDaysWithEvents(currentYear, currentMonth);

//Load all days of the current month that have events
async function loadDaysWithEvents(year, month)
{
	const response = await fetch(`/calendar/dates?year=${year}&month=${month + 1}`);
	if(!response.ok)
	{
		console.error("Event-Tage konnten nicht geladen werden.");
		daysWithEvents = [];
	}
	else
	{
		daysWithEvents = await response.json();
	}
	
	//then: create the calendar page
	createCalendar(year, month);
}

//Display the name of the current month and the year
function showMonth(year, month)
{
	monthElement.innerHTML = months[month] + " " + year;
}

/**
 * Creates the calendar page for a specific month and year.
 * Adds the days of the previous month if necessary to avoid empty cells,
 * followed by the days of the current month and the next month.
 */
function createCalendar(year, month)
{
	calendar.innerHTML = "";
	
	showMonth(year, month);	
	
	let amountDays = 0;
	
	amountDays += createPreviousMonthDays(year, month);
	amountDays += createCurrentMonthDays(year, month);
	createNextMonthDays(amountDays, month, year);
}

/**
 * Creates the remaining days of the previous month
 * if the first day of the current month is not a Monday.
 */ 
function createPreviousMonthDays(year, month)
{
	const firstDayOfMonth = new Date(year, month, 1);
	const weekday = firstDayOfMonth.getDay();
	const daysBefore = weekday === 0 ? 6 : weekday - 1;

	const lastDayOfLastMonth = new Date(year, month, 0);
	
	const prevMonth = lastDayOfLastMonth.getMonth();
	const prevYear = lastDayOfLastMonth.getFullYear();
	
	for(let i = daysBefore; i > 0; i--)
	{
		createDay(lastDayOfLastMonth.getDate() - i + 1, prevMonth, prevYear, "other-month");
	}
	
	return daysBefore;
}

//Create all days of the current Month
function createCurrentMonthDays(year, month)
{
	const amountDays = daysInMonth(year, month);
		
	for(let i = 1; i <= amountDays; i++)
	{
		//Mark the current day
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

/**
 * Creates the days of the next month needed to complete 
 * the final row of the calendar.
 */
function createNextMonthDays(amountDays, month, year)
{
	let day = 1;
	
	while(amountDays % 7 !== 0)
	{
		createDay(day, month + 1, year, "other-month");
		
		day++;
		amountDays++;
	}
}

//Create a single calendar day
function createDay(number, month, year, additionalClass = "")
{
	const day = document.createElement("div");
	day.classList.add("raster-cell");
	
	//Add a class for days of other months or the current day
	if(additionalClass)
	{
		day.classList.add(additionalClass);
	}
	
	//Mark the day if it has events
	const currentDay = `${year}-${String(month + 1).padStart(2, "0")}-${String(number).padStart(2, "0")}`;
	if(daysWithEvents.includes(currentDay))
	{
		day.classList.add("has-events");
	}
	
	day.textContent = number;
	
	//Open the day schedule when the user clicks on a day
	day.onclick = function()
	{
		openDay(year, month, number);
	}
	
	calendar.appendChild(day);
}

//Calculate the number of days in a specific month
function daysInMonth(year, month)
{
	return new Date(year, month + 1, 0).getDate();
}

//Load the calendar page of the previous month 
function previousMonth()
{	
	currentMonth--;
	
	if(currentMonth < 0)
	{
		currentMonth = 11;
		currentYear--;
	}
	
	loadDaysWithEvents(currentYear, currentMonth);
}

//Load the calendar page of the next month 
function nextMonth()
{	
	currentMonth++;
	
	if(currentMonth > 11)
	{
		currentMonth = 0;
		currentYear++;
	}
	
	loadDaysWithEvents(currentYear, currentMonth);
}