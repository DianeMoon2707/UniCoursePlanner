const POPUP_OPTIONS = "width=800,height=700";

function openInsertPopup()
{
	window.open(
		"/popup/insert?insertType=MODUL", 
		"_blank", 
		POPUP_OPTIONS
	);
}

function openEditPopup()
{
	const rowData = encodeURIComponent(document.getElementById("rowData").value);
	window.open(
		"/popup/edit?editType=MODUL&rowData=" + rowData,
		"_blank",
		POPUP_OPTIONS
	);
}

function openDeletePopup()
{
	const rowData = encodeURIComponent(document.getElementById("rowData").value);
	window.open(
		"/popup/delete?deleteType=MODUL&rowData=" + rowData,
		"_blank",
		POPUP_OPTIONS
	);
}