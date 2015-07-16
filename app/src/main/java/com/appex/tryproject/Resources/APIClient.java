package com.appex.tryproject.resources;

import com.appex.tryproject.model.instagram.InstaFeed;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Developer: appex_corp
 * Package : com.appex.networkmanager.network
 * Project : Network Manager
 * Date : 7/7/15
 */

public class APIClient {
    //private static DataInterface dataInterface = null;
    private static InstaFeedInterface instaFeedInterface=null;

    public static InstaFeedInterface getInstagram() {
        if (instaFeedInterface == null) {
            String URL_INSTA = "https://api.instagram.com";
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(URL_INSTA)
                    .build();

            instaFeedInterface = restAdapter.create(InstaFeedInterface.class);
        }
        return instaFeedInterface;
    }

    public interface InstaFeedInterface {
        @GET("/v1/tags/techtatva15/media/recent?client_id=fd6b3100174e42d7aa7d546574e01c76")
        void getFeed(Callback<InstaFeed> instaFeedCallback);
    }
}