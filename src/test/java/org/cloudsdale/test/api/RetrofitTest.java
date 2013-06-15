package org.cloudsdale.test.api;

import junit.framework.TestCase;

import org.cloudsdale.Api;
import org.cloudsdale.models.Cloud;
import org.cloudsdale.models.User;
import org.cloudsdale.responses.CloudResponse;
import org.cloudsdale.responses.UserResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Created with IntelliJ IDEA. User: Berwyn Codeweaver Date: 14/06/13 Time:
 * 22:55 To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class RetrofitTest extends TestCase {

	private static final String	DEBUG_USER_ID			= "4f2ad7371f5f9d3ab7004476";
	private static final String	DEBUG_CLOUD_SHORTNAME	= "android";

	private Api					cloudsale;

	@Before
	public void setup() {
		cloudsale = new Api.Builder().build();
	}

	@Test
	public void testUserGet() {
		UserResponse res = cloudsale.getUser(DEBUG_USER_ID);
		User ber = res.getResult();

		assertEquals(DEBUG_USER_ID, ber.getId());
		assertEquals("berwyn", ber.getUsername());
	}

	@Test
	public void testCloudGet() {
		CloudResponse res = cloudsale.getCloud(DEBUG_CLOUD_SHORTNAME);
		Cloud android = res.getResult();

		assertEquals(DEBUG_CLOUD_SHORTNAME, android.getShortName());
		assertEquals("Android App", android.getName());
	}

}
