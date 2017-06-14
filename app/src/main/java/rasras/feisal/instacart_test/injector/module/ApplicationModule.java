package rasras.feisal.instacart_test.injector.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;
import rasras.feisal.instacart_test.MyApplication;
import rasras.feisal.instacart_test.injector.scope.PerApplication;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

@Module
public class ApplicationModule {
    private final MyApplication application;

    public ApplicationModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @PerApplication
    public MyApplication provideMyApplication() {
        return application;
    }

    @Provides
    @PerApplication
    public Application provideApplication() {
        return application;
    }
}
