package rasras.feisal.instacart_test;

import android.app.Application;
import android.content.Context;

import rasras.feisal.instacart_test.injector.component.ApplicationComponent;
import rasras.feisal.instacart_test.injector.component.DaggerApplicationComponent;
import rasras.feisal.instacart_test.injector.module.ApplicationModule;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupInjector();
    }



    private void setupInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                //.networkModule(new NetworkModule(BuildConfig.apiEndpointUrl))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
