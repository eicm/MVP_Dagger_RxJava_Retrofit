package rasras.feisal.mvp.repository;

import rasras.feisal.mvp.mvp.model.QuestionWrapper;
import rx.Observable;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public interface ProviderRepository {
    Observable<QuestionWrapper> fetchQuestions();
    Observable<Long> startTimer(int initialCount, int timerLength);
}
