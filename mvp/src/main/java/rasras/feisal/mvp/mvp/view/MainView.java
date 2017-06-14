package rasras.feisal.mvp.mvp.view;

import java.util.ArrayList;

import rasras.feisal.mvp.mvp.model.Question;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public interface MainView extends View {
    void showLoading();
    void hideLoading();
    void onTimeEnded();
    void onTick(long secondsPassed);
    void onQuestionsLoaded(ArrayList<Question> questions);
}
