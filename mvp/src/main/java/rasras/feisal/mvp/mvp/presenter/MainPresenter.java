package rasras.feisal.mvp.mvp.presenter;

import rasras.feisal.mvp.domain.FetchQuestionsUseCase;
import rasras.feisal.mvp.domain.StartTimerUseCase;
import rasras.feisal.mvp.mvp.model.QuestionWrapper;
import rasras.feisal.mvp.mvp.view.MainView;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public class MainPresenter extends Presenter<MainView> {

    private MainView mainView;
    private FetchQuestionsUseCase fetchQuestionsUseCase;
    private StartTimerUseCase startTimerUseCase;

    public MainPresenter(FetchQuestionsUseCase fetchQuestionsUseCase, StartTimerUseCase startTimerUseCase) {
        this.fetchQuestionsUseCase = fetchQuestionsUseCase;
        this.startTimerUseCase = startTimerUseCase;
    }

    @Override
    public void attachView(MainView view) {
        this.mainView = view;
    }

    public void fetchQuestions() {
        mainView.showLoading();
        Subscription fetchQuestionsSubscription = fetchQuestionsUseCase.execute()
                .subscribe(new Subscriber<QuestionWrapper>() {
                    @Override
                    public void onCompleted() {
                        mainView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.hideLoading();
                        mainView.onError(e);
                    }

                    @Override
                    public void onNext(QuestionWrapper questionWrapper) {
                        mainView.hideLoading();
                        mainView.onQuestionsLoaded(questionWrapper.getQuestions());
                    }
                });

        if(compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(fetchQuestionsSubscription);
    }

    public void startTimer(int initialCount, int timerLength) {
        Subscription timerSubscription = startTimerUseCase.execute(initialCount, timerLength)
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {
                        mainView.onTimeEnded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.onError(e);
                    }

                    @Override
                    public void onNext(Long secondsPassed) {
                        mainView.onTick(secondsPassed);
                    }
                });
        if(compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(timerSubscription);
    }

    public void stopTimer() {
        super.onStop();
    }
}
