package rasras.feisal.instacart_test.injector.module;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import rasras.feisal.instacart_test.activity.MainActivity;
import rasras.feisal.instacart_test.injector.scope.PerActivity;
import rasras.feisal.instacart_test.repository.ProviderRepositoryImpl;
import rasras.feisal.mvp.domain.FetchQuestionsUseCase;
import rasras.feisal.mvp.domain.StartTimerUseCase;
import rasras.feisal.mvp.mvp.presenter.MainPresenter;
import rasras.feisal.mvp.repository.ProviderRepository;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

@Module
public class MainActivityModule {

    private MainActivity activity;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    ProviderRepository provideProviderRepository(Gson gson) {
        return new ProviderRepositoryImpl(activity, gson);
    }

    @Provides
    @PerActivity
    FetchQuestionsUseCase provideFetchQuestionsUseCase(ProviderRepository repository) {
     return new FetchQuestionsUseCase(repository);
    }

    @Provides
    @PerActivity
    StartTimerUseCase provideStartTimerUseCase(ProviderRepository repository) {
        return new StartTimerUseCase(repository);
    }

    @Provides
    @PerActivity
    MainPresenter provideMainPresenter(FetchQuestionsUseCase fetchQuestionsUseCase, StartTimerUseCase startTimerUseCase) {
        return new MainPresenter(fetchQuestionsUseCase, startTimerUseCase);
    }

    @Provides
    @PerActivity
    public RequestManager provideGlideRequestManager() {
        return Glide.with(activity);
    }
}
