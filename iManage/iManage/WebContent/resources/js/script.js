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
/*
 * window.onload = ()=>{
 * document.getElementById("profile-tab").addEventListener("click",
 * function(ev){
 * 
 * 
 * toggleVisibility("profile-tab-menu"); window.addEventListener("click",
 * clickout );
 *  } );
 *  };
 * 
 * 
 * function clickout(event){ let thispanel =
 * document.getElementById("profile-tab-menu");
 * 
 * console.log(event.target.parentNode.id);
 * 
 * if(!event.target.parentNode.id == 'profile-tab-menu'){
 * console.log(event.target.parentNode.id); thispanel.style.display = 'none';
 * window.removeEventListener('click',clickout); }else{ console.log("hit"); }
 * 
 * 
 *  }
 */

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

	if (x.style.display === "none" || x.style.display === "") {
		x.style.display = "block";
	} else {
		x.style.display = "none";
	}
}
window.onunload = function() {

	if (sessionStorage.getItem("lastevent") == null) {
		sessionStorage.setItem("lastLoaded", document
				.getElementsByClassName('active')[0].id);
	} else {
		sessionStorage.clear();
	}

};
window.onload = function() {
	console.log(sessionStorage.getItem("lastLoaded"));
	if (sessionStorage.getItem("lastLoaded") != null
			&& sessionStorage.getItem("lastLoaded") != 'edit') {

		document.querySelector(".active").classList.remove("active");
		document.getElementById(sessionStorage.getItem("lastLoaded")).classList
				.add('active');

		var children = document.querySelectorAll("#main-panel > div");
		var active = document.getElementById(sessionStorage
				.getItem("lastLoaded"));
		var index = Array.prototype.indexOf.call(children, active) - 1;

		var elements = document.getElementsByClassName('current');
		while (elements.length > 0) {
			elements[0].classList.remove('current');
		}
		document.querySelectorAll("#menu-options-sidebar li a")[index].classList
				.add("current");
		document.querySelectorAll("#menu-options li a")[index].classList
				.add("current");

	}
}

function clearSession() {
	console.log("JOHNSON");
	sessionStorage.setItem("lastevent", "final");
}