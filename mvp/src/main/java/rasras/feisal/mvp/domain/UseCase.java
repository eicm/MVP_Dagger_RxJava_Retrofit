package rasras.feisal.mvp.domain;

import rx.Observable;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public interface UseCase<T> {
    Observable<T> execute();
}