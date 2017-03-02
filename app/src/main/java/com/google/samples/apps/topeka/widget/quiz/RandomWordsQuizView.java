/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.topeka.widget.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.anton46.collectionitempicker.CollectionPicker;
import com.anton46.collectionitempicker.Item;
import com.anton46.collectionitempicker.OnItemClickListener;
import com.google.samples.apps.topeka.R;
import com.google.samples.apps.topeka.model.Category;
import com.google.samples.apps.topeka.model.quiz.RandomWordsQuiz;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ViewConstructor")
public class RandomWordsQuizView extends AbsQuizView<RandomWordsQuiz> {

    private static final String KEY_ANSWER = "ANSWER";

    private String mAnswerText;

    public RandomWordsQuizView(Context context, Category category, RandomWordsQuiz quiz) {
        super(context, category, quiz);
    }

    @Override
    protected View createQuizContentView() {
        String[] randomWords = getQuiz().getmRandomWord();
        return getQuestionAnswerPickerView(randomWords);
    }

    @Override
    public Bundle getUserInput() {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ANSWER, getSelectedItemString());
        return bundle;
    }

    @Override
    public void setUserInput(Bundle savedInput) {
        if (savedInput == null) {
            return;
        }
        mAnswerText = savedInput.getString(KEY_ANSWER);
    }

    private View getQuestionAnswerPickerView(String[] randomWords) {
        LinearLayout container = (LinearLayout) getLayoutInflater().inflate(
                R.layout.quiz_layout_random_words, this, false);

        CollectionPicker mPicker = (CollectionPicker) container.findViewById(R.id.collection_item_picker);

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < randomWords.length; i++) {
            String word = randomWords[i];
            items.add(new Item("item" + i, word));
        }
        setItems(items);
        mPicker.setItems(items);
        mPicker.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(Item item, int position) {
                if (!item.isSelected) {
                    removeItem(item);
                } else {
                    pickItem(item);
                }
            }
        });
        return container;
    }

    @Override
    protected boolean isAnswerCorrect() {
        return getQuiz().isAnswerCorrect(mAnswerText);
    }
}
