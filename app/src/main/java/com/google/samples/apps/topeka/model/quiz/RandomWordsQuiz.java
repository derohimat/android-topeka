package com.google.samples.apps.topeka.model.quiz;

import android.annotation.SuppressLint;
import android.os.Parcel;

@SuppressLint("ParcelCreator")
public final class RandomWordsQuiz extends Quiz<String> {

    private String[] mRandomWord;

    public RandomWordsQuiz(String question, String answer, String[] randomWord, boolean solved) {
        super(question, answer, solved);
        mRandomWord = randomWord;
    }

    @SuppressWarnings("unused")
    public RandomWordsQuiz(Parcel in) {
        super(in);
        setAnswer(in.readString());
        this.mRandomWord = in.createStringArray();
    }

    @Override
    public String getStringAnswer() {
        return getAnswer();
    }

    public String[] getmRandomWord() {
        return mRandomWord;
    }

    @Override
    public QuizType getType() {
        return QuizType.RANDOM_WORDS;
    }

    @Override
    public boolean isAnswerCorrect(String answer) {
        return getAnswer().equalsIgnoreCase(answer);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(getAnswer());
        dest.writeStringArray(this.mRandomWord);
    }
}
