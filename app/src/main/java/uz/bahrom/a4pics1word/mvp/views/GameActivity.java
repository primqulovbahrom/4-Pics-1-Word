package uz.bahrom.a4pics1word.mvp.views;

import static uz.bahrom.a4pics1word.utils.Constants.MAX_ANSWER;
import static uz.bahrom.a4pics1word.utils.Constants.MAX_IMAGES;
import static uz.bahrom.a4pics1word.utils.Constants.MAX_VARIANT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

import uz.bahrom.a4pics1word.R;
import uz.bahrom.a4pics1word.mvp.contracts.GameContract;
import uz.bahrom.a4pics1word.mvp.presenters.GamePresenter;

public class GameActivity extends AppCompatActivity implements GameContract.View {
    private GameContract.Presenter presenter;

    private List<ShapeableImageView> questionImages;
    private List<TextView> answers;
    private List<TextView> variants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        questionImages = new ArrayList<ShapeableImageView>(MAX_IMAGES.value);
        answers = new ArrayList<TextView>(MAX_ANSWER.value);
        variants = new ArrayList<TextView>(MAX_VARIANT.value);

        presenter = new GamePresenter(this);
        findViews();
        presenter.loadCurrentQuestion();
    }

    private void findViews() {


        questionImages.add(findViewById(R.id.image1));
        questionImages.add(findViewById(R.id.image2));
        questionImages.add(findViewById(R.id.image3));
        questionImages.add(findViewById(R.id.image4));


        LinearLayout parentOfAnswerButtons = findViewById(R.id.answers);

        for (int i = 0; i < MAX_ANSWER.value; i++) {
            final TextView btn = (TextView) parentOfAnswerButtons.getChildAt(i);
            btn.setTag(i);
            answers.add(btn);
        }


        RelativeLayout parentOfVariantButtons = findViewById(R.id.variants);

        for (int i = 0; i < MAX_VARIANT.value; i++) {
          final   TextView btn = (TextView) parentOfVariantButtons.getChildAt(i);
            btn.setTag(i);
            variants.add(btn);
        }
        clickEvents();
    }


    @Override
    public void showQuestionImagesToView(List<Integer> images) {
        for (int i = 0; i < MAX_IMAGES.value; i++) {
            questionImages.get(i).setImageResource(images.get(i));
        }
    }

    @Override
    public void showVariantsToView(String variant) {
        for (int i = 0; i < MAX_VARIANT.value; i++) {
            variants.get(i).setText(String.valueOf(variant.charAt(i)));
        }
    }

    @Override
    public void setVisibilityToAnswer(int answerLength) {
        for (int i = 0; i < answerLength; i++) {
            answers.get(i).setVisibility(View.VISIBLE);
        }

        for (int i = answerLength; i < MAX_ANSWER.value; i++) {
            answers.get(i).setVisibility(View.GONE);
        }
    }

    @Override
    public void clearOldData() {
        for (int i = 0; i < MAX_ANSWER.value; i++) {
            answers.get(i).setText("");
        }

        for (int i = 0; i < MAX_VARIANT.value; i++) {
            variants.get(i).setEnabled(true);
        }
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void showDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle("Congratulations!")
                .setMessage("You won, want to continue?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    dialogInterface.cancel();
                    presenter.loadNextQuestion();
                })
                .setNegativeButton("No", ((dialogInterface, i) -> {
                    dialogInterface.cancel();
                    presenter.finishActivity();
                }));
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

    @Override
    public void setTextToAnswer(int pos, String letter) {
        answers.get(pos).setText(letter);
    }

    @Override
    public void setEnabledVariantByPos(int pos, boolean state) {
        variants.get(pos).setEnabled(state);
    }

    @Override
    public void wrongAnswerAnimation() {
        YoYo.with(Techniques.Shake).duration(600).playOn(findViewById(R.id.answers));
    }

    @Override
    public void setWrongColorToAnswers() {
        for (int i = 0; i < MAX_ANSWER.value; i++) {
            answers.get(i).setTextColor(getResources().getColor(R.color.red));
        }
    }

    @Override
    public void setDefaultColorToAnswers() {
        for (int i = 0; i < MAX_ANSWER.value; i++) {
            answers.get(i).setTextColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.finishActivity();
    }

    private void clickEvents() {
        for (int i = 0; i < MAX_ANSWER.value; i++) {
            answers.get(i).setOnClickListener(v -> presenter.answerBtnClick((int) v.getTag()));
        }

        for (int i = 0; i < MAX_VARIANT.value; i++) {
            variants.get(i).setOnClickListener(v -> presenter.variantBtnClick((int) v.getTag()));
        }
    }
}