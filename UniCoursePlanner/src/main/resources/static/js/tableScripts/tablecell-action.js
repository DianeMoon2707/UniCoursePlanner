function entryClicked(entry)
{
	//Entry-marking and remove marking
	const entrys = document.querySelectorAll(".raster-entry");
	entrys.forEach(e => e.classList.remove("active"));
	entry.classList.add("active");
		
	//Entry-data saving
	const data = [
		entry.innerText.trim(),
		entry.dataset.weekday,
		entry.dataset.time
	];
	
	document.getElementById("data").value = JSON.stringify(data);
}