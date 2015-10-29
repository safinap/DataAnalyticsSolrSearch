/*price range*/

$('#sl2').slider();

var RGBChange = function() {
	$('#RGB').css(
			'background',
			'rgb(' + r.getValue() + ',' + g.getValue() + ',' + b.getValue()
					+ ')')
};

/* scroll to top */
$(document).ready(function() {
	$(function() {
		$.scrollUp({
			scrollName : 'scrollUp', // Element ID
			scrollDistance : 300, // Distance from top/bottom before showing
			// element (px)
			scrollFrom : 'top', // 'top' or 'bottom'
			scrollSpeed : 300, // Speed back to top (ms)
			easingType : 'linear', // Scroll to top easing (see
			// http://easings.net/)
			animation : 'fade', // Fade, slide, none
			animationSpeed : 200, // Animation in speed (ms)
			scrollTrigger : false, // Set a custom triggering element. Can be
			// an HTML string or jQuery object
			// scrollTarget: false, // Set a custom target element for scrolling
			// to the top
			scrollText : '<i class="fa fa-angle-up"></i>', // Text for element,
			// can contain HTML
			scrollTitle : false, // Set a custom <a> title if required.
			scrollImg : false, // Set true to use image
			activeOverlay : false, // Set CSS color to display scrollUp active
			// point, e.g '#00FFFF'
			zIndex : 2147483647
		// Z-Index for the overlay
		});
	});
});

$('#btnSubscribe').click(function() {
	subscribe();
});

$('#btnSaveUrl').click(function() {
	if ($('#url').val() == '')
		addUrl();
});

function addUrl() {
	console.log('addUrl');
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL,
		dataType : "json",
		data : formToJSON(),
		success : function(data, textStatus, jqXHR) {
			alert('Url saved successfully');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('addUrl error: ' + textStatus);
		}
	});
}

function subscribe() {
	alert('Clicked '+JSON.stringify({
		email : $('#email').val(),
		keywords : $('#keywords').val()
	}));
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : 'http://localhost:8090/notification/subscribe',
		dataType : "json",
		processData: false,
		data : JSON.stringify({
			email : $('#email').val(),
			keywords : $('#keywords').val()
		}),
		success : function(data, textStatus, jqXHR) {
			alert('Subscribed done: '+textStatus+"---"+jqXHR);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('subscribe error: ' + textStatus + '---' + errorThrown);
		}
	});
}
