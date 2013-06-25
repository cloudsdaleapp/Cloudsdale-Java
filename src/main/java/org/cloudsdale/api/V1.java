package org.cloudsdale.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.cloudsdale.responses.v1.CloudResponse;
import org.cloudsdale.responses.v1.DropResponse;
import org.cloudsdale.responses.v1.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RequestHeaders;
import retrofit.RestAdapter;
import retrofit.client.Header;
import retrofit.converter.GsonConverter;
import retrofit.http.*;
import retrofit.mime.TypedFile;

/**
 * The API contract for the Cloudsdale.org API v1, describes all the HTTP actions <br/>
 * Copyright (c) 2013 Cloudsdale.org
 *
 * @author Berwyn Codeweaver <berwyn.codeweaver@gmail.com>
 * @see <a href="https://dev.cloudsdale.org/">Developer Documentation</a>
 */
@Deprecated
public interface V1 {

    String BASE_URL = "https://www.cloudsdale.org/v1";

    //region >> SESSION RESOURCES

    // FIXME Create sessions

    // FIXME Renew sessions

    //endregion

    //region >> USER RESOURCES

    /**
     * Synchronously gets a user by ID
     *
     * @param id The user's ID
     * @return A {@link UserResponse} containing either errors or the user
     */
    @GET("/users/{id}.json")
    public UserResponse getUser(@Path("id") String id);

    /**
     * Asynchronously gets a user by ID
     *
     * @param id       The user's ID
     * @param callback Asynchronous callback to handle response
     */
    @GET("/users/{id}.json")
    public void getUser(@Path("id") String id, Callback<UserResponse> callback);

    /**
     * Synchronously gets a user's clouds
     *
     * @param id The user's ID
     * @return A {@link CloudResponse} containing either errors or the user's clouds
     */
    @GET("/users/{id}/clouds.json")
    public CloudResponse getCloudsForUser(@Path("id") String id);

    /**
     * Asynchronously gets a user's clouds
     *
     * @param id       The user's ID
     * @param callback Asynchronous callback to handle response
     */
    @GET("/users/{id}/clouds.json")
    public void getCloudsForUser(@Path("id") String id, Callback<CloudResponse> callback);

    @Multipart
    @POST("/users/{id}.json")
    public UserResponse uploadAvatarForUser(@Path("id")String id, @Part("user[avatar]")TypedFile avatar);

    @Multipart
    @POST("/users/{id}.json")
    public void uploadAvatarForUser(@Path("id")String id, @Part("user[avatar]")TypedFile avatar, Callback<UserResponse> callback);

    //endregion

    //region >> CLOUD RESOURCES

    /**
     * Synchronously gets a cloud by ID
     *
     * @param id The cloud's ID
     * @return A {@link CloudResponse} containing either errors or the user
     */
    @GET("/clouds/{id}.json")
    public CloudResponse getCloud(@Path("id") String id);

    /**
     * Asynchronously gets a cloud by ID
     *
     * @param id       The cloud's ID
     * @param callback Asynchronous callback to handle the response
     */
    @GET("/clouds/{id}.json")
    public void getCloud(@Path("id") String id, Callback<CloudResponse> callback);

    //endregion

    //region >> DROP RESOURCES

    /**
     * Synchronously gets drops for a cloud
     *
     * @param id The cloud's ID
     * @return A {@link DropResponse} containing either errors or the list of
     *         drops
     */
    @GET("/clouds/{id}/drops.json")
    public DropResponse getDropsForCloud(@Path("id") String id);

    /**
     * Asynchronously gets drops for a cloud
     *
     * @param id       The cloud's ID
     * @param callback Asynchronous callback to handle the response
     */
    @GET("/clouds/{id}/drops.json")
    public void getDropsForCloud(@Path("id") String id,
                                 Callback<DropResponse> callback);

    //endregion

    public class Builder {

        public V1 build(final String internalToken) {
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
            return new RestAdapter.Builder()//
                    .setConverter(new GsonConverter(gson))//
                    .setServer(BASE_URL)//
                    .setRequestHeaders(headers)//
                    .build()//
                    .create(V1.class);
        }
    }
}
