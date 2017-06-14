package rasras.feisal.instacart_test.activity;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rasras.feisal.instacart_test.R;
import rasras.feisal.instacart_test.injector.component.ApplicationComponent;
import rasras.feisal.instacart_test.injector.component.MainActivityComponent;
import rasras.feisal.instacart_test.injector.module.MainActivityModule;
import rasras.feisal.mvp.mvp.model.Question;
import rasras.feisal.mvp.mvp.presenter.MainPresenter;
import rasras.feisal.mvp.mvp.view.MainView;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainView {

    private final static String PARCEL_KEY = "parcel_key";
    private final static String LAST_TIME_KEY = "last_time_key";
    private final static String TIMER_COUNT_KEY = "timer_count_key";
    private final static String QUESTION_INDEX_KEY = "index_key";

    private final static int TIMER_LENGTH = 120;

    private final static int STATE_IDLE = 0;
    private final static int STATE_STARTED = 1;
    private final static int STATE_FINISHED = 2;

    @BindView(R.id.button_start)
    Button startButton;

    @BindView(R.id.button_submit)
    Button submitButton;

    @BindView(R.id.imageView_1)
    ImageView firstImageView;

    @BindView(R.id.imageView_2)
    ImageView secondImageView;

    @BindView(R.id.imageView_3)
    ImageView thirdImageView;

    @BindView(R.id.imageView_4)
    ImageView forthImageView;

    @BindView(R.id.textView_timer)
    TextView timerTextView;

    @BindView(R.id.textView_question)
    TextView questionTextView;

    @Inject
    MainPresenter mainPresenter;

    @Inject
    RequestManager requestManager;


    private int index = 0;
    private int timerCount = 0;
    private long lastUsedTime;
    private ImageView selectedImageView;
    private ProgressDialog progressDialog;
    private ArrayList<Question> questions;
    private MainActivityComponent mainActivityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(QUESTION_INDEX_KEY);
            questions = savedInstanceState.getParcelableArrayList(PARCEL_KEY);
            lastUsedTime = savedInstanceState.getLong(LAST_TIME_KEY);
            timerCount = savedInstanceState.getInt(TIMER_COUNT_KEY);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.onStart();
        if (questions == null) {
            updateUI(STATE_IDLE);
        } else {
            updateUI(STATE_STARTED);
            long passedTimeMs = System.currentTimeMillis() - lastUsedTime;
            int secondsPassed = timerCount + (int) (passedTimeMs / 1000);
            onTick(secondsPassed);
            mainPresenter.startTimer(secondsPassed, TIMER_LENGTH);
            showNext();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        hideLoading();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityComponent = null;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        lastUsedTime = System.currentTimeMillis();
        outState.putParcelableArrayList(PARCEL_KEY, questions);
        outState.putLong(LAST_TIME_KEY, lastUsedTime);
        outState.putInt(TIMER_COUNT_KEY, timerCount);
        outState.putInt(QUESTION_INDEX_KEY, index);
    }

    @Override
    protected void injectComponent(ApplicationComponent component) {
        mainActivityComponent = component.plus(new MainActivityModule(this));
        mainActivityComponent.inject(this);
    }

    @Override
    protected void initializePresenter() {
        mainPresenter.attachView(this);
    }

    @OnClick(R.id.button_start)
    public void onStartClicked() {
        index = 0;
        mainPresenter.fetchQuestions();
        updateUI(STATE_STARTED);
    }

    @OnClick(R.id.button_submit)
    public void onSubmitClicked() {
        if (questions.get(index).isAlreadyAnswered()) {
            index++;
            showNext();
        } else {
            Toast.makeText(this, getString(R.string.pleas_make_selection_first), Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.imageView_1)
    public void onFirstImageViewClicked() {
        if (firstImageView != selectedImageView) {
            if (selectedImageView != null) {
                selectedImageView.clearColorFilter();
            }
            firstImageView.setColorFilter(ContextCompat.getColor(this, R.color.black_shade_30), PorterDuff.Mode.SRC_ATOP);
            selectedImageView = firstImageView;
            questions.get(index).setGivenAnswer(questions.get(index).getUrls()[0]);
        }
    }

    @OnClick(R.id.imageView_2)
    public void onSecondImageViewClicked() {
        if (secondImageView != selectedImageView) {
            if (selectedImageView != null) {
                selectedImageView.clearColorFilter();
            }
            secondImageView.setColorFilter(ContextCompat.getColor(this, R.color.black_shade_30), PorterDuff.Mode.SRC_ATOP);
            selectedImageView = secondImageView;
            questions.get(index).setGivenAnswer(questions.get(index).getUrls()[1]);
        }
    }

    @OnClick(R.id.imageView_3)
    public void onThirdImageViewClicked() {
        if (thirdImageView != selectedImageView) {
            if (selectedImageView != null) {
                selectedImageView.clearColorFilter();
            }
            thirdImageView.setColorFilter(ContextCompat.getColor(this, R.color.black_shade_30), PorterDuff.Mode.SRC_ATOP);
            selectedImageView = thirdImageView;
            questions.get(index).setGivenAnswer(questions.get(index).getUrls()[2]);
        }
    }

    @OnClick(R.id.imageView_4)
    public void onForthImageViewClicked() {
        if (forthImageView != selectedImageView) {
            if (selectedImageView != null) {
                selectedImageView.clearColorFilter();
            }
            forthImageView.setColorFilter(ContextCompat.getColor(this, R.color.black_shade_30), PorterDuff.Mode.SRC_ATOP);
            selectedImageView = forthImageView;
            questions.get(index).setGivenAnswer(questions.get(index).getUrls()[3]);
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = ProgressDialog.show(this, null, getString(R.string.loading_), true, true);
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onTimeEnded() {
        showResult();
        Timber.d("onTimeEnded");
    }

    @Override
    public void onTick(long secondsPassed) {
        timerCount = (int) secondsPassed;
        updateTimerUI((int) secondsPassed);
    }

    private void showNext() {
        if (selectedImageView != null) {
            selectedImageView.clearColorFilter();
            selectedImageView = null;
        }
        if (index < questions.size()) {
            Question question = questions.get(index);
            questionTextView.setText(question.getQuestion());
            requestManager.load(question.getUrls()[0]).centerCrop().error(R.drawable.placeholder).into(firstImageView);
            requestManager.load(question.getUrls()[1]).centerCrop().error(R.drawable.placeholder).into(secondImageView);
            requestManager.load(question.getUrls()[2]).centerCrop().error(R.drawable.placeholder).into(thirdImageView);
            requestManager.load(question.getUrls()[3]).centerCrop().error(R.drawable.placeholder).into(forthImageView);
            checkSelection(question);
        } else {
            mainPresenter.stopTimer();
            showResult();
        }
    }

    private void checkSelection(Question question) {
        if (question.isAlreadyAnswered()) {
            for(int i = 0; i < question.getUrls().length; i++) {
                if(question.getUrls()[i].equals(question.getGivenAnswer())) {
                    selectImage(i);
                    break;
                }
            }
        }
    }

    private void selectImage(int i) {
        switch (i) {
            case 0:
                firstImageView.setColorFilter(ContextCompat.getColor(this, R.color.black_shade_30), PorterDuff.Mode.SRC_ATOP);
                selectedImageView = firstImageView;
                break;
            case 1:
                secondImageView.setColorFilter(ContextCompat.getColor(this, R.color.black_shade_30), PorterDuff.Mode.SRC_ATOP);
                selectedImageView = secondImageView;
                break;
            case 2:
                thirdImageView.setColorFilter(ContextCompat.getColor(this, R.color.black_shade_30), PorterDuff.Mode.SRC_ATOP);
                selectedImageView = thirdImageView;
                break;
            case 3:
                forthImageView.setColorFilter(ContextCompat.getColor(this, R.color.black_shade_30), PorterDuff.Mode.SRC_ATOP);
                selectedImageView = forthImageView;
                break;
        }
    }

    private void showResult() {
        updateUI(STATE_FINISHED);
        int result = 0;
        for (Question question : questions) {
            if (question.isAnsweredCorrectly()) {
                result++;
            }
        }
        questionTextView.setText("Result: " + result + "/" + questions.size());
    }

    private void updateTimerUI(int totalSeconds) {
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        timerTextView.setText(String.format(Locale.ENGLISH, "%02d:%02d", minutes, seconds));
    }

    @Override
    public void onQuestionsLoaded(ArrayList<Question> questions) {
        this.questions = questions;
        showNext();
        mainPresenter.startTimer(0, TIMER_LENGTH);
    }

    private void updateUI(int state) {
        switch (state) {
            case STATE_IDLE:
                startButton.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.GONE);
                firstImageView.setVisibility(View.GONE);
                secondImageView.setVisibility(View.GONE);
                thirdImageView.setVisibility(View.GONE);
                forthImageView.setVisibility(View.GONE);
                timerTextView.setVisibility(View.GONE);
                questionTextView.setVisibility(View.GONE);
                break;
            case STATE_STARTED:
                startButton.setVisibility(View.GONE);
                submitButton.setVisibility(View.VISIBLE);
                firstImageView.setVisibility(View.VISIBLE);
                secondImageView.setVisibility(View.VISIBLE);
                thirdImageView.setVisibility(View.VISIBLE);
                forthImageView.setVisibility(View.VISIBLE);
                timerTextView.setVisibility(View.VISIBLE);
                questionTextView.setVisibility(View.VISIBLE);
                break;
            case STATE_FINISHED:
                startButton.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.GONE);
                firstImageView.setVisibility(View.GONE);
                secondImageView.setVisibility(View.GONE);
                thirdImageView.setVisibility(View.GONE);
                forthImageView.setVisibility(View.GONE);
                timerTextView.setVisibility(View.VISIBLE);
                questionTextView.setVisibility(View.VISIBLE);
                break;
        }
    }
}
