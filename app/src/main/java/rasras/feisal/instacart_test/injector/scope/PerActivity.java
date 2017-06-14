package rasras.feisal.instacart_test.injector.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
