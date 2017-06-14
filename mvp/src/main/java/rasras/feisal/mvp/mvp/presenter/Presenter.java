package rasras.feisal.mvp.mvp.presenter;

import rasras.feisal.mvp.mvp.view.View;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public abstract class Presenter<T extends View> {
    CompositeSubscription compositeSubscription;

    public void onStart() {
        compositeSubscription = new CompositeSubscription();
    }

    public void onStop() {
        if (!compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
        compositeSubscription = null;
    }

    public abstract void attachView(T view);
}
