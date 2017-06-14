package rasras.feisal.mvp.domain;

import rasras.feisal.mvp.repository.ProviderRepository;
import rx.Observable;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public class StartTimerUseCase implements UseCase<Long> {

    private ProviderRepository repository;

    public StartTimerUseCase(ProviderRepository repository) {
        this.repository = repository;
    }

    public Observable<Long> execute(int initialCount, int timerLength) {
        return repository.startTimer(initialCount, timerLength);
    }

    @Override
    public Observable<Long> execute() {
        return null;
    }
}
