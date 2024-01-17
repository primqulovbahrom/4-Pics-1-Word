package uz.bahrom.a4pics1word.mvp.contracts;

import uz.bahrom.a4pics1word.data.model.QuestionData;

public interface MainContract {
    interface Model {
        QuestionData getCurrentQuestionData();

        int getCurrentQuestionPos();
    }

    interface Presenter {
        void loadQuestion();

        void openPlayActivity();
    }

    interface View {
        void setImageQuestionsToView(QuestionData data);

        void setCurrentQuestionPosToView(int pos);

        void goToPlayActivity();
    }
}
