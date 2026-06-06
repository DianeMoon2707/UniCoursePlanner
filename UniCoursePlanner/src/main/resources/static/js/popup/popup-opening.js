function openDeletePopup()
{
	const rowData = encodeURIComponent(document.getElementById("rowData").value);
	window.open(
		"/popup/delete?deleteType=MODUL&rowData=" + rowData,
		"_blank",
		"width=600,height=500"
	);
}

function openEditPopup()
{
	const rowData = encodeURIComponent(document.getElementById("rowData").value);
	window.open(
		"/popup/edit?editType=MODUL&rowData=" + rowData,
		"_blank",
		"width=600,height=500"
	);
}