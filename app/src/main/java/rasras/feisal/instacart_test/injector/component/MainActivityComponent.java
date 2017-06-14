package rasras.feisal.instacart_test.injector.component;

import dagger.Subcomponent;
import rasras.feisal.instacart_test.activity.MainActivity;
import rasras.feisal.instacart_test.injector.module.MainActivityModule;
import rasras.feisal.instacart_test.injector.scope.PerActivity;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

@PerActivity
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
