package marius.mac.com.raandroid_header.injector.components;


import android.content.SharedPreferences;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import marius.mac.com.raandroid_header.injector.modules.AppModule;
import marius.mac.com.raandroid_header.injector.modules.NetModule;
import retrofit.RestAdapter;

@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    // downstream components need these exposed
    RestAdapter retrofit();
    OkHttpClient okHttpClient();
    SharedPreferences sharedPreferences();
    Picasso getPicasso();
}