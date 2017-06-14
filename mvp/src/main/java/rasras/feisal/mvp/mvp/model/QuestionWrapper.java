package rasras.feisal.mvp.mvp.model;

import java.util.ArrayList;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public class QuestionWrapper {

    private Question question;
    private ArrayList<Question> questions;

    /**
     *
     * @return
     * The question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     *
     * @param question
     * The question
     */
    public void setQuestion(Question question) {
        this.question = question;
    }

    /**
     * @return The questions
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * @param questions The questions
     */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
