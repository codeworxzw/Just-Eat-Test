package marius.mac.com.raandroid_header.observables;

import marius.mac.com.raandroid_header.model.Example;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Marius on 04/04/2016.
 */
public interface IRestaurant {
    @Headers({
            "Accept-Tenant: uk",
            "Accept-Language: en-GB",
            "Authorization: Basic VGVjaFRlc3RBUEk6dXNlcjI=",
            "Host: public.je-apis.com"
    })
    @GET("/restaurants")
    Observable<Example> getRestraurent(@Query("q") String postcode);
}
