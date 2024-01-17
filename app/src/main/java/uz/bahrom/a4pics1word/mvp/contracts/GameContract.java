package uz.bahrom.a4pics1word.mvp.contracts;

import java.util.List;

import uz.bahrom.a4pics1word.data.model.QuestionData;

public interface GameContract {
    interface Model {
        QuestionData getQuestionData();
        int getCurrentQuestionPos();

        void saveCurrentQuestionPos(int pos);
    }

    interface Presenter {
        void loadCurrentQuestion();
        void answerBtnClick(int clickedPosition);
        void variantBtnClick(int clickedPosition);
        void finishActivity();
        void loadNextQuestion();
    }

    interface View {
        void showQuestionImagesToView(List<Integer> questionImages);

        void showVariantsToView(String variant);

        void setVisibilityToAnswer(int answerLength);

        void clearOldData();
        void closeActivity();
        void showDialog();

        void setTextToAnswer(int pos,String letter);

        void setEnabledVariantByPos(int pos, boolean state);
        void wrongAnswerAnimation();
        void setWrongColorToAnswers();
        void setDefaultColorToAnswers();
    }
}
