function rowClicked(row)
{
	//Table-marking
	document.querySelectorAll(".content tr.selected").forEach(tr => tr.classList.remove("selected"));
	row.classList.add("selected");	
		
	const rowData = Array.from(row.cells).map(cell => cell.innerText.trim());
	
	//Row-Data saving	
	document.getElementById("rowData").value = JSON.stringify(rowData);
}