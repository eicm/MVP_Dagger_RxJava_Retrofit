package rasras.feisal.mvp.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by feisal on 1/28/17.
 * All rights reserved.
 */

public class Question implements Parcelable {

    private String Question;
    private String[] urls;
    private String correctAnswer;
    private String givenAnswer;

    public Question(String question) {
        Question = question;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] input) {
        this.urls = input;
        if(urls.length > 0) {
            correctAnswer = urls[0];
        }
        Random rnd = new Random();
        for (int i = urls.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String tempUrl = urls[index];
            urls[index] = urls[i];
            urls[i] = tempUrl;
        }
    }


    public String getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public boolean isAlreadyAnswered() {
        return givenAnswer != null;
    }
    public boolean isAnsweredCorrectly() {
        return correctAnswer != null && givenAnswer != null &&
                correctAnswer.equalsIgnoreCase(getGivenAnswer());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Question);
        dest.writeStringArray(this.urls);
        dest.writeString(this.correctAnswer);
        dest.writeString(this.givenAnswer);
    }

    public Question(Parcel in) {
        this.Question = in.readString();
        this.urls = in.createStringArray();
        this.correctAnswer = in.readString();
        this.givenAnswer = in.readString();
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
