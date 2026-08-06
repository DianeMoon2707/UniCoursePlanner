function rowClicked(row)
{
	//Table-marking
	document.querySelectorAll(".content tr.selected").forEach(tr => tr.classList.remove("selected"));
	row.classList.add("selected");	
		
	//Row-Data saving
	const data = Array.from(row.cells).map(cell => cell.innerText.trim());
	document.getElementById("data").value = JSON.stringify(data);
}