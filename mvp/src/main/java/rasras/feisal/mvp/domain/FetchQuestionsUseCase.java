package rasras.feisal.mvp.domain;

import rasras.feisal.mvp.mvp.model.QuestionWrapper;
import rasras.feisal.mvp.repository.ProviderRepository;
import rx.Observable;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public class FetchQuestionsUseCase implements UseCase<QuestionWrapper> {

    private ProviderRepository repository;

    public FetchQuestionsUseCase(ProviderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<QuestionWrapper> execute() {
        return repository.fetchQuestions();
    }
}
