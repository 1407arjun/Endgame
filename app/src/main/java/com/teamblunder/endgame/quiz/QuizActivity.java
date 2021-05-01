package com.teamblunder.endgame.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.teamblunder.endgame.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    TextView questionTextView;
    EditText answerEditTextView;
    Button submit;
    CountDownTimer countDownTimer;
    String secondsLeft;
    TextView timerTextView;
    ArrayList<String> questionsAL;
    ArrayList<String> answersAL;
    int i = 0, j = 0, correct = 0, q_count = 0;
    String questionss = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionsAL = new ArrayList<>();

        answersAL = new ArrayList<>();

        questionTextView = findViewById(R.id.question);
        answerEditTextView = findViewById(R.id.answers);
        timerTextView = findViewById(R.id.timer);
        submit = findViewById(R.id.submit);

        findViewById(R.id.loginLayout).setOnClickListener(view -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            View focusedView = QuizActivity.this.getCurrentFocus();
            if (focusedView != null) {
                imm.hideSoftInputFromWindow(focusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        Question question = new Question();
        question.execute("https://71c63cac26d7.ngrok.io/my%20name%20is%20yash%20jasani.%20my%20sister's%20name%20is%20nysha.");

    }

    public void go(View view) {

        if (i < questionsAL.size()) {
            questionGenerate();
            answerEditTextView.setText("");
            q_count++;
            countDownTimer = new CountDownTimer(15 * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    secondsLeft = Long.toString(millisUntilFinished / 1000);
                    if (millisUntilFinished / 1000 <= 9) {
                        secondsLeft = "0" + secondsLeft;
                    }
                    timerTextView.setText("00:" + secondsLeft);
                }

                @Override
                public void onFinish() {

                    if (q_count < 2) {
                        go(view);
                    } else {
                        timerTextView.setText("time over");
                        Toast.makeText(QuizActivity.this, "Quiz is over", Toast.LENGTH_SHORT).show();
                        i = 0;
                        j = 0;
                        q_count = 0;
                        correct = 0;
                    }
                }
            }.start();
        }else{
            timerTextView.setText("time over");
            Toast.makeText(QuizActivity.this, "Quiz is over", Toast.LENGTH_SHORT).show();
            i = 0;
            j = 0;
            q_count = 0;
            answerEditTextView.setText("");
            correct = 0;
        }
    }

    public void questionGenerate() {

        questionTextView.setText(questionsAL.get(i));
        i++;
    }

    public void check(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(answerEditTextView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        if(i>0) {
            if ((answerEditTextView.getText().toString()).equalsIgnoreCase((answersAL.get(i - 1)).trim())) {
                correct++;
                Snackbar snackbar = Snackbar
                        .make(view, "Correct Answer!!", Snackbar.LENGTH_SHORT);
                snackbar.show();

            } else {
                Snackbar snackbar = Snackbar
                        .make(view, "Incorrect Answer!!", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
            countDownTimer.cancel();
            go(view);
        }
    }

    public class Question extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                Log.i("result", result);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "0";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            JSONArray array = null;
            try {
                array = new JSONArray(result);
                questionss = array.get(0).toString();
                if(questionss.charAt(questionss.length()-1) != '.') {
                    questionss += '.';
                }
                String s; int prev=0;
                Log.i("length" , Integer.toString(questionss.length()));
                for(int i = 0;i<questionss.length();i++){
                    if(questionss.charAt(i) == '.'){
                        Log.i("check" , Integer.toString(i));
                        s = questionss.substring(prev, i);
                        s = s.trim();
                        prev = i+1;
                        questionsAL.add(s);
                    }
                }
                for (int i = 1; i < array.length(); i++) {
                    try {
                        answersAL.add(array.get(i).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                for(int i=0;i<questionsAL.size();i++){
                    Log.i("question" , questionsAL.get(i));
                }
                for(int i=0;i<answersAL.size();i++){
                    Log.i("answers" , answersAL.get(i));
                }
                Log.i("questionss" , questionss);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}