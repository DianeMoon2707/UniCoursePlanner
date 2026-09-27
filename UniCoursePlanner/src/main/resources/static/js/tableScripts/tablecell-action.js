function entryClicked(entry)
{
	//Mark the selected timetable entry
	const entries = document.querySelectorAll(".raster-entry");
	entries.forEach(e => e.classList.remove("active"));
	entry.classList.add("active");
		
	//Store the entry data in the hidden input
	const data = [
		entry.innerText.trim(),
		entry.dataset.weekday,
		entry.dataset.time
	];
	
	document.getElementById("data").value = JSON.stringify(data);
}