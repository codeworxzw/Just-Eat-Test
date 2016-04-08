package marius.mac.com.raandroid_header.injector.components;

import dagger.Component;
import marius.mac.com.raandroid_header.injector.modules.APIModule;
import marius.mac.com.raandroid_header.injector.scope.UserScope;
import marius.mac.com.raandroid_header.presenters.RestaurantPresenter;


/**
 * Created by Marius on 04/04/2016.
 */

@UserScope
@Component(dependencies = NetComponent.class, modules = APIModule.class)
public interface APIComponents {

    void inject(RestaurantPresenter restaurantPresenter);

}
