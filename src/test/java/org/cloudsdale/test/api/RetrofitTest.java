package org.cloudsdale.test.api;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import junit.framework.TestCase;

import org.cloudsdale.Cloudsdale;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import retrofit.Profiler;
import retrofit.RestAdapter;
import retrofit.client.Client;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA. User: Berwyn Codeweaver Date: 14/06/13 Time:
 * 22:55 To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class RetrofitTest extends TestCase {

    // TODO FIXME Mock out the client and test!

	private static final String	AUTH_TOKEN				= "SOME SUPER SECRET AWESOME KEY";
	private static final String	DEBUG_USER_ID			= "awesome user id";
	private static final String	DEBUG_CLOUD_SHORTNAME	= "awesome cloud id";

	private Cloudsdale cloudsale;
	private Client				mockClient;
	private Profiler<Object>	mockProfiler;

	@Before
	public void setup() {
		mockClient = mock(Client.class);
		mockProfiler = mock(Profiler.class);
		cloudsale = new RestAdapter.Builder().setClient(mockClient)
				.setProfiler(mockProfiler)
				.setServer("https://www.cloudsdale.org").build()
				.create(Cloudsdale.class);
	}

	@Test
	public void testUserGet() throws IOException {
	}

	@Test
	public void testCloudGet() {
	}

	public void testCloudDropGet() {
	}

}
