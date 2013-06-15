package org.cloudsdale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.cloudsdale.responses.CloudResponse;
import org.cloudsdale.responses.DropResponse;
import org.cloudsdale.responses.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestHeaders;
import retrofit.RestAdapter;
import retrofit.client.Header;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * The API contract for Cloudsdale, describes all the HTTP actions <br/>
 * Copyright (c) 2013 Cloudsdale.org
 * 
 * @author Berwyn Codeweaver <berwyn.codeweaver@gmail.com>
 */
public interface Api {

	String	BASE_URL	= "http://www.cloudsdale.org/v1";

	/**
	 * Synchronously gets a user by ID
	 * 
	 * @param id
	 *            The user's ID
	 * @return A {@link UserResponse} containing either errors or the user
	 */
	@GET("/users/{id}.json")
	public UserResponse getUser(@Path("id") String id);

	/**
	 * Asynchronously gets a user by ID
	 * 
	 * @param id
	 *            The user's ID
	 * @param callback
	 *            Asynchronous callback to handle response
	 */
	@GET("/users/{id}.json")
	public void getUser(@Path("id") String id, Callback<UserResponse> callback);

	/**
	 * Synchronously gets a cloud by ID
	 * 
	 * @param id
	 *            The cloud's ID
	 * @return A {@link CloudResponse} containing either errors or the user
	 */
	@GET("/clouds/{id}.json")
	public CloudResponse getCloud(@Path("id") String id);

	/**
	 * Asynchronously gets a cloud by ID
	 * 
	 * @param id
	 *            The cloud's ID
	 * @param callback
	 *            Asynchronous callback to handle the response
	 */
	@GET("/clouds/{id}.json")
	public void getCloud(@Path("id") String id, Callback<CloudResponse> callback);

	/**
	 * Synchronously gets drops for a cloud
	 * 
	 * @param id
	 *            The cloud's ID
	 * @return A {@link DropResponse} containing either errors or the list of
	 *         drops
	 */
	@GET("/clouds/{id}/drops.json")
	public DropResponse getDropsForCloud(@Path("id") String id);

	/**
	 * Asynchronously gets drops for a cloud
	 * 
	 * @param id
	 *            The cloud's ID
	 * @param callback
	 *            Asynchronous callback to handle the response
	 */
	@GET("/clouds/{id}/drops.json")
	public void getDropsForCloud(@Path("id") String id,
			Callback<DropResponse> callback);

	public class Builder {

		public Api build(final String internalToken) {
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy/MM/dd HH:mm:ss Z").create();
			RequestHeaders headers = new RequestHeaders() {

                private List<Header> headers = new ArrayList<Header>() {
                    {
                        new Header("X-Auth-Token", internalToken);
                        new Header("Content-Type", "application/json");
                        new Header("Accept", "application/json");
                    }
                };

				@Override
				public List<Header> get() {
					return headers;
				}
			};
			RestAdapter adapter = new RestAdapter.Builder()
					.setConverter(new GsonConverter(gson)).setServer(BASE_URL)
					.setRequestHeaders(headers).build();
			return adapter.create(Api.class);
		}
	}
}
