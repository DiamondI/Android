package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_IS_CHEATER = "is_cheater";
    private static final int REQUEST_CODE_CHEAT = 0;
    private static final String KEY_IS_CHEAT_DISABLED = "is_cheat_disabled";
    private static final String KET_CHEAT_TIMES = "cheat_times";

    private static final int MAX_CHEAT_TIMES = 3;

    private boolean mCheatIsDisabled = false;

    private int mCheatTimes = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private boolean[] mIsCheaterBank = new boolean[mQuestionBank.length];

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            // need to restore the whole boolean array
            mIsCheaterBank = savedInstanceState.getBooleanArray(KEY_IS_CHEATER);
            mCheatIsDisabled = savedInstanceState.getBoolean(KEY_IS_CHEAT_DISABLED);
            mCheatTimes = savedInstanceState.getInt(KET_CHEAT_TIMES);
        }

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setText(getString(R.string.cheat_button, MAX_CHEAT_TIMES - mCheatTimes));
        if (mCheatIsDisabled) {
            mCheatButton.setEnabled(false);
        }
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start CheatActivity
                //Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                // startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
                mCheatTimes++;
                mCheatButton.setText(getString(R.string.cheat_button, MAX_CHEAT_TIMES - mCheatTimes));
                if (mCheatTimes >= MAX_CHEAT_TIMES) {
                    mCheatButton.setEnabled(false);
                    mCheatIsDisabled = true;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheaterBank[mCurrentIndex] = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        // need to store the whole boolean array
        savedInstanceState.putBooleanArray(KEY_IS_CHEATER, mIsCheaterBank);
        savedInstanceState.putBoolean(KEY_IS_CHEAT_DISABLED, mCheatIsDisabled);
        savedInstanceState.putInt(KET_CHEAT_TIMES, mCheatTimes);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;

        if (mIsCheaterBank[mCurrentIndex]) {
            messageResId = R.string.judgement_toast;
        } else {
            if (userPressedTrue == answerTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }


        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
