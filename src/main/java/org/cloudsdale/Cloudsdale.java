package org.cloudsdale;

import java.util.ArrayList;
import java.util.List;

import org.cloudsdale.models.Cloud;
import org.cloudsdale.models.User;
import org.cloudsdale.responses.CloudResponse;
import org.cloudsdale.responses.DropResponse;
import org.cloudsdale.responses.SessionResponse;
import org.cloudsdale.responses.UserResponse;

import retrofit.Callback;
import retrofit.RequestHeaders;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The API contract for Cloudsdale, describes all the HTTP actions <br/>
 * Copyright (c) 2013 Cloudsdale.org
 * 
 * @author Berwyn Codeweaver <berwyn.codeweaver@gmail.com>
 */
public interface Cloudsdale {

    String BASE_URL = "http://www.cloudsdale.org/v1";

    /**
     * Asynchronously establishes a session with the server
     * 
     * @param email
     *            The user's email address, as registered with Cloudsdale.org
     * @param password
     *            The user's password
     * @param callback
     *            The callback to be executed on completion of the request
     */
    @FormUrlEncoded
    @POST("/sessions.json")
    public void postSession(@Field("email") String email,
            @Field("password") String password,
            Callback<SessionResponse> callback);

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
     * Updates a user
     * 
     * @param id
     *            The ID of the user to update
     * @param user
     *            The user entity used to update the API
     */
    @PUT("/users/{id}.json")
    public void updateUser(@Path("id") String id, @Body User user);

    /**
     * Asynchronously creates the user
     * 
     * @param user
     *            The user to create
     */
    @POST("/users.json")
    public void createUser(@Body User user);

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
     * Asynchronously fetch the ten most popular clouds
     * 
     * @param callback
     *            The callback executed on completion of the request
     */
    @GET("/clouds/popular.json")
    public void getPopularClouds(Callback<CloudResponse> callback);

    /**
     * Asynchronously fetch the ten most popular clouds
     * 
     * @param offset
     *            The number of clouds to offset the result by
     * @param callback
     *            The callback executed on completion of the request
     */
    @GET("/clouds/popular.json")
    public void getPopularClouds(@Header("X_RESULT_PAGE") int offset,
            Callback<CloudResponse> callback);

    /**
     * Fetches the most recently created clouds
     * 
     * @param callback
     *            The callback executed on completion of the request
     */
    @GET("/clouds/recent.json")
    public void getRecentClouds(Callback<CloudResponse> callback);

    /**
     * Fetches the most recently created clouds
     * 
     * @param offset
     *            The number of clouds to offset the result by
     * @param callback
     *            The callback executed on completion of the request
     */
    @GET("/clouds/recent.json")
    public void getRecentClouds(@Header("X_RESULT_PAGE") int offset,
            Callback<CloudResponse> callback);

    /**
     * Searches clouds based on their names and descriptions
     * 
     * @param query
     *            The query string to search by
     * @param callback
     *            The callback executed on completion of the request
     */
    @FormUrlEncoded
    @GET("/clouds/search.json")
    public void searchClouds(@Field("q") String query,
            Callback<CloudResponse> callback);

    /**
     * Searches clouds based on their names and descriptions
     * 
     * @param query
     *            The query string to search by
     * @param offset
     *            The number of clouds to offset the result by
     * @param callback
     *            The callback executed on completion of the request
     */
    @FormUrlEncoded
    @GET("/clouds/search.json")
    public void searchClouds(@Field("q") String query,
            @Header("X_RESULT_PAGE") int offset,
            Callback<CloudResponse> callback);

    /**
     * Asynchronusly updates a cloud
     * 
     * @param id
     *            The ID of the cloud to update
     * @param cloud
     *            The cloud object used to update the API model
     * @param callback
     *            The callback to execute upon completion of the request
     */
    @PUT("/clouds/{id}.json")
    public void updateCloud(@Path("id") String id, @Body Cloud cloud,
            Callback<CloudResponse> callback);

    /**
     * Creates a cloud
     * 
     * @param cloud
     *            The cloud to create
     * @param callback
     *            The callback to execute on completion of the request
     */
    @POST("/clouds.json")
    public void createCloud(@Body Cloud cloud, Callback<CloudResponse> callback);

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

        public Cloudsdale build(final String internalToken) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy/MM/dd HH:mm:ss Z").create();
            RequestHeaders headers = new RequestHeaders() {

                private List<retrofit.client.Header> headers = new ArrayList<retrofit.client.Header>() {
                                                                 {
                                                                     new retrofit.client.Header(
                                                                             "X-Auth-Token",
                                                                             internalToken);
                                                                     new retrofit.client.Header(
                                                                             "Content-Type",
                                                                             "application/json");
                                                                     new retrofit.client.Header(
                                                                             "Accept",
                                                                             "application/json");
                                                                 }
                                                             };

                @Override
                public List<retrofit.client.Header> get() {
                    return headers;
                }
            };
            RestAdapter adapter = new RestAdapter.Builder()
                    .setConverter(new GsonConverter(gson)).setServer(BASE_URL)
                    .setRequestHeaders(headers).build();
            return adapter.create(Cloudsdale.class);
        }
    }
}
