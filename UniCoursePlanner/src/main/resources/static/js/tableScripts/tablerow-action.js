function rowClicked(row)
{
	//Mark the one selected table row
	document.querySelectorAll(".content tr.selected").forEach(tr => tr.classList.remove("selected"));
	row.classList.add("selected");	
		
	//Store the row's identifier in the hidden input
	const data = row.cells[0].innerText.trim();
	document.getElementById("data").value = data;
}