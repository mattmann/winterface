(function() {

	function assertTrue(value) {
		if (true !== value) {
			throw new Error();
		}
	}
	
	function assertFalse(value) {
		if (false !== value) {
			throw new Error();
		}
	}
	
	function assertEquals(value1, value2) {
		if (value1 != value2) {
			throw new Error();
		}
	}
	
	function assertSame(value1, value2) {
		if (value1 !== value2) {
			throw new Error();
		}
	}
	
	function assertNull(value) {
		if (value != null) {
			throw new Error();
		}
	}
	
	function assertNotNull(value) {
		if (value == null) {
			throw new Error();
		}
	}
	
	window.alphabet = 'abcdefghijklmnopqrstuvwxyz';
	
	assertFalse(closed);
	assertEquals('', defaultStatus);
	assertNotNull(document);
	assertNotNull(frames);
	// assertNotNull(history);
	assertNotNull(innerWidth);
	assertNotNull(innerHeight);
	assertNotNull(length);
	assertNotNull(location);
	// assertNotNull(name);
	// assertNotNull(navigator);
	assertNull(opener);
	assertNotNull(outerWidth);
	assertNotNull(outerHeight);
	//assertNotNull(pageXOffset);
	//assertNotNull(pageYOffset);
	assertNull(parent);
	//assertNotNull(screen);
	//assertNotNull(screenLeft);
	//assertNotNull(screenTop);
	//assertNotNull(screenX);
	//assertNotNull(screenY);
	assertSame(this, self);
	//assertNotNull(status);
	assertSame(this, top);
	
	var text1 = 'Hello World!';
	var text2 = btoa(text1);
	var text3 = atob(text2);
	assertEquals(text1, text3);
	
	assertNotNull(alert);
	assertNotNull(blur);
	assertNotNull(stop);
	
	assertEquals(0, length);
	assertEquals(0, frames.length);
	assertNull(window[0]);
	
	$(function() {
		window.alphabet = window.alphabet.split('').reverse('').join('').toUpperCase();
	});

	return 0;
})();