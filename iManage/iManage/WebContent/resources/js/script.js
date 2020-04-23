var prev;

function toggleSubMenu(id) {
	console.log(prev);
	if (id == undefined) {
		document.querySelector(".active").classList.remove("active");
		document.getElementById(prev).classList.add("active");
	} else {
		prev = document.getElementsByClassName('active')[0].id;
		document.querySelector(".active").classList.remove("active");
		document.getElementById(id).classList.add("active");
	}

	return false;
}

function toggleMenus(e, id) {
	var elements = document.getElementsByClassName('current');
	while (elements.length > 0) {
		elements[0].classList.remove('current');
	}

	if (document.getElementById('menu-options').contains(e)) {
		var children = document.querySelectorAll("#menu-options li a");
		var index = Array.prototype.indexOf.call(children, e);
		var sidebarMenu = document
				.querySelectorAll("#menu-options-sidebar li a");
		sidebarMenu[index].classList.add("current");

	} else {
		var children = document.querySelectorAll("#menu-options-sidebar li a");
		var index = Array.prototype.indexOf.call(children, e);
		var sideMenu = document.querySelectorAll("#menu-options li a");
		sideMenu[index].classList.add("current");
	}

	document.querySelector(".active").classList.remove("active");
	document.getElementById(id).classList.add("active");

	e.classList.add("current");

	return false;
}

function toggleVisibility(id) {
	var x = document.getElementById(id);

	
	if (x.style.display === "none" ||x.style.display === "") {
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
}