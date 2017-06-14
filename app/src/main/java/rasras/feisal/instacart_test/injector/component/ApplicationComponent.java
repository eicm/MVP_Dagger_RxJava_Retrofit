package rasras.feisal.instacart_test.injector.component;

import android.app.Application;

import dagger.Component;
import rasras.feisal.instacart_test.MyApplication;
import rasras.feisal.instacart_test.injector.module.ApplicationModule;
import rasras.feisal.instacart_test.injector.module.BaseModule;
import rasras.feisal.instacart_test.injector.module.MainActivityModule;
import rasras.feisal.instacart_test.injector.scope.PerApplication;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

@PerApplication
@Component(modules = {ApplicationModule.class, BaseModule.class})
public interface ApplicationComponent {

    Application application();
    MyApplication myApplication();

    MainActivityComponent plus(MainActivityModule module);
}
