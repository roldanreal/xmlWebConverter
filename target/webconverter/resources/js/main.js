/**
 * This Javascript file will be used in all pages
 */
$(function() {
	"use strict";
	
	// initialize tooltip
	$('[data-toggle="tooltip"]').tooltip();
	
	//bind showing loading gif on ajax start
	$(document).ajaxStart(function() {
		$.blockUI({ 
			message: "<img src='resources/img/circular-load.GIF'>",
			baseZ: 2000,
			css: {
		        border: 'none',
		        backgroundColor: 'transparent'
		    }
		});
	});
	$(document).ajaxStop(function() {
		$.unblockUI();
	});
	
	//reset modal form data on data-dismiss
	$('[data-dismiss=modal]').click(function (e) {
	    var $t = $(this),
	        target = $t[0].href || $t.data("target") || $t.parents('.modal') || [];

	  $(target)
	    .find("input,textarea,select")
	       .val('')
	       .end()
	    .find("input[type=checkbox], input[type=radio]")
	       .prop("checked", "")
	       .end()
	     .find("div")
	     	.removeClass("has-error")
	     	.end()
	     .find(".error")
	     	.empty()
	     	.end();
	});
	
	//listen when the link is clicked, clear all the cookies
	$("#logoutLink").click(function() {
		//alert('im clicked');
		Cookies.set('username', '');
		Cookies.set('usertype', '');
		//Cookies.set('username', '');
		//redirect to login page
		window.location.replace('login');
	});
});