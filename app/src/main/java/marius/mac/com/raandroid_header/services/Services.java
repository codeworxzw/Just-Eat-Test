package marius.mac.com.raandroid_header.services;

import marius.mac.com.raandroid_header.constants.Constants;
import marius.mac.com.raandroid_header.observables.IRestaurant;
import marius.mac.com.raandroid_header.utilities.APIErrorHandler;
import retrofit.RestAdapter;

/**
 * Created by Marius on 04/04/2016.
 */
public class Services {
    public static IRestaurant _createRestruentshubApi() {

        RestAdapter.Builder builder= new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(new APIErrorHandler());


        return builder.build().create(IRestaurant.class);
    }
}
