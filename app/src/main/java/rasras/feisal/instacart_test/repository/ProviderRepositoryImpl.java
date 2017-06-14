package rasras.feisal.instacart_test.repository;

import android.content.Context;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import rasras.feisal.instacart_test.utils.HelperMethods;
import rasras.feisal.mvp.mvp.model.Question;
import rasras.feisal.mvp.mvp.model.QuestionWrapper;
import rasras.feisal.mvp.repository.ProviderRepository;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public class ProviderRepositoryImpl implements ProviderRepository {

    private Context context;
    private Gson gson;

    public ProviderRepositoryImpl(Context context, Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    private static final String FILE_NAME = "questions.json";

    @Override
    public Observable<QuestionWrapper> fetchQuestions() {
        return Observable.create(new Observable.OnSubscribe<QuestionWrapper>() {
            @Override
            public void call(Subscriber<? super QuestionWrapper> subscriber) {
                try {
                    ArrayList<Question> questions = new ArrayList<>();
                    JSONObject jsonObject = HelperMethods.readJsonFromAssets(context, FILE_NAME);
                    Iterator keys = jsonObject.keys();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        Question question = new Question(key);
                        question.setUrls(gson.fromJson(jsonObject.get(key).toString(), String[].class));
                        questions.add(question);
                    }
                    Collections.shuffle(questions);
                    QuestionWrapper questionWrapper = new QuestionWrapper();
                    questionWrapper.setQuestions(questions);
                    subscriber.onNext(questionWrapper);
                } catch (JSONException e) {
                    subscriber.onError(e);
                    Timber.e(e.getMessage());
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Long> startTimer(final int initialCount, final int timerLength) {
        return Observable.interval(1, TimeUnit.SECONDS)
                .filter(new Func1<Long, Boolean>() {
                    @Override
                    public Boolean call(Long aLong) {
                        return initialCount <= timerLength;
                    }
                })
                .take(((timerLength - initialCount) + 1) > 0 ? ((timerLength - initialCount) + 1) : 0)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long tick) {
                        return tick + initialCount;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
