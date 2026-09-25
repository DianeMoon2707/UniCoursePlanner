function rowClicked(row)
{
	//Table-marking
	document.querySelectorAll(".content tr.selected").forEach(tr => tr.classList.remove("selected"));
	row.classList.add("selected");	
		
	//Row-Data ID-Attribut saving
	const data = row.cells[0].innerText.trim();
	document.getElementById("data").value = data;
}