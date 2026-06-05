function openDeletePopup()
{
	const rowData = encodeURIComponent(document.getElementById("rowData").value);
	window.open(
		"/popup/delete?deleteType=MODUL&rowData=" + rowData,
		"_blank",
		"width=500,height=400"
	);
}