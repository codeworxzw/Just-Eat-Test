package marius.mac.com.raandroid_header;

import android.app.Application;

import marius.mac.com.raandroid_header.constants.Constants;
import marius.mac.com.raandroid_header.injector.components.APIComponents;
import marius.mac.com.raandroid_header.injector.components.DaggerAPIComponents;
import marius.mac.com.raandroid_header.injector.components.DaggerNetComponent;
import marius.mac.com.raandroid_header.injector.components.NetComponent;
import marius.mac.com.raandroid_header.injector.modules.APIModule;
import marius.mac.com.raandroid_header.injector.modules.AppModule;
import marius.mac.com.raandroid_header.injector.modules.NetModule;


/**
 * Created by Marius on 04/04/2016.
 */
public class MyApp extends Application {
   private NetComponent mNetComponent;
   private APIComponents mApiComponents;
    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent= DaggerNetComponent.builder()
                .netModule(new NetModule(Constants.BASE_URL))
                .appModule(new AppModule(this))
                .build();

        mApiComponents= DaggerAPIComponents.builder()
                .netComponent(mNetComponent)
                .aPIModule(new APIModule())
                .build();


    }
    public NetComponent getNetComponent() {
        return mNetComponent;
    }

    public APIComponents getApiComponent() {
        return mApiComponents;
    }
}
