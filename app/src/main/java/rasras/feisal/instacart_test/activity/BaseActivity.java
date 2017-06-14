package rasras.feisal.instacart_test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import rasras.feisal.instacart_test.MyApplication;
import rasras.feisal.instacart_test.injector.component.ApplicationComponent;
import rasras.feisal.mvp.mvp.view.View;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public abstract class BaseActivity extends AppCompatActivity implements View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectComponent(MyApplication.get(this).getApplicationComponent());
        initializePresenter();
    }

    protected abstract void injectComponent(ApplicationComponent component);

    protected abstract void initializePresenter();

    @Override
    public void onError(Throwable e) {
        //TODO: show error dialog
    }
}
