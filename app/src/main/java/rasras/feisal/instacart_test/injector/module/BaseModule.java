package rasras.feisal.instacart_test.injector.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import rasras.feisal.instacart_test.injector.scope.PerApplication;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

@Module
public class BaseModule {

    @Provides
    @PerApplication
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        return gsonBuilder.create();
    }
}
