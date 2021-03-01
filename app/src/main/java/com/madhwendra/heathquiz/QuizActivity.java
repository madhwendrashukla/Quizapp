package com.madhwendra.heathquiz;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class QuizActivity extends AppCompatActivity {
    TextView timer;
    private String name;
    public static final String FILE_NAME = "QUIZZER";
    public static final String KEY_NAME = "QUESTIONS";


    private TextView question, noIndicator;
    private LinearLayout optionsContainer;
    private Button shareBtn, nextBtn;
    private int count = 0;
    private List<QuestionModel> list;
    private int position = 0;
    private int score = 0;
    private Dialog loadingdialog;
    MediaPlayer correctSound, incorrectSound;
    boolean timerAgain = true;
    CountDownTimer countDownTimer;
    int totalscore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        question = findViewById(R.id.question);
        noIndicator = findViewById(R.id.no_indicator);

        optionsContainer = findViewById(R.id.options_container);
        shareBtn = findViewById(R.id.share_btn);
        nextBtn = findViewById(R.id.next_btn);
        timer = findViewById(R.id.timer);
        correctSound = MediaPlayer.create(this, R.raw.eighth);



        list = new ArrayList<>();


        list.add(new QuestionModel("I feel lack of confidence", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I get excited very quickly", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I am not able to make quick decisions on any subject", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I feel that the situation is turning against me ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I love and love my neighbors.", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I change myself to suit the circumstances", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I feel like i’m losing respect in you", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I take a broad view of life ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I worry about small things for a long time ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I don’t know what my next step will be ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I miss major it takes me so long to meet people ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I do my work properly even in odd circumstances", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("It seems to me that I do not know to the full extent of my ability to do various tasks", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I behave in odd par situations without taking into account facts or reality", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I feel irritated", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I feel insecure ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I am very concerned about my work responsibilities", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I feel sad and tearful", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I pay an important role in organizing social", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I act prudently even in times of troble", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I feel that my relationships with others are not satisfactory", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("My work is load about me", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I suffer from inferiority complex", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I live in a world of fantasy", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I keep hinting and worried about my future", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("My friends are ready to help in times of trouble", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I make sure plans about my future", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I get angry on the slightest choice ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I make easy decisions even in difficult situations", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I am not able to behave as expected of me ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("My colleagues and colleagues look at me with respect", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("My morale is fluctuating a lot ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I am ready to face problems ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I the absence of available evidence, people make these perspectives about these subjects ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I cannot concentrate fully in my works ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I have an inclination towards the opposite sex", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I solve my problems myself", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I have full support for the important work of my group", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I keep troubled by my anti purse thoughts", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I decide on the basis of fact even if it is against my well", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I do not know how to work late ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I feel safe among my group or peers", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I do not disappoint even when I fail", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I consider my work meaningful for the society", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I aspire without taking my shortcoming into consideration", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I cannot make the decision I want to make", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I indicate that you do not read my objection ", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I feel that world is a good place to live life.", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I am excited just thinking that I will definitely achieve my objective", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I am not disappointed by the simple problems of daily life", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("My state of mind changes very quickly", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("I decide for myself what I should do", "Always", "Often", "Rarely", "Never", "Always"));
        list.add(new QuestionModel("Feel happy taking responsibilities", "Always", "Often", "Rarely", "Never", "Always"));


        if (list.size() > 0) {

            for (int i = 0; i < 4; i++) {
                optionsContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View view) {
                        checkAnswer((Button) view);
                    }
                });
            }
            playAnim(question, 0, list.get(position).getQuestion());
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    nextBtn.setEnabled(false);
                    nextBtn.setAlpha(0.7f);
                    enabledoption(true);
                    position++;
                    if (position == list.size()) {
                        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                        intent.putExtra("score", score);
                        intent.putExtra("total", list.size());
                        startActivity(intent);
                        finish();


                        return;

                    }
                    count = 0;
                    playAnim(question, 0, list.get(position).getQuestion());
                }
            });
            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String body = "*" + list.get(position).getQuestion() + "*" + "\n" +
                            "(a) " + list.get(position).getA() + "\n" +
                            "(b) " + list.get(position).getB() + "\n" +
                            "(c) " + list.get(position).getC() + "\n" +
                            "(d) " + list.get(position).getD();

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Data Structure Using C");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, body);
                    startActivity(Intent.createChooser(shareIntent, "Share Via"));
                }
            });

        } else {
            Toast.makeText(QuizActivity.this, "No Questions", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedoption) {
        enabledoption(false);
        nextBtn.setEnabled(true);
        nextBtn.setAlpha(1);
        if (selectedoption.getText().toString().equals(list.get(position).getA())) {
//            correct answer
            score +=4 ;
            correctSound.start();
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));


        } else if (selectedoption.getText().toString().equals(list.get(position).getB())) {

            score += 3;
            correctSound.start();
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));

        } else if (selectedoption.getText().toString().equals(list.get(position).getC())) {

            score += 2;
            correctSound.start();
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));

        } else {
            score += 1;
            correctSound.start();
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enabledoption(boolean enable) {
        for (int i = 0; i < 4; i++) {
            optionsContainer.getChildAt(i).setEnabled(enable);
            if (enable) {
                optionsContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ABA8AC")));
            }
        }
    }

    private void playAnim(final View view, final int value, final String data) {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                if (value == 0 && count < 4) {
                    String option = "";
                    if (count == 0) {
                        option = list.get(position).getA();
                    } else if (count == 1) {
                        option = list.get(position).getB();
                    } else if (count == 2) {
                        option = list.get(position).getC();
                    } else if (count == 3) {
                        option = list.get(position).getD();
                    }
                    playAnim(optionsContainer.getChildAt(count), 0, option);
                    count++;
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationEnd(Animator animator) {
                if (value == 0) {
                    try {
                        noIndicator.setText(position + 1 + "/" + list.size());
                        ((TextView) view).setText(data);
                    } catch (ClassCastException ex) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view, 1, data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }


}