
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
	if (!value1.equals(value2)) {
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