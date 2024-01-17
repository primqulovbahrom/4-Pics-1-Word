package uz.bahrom.a4pics1word.mvp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uz.bahrom.a4pics1word.R;
import uz.bahrom.a4pics1word.data.model.QuestionData;
import uz.bahrom.a4pics1word.mvp.contracts.MainContract;
import uz.bahrom.a4pics1word.mvp.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    private AppCompatTextView questionPosTV;
    private List<ImageView> questionImages;
    private AppCompatButton playBtn;
    private AppCompatButton aboutBtn;

    private final int MAX_IMAGES = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        findViews();
    }

    private void findViews() {
        questionImages = new ArrayList<>(MAX_IMAGES);

        questionImages.add(findViewById(R.id.image1));
        questionImages.add(findViewById(R.id.image2));
        questionImages.add(findViewById(R.id.image3));
        questionImages.add(findViewById(R.id.image4));

        questionPosTV = findViewById(R.id.question_position);

        playBtn = findViewById(R.id.buttonPlay);
        aboutBtn = findViewById(R.id.buttonAbout);

        clickEvents();
    }

    private void clickEvents() {
        playBtn.setOnClickListener(v ->{
            startActivity(new Intent(this, GameActivity.class));
//            presenter.openPlayActivity()
        });
        aboutBtn.setOnClickListener(v -> Toast.makeText(this, "about button hali tamirda", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadQuestion();
    }

    @Override
    public void setImageQuestionsToView(QuestionData data) {
        for (int i = 0; i < MAX_IMAGES; i++) {
            questionImages.get(i).setImageResource(data.getImageQuestions().get(i));
        }
    }

    @Override
    public void setCurrentQuestionPosToView(int pos) {
        questionPosTV.setText(String.valueOf(pos + 1));
    }

    @Override
    public void goToPlayActivity() {
        startActivity(new Intent(this, GameActivity.class));
    }
}