package uz.bahrom.a4pics1word.mvp.presenters;

import static uz.bahrom.a4pics1word.utils.Constants.MAX_VARIANT;

import java.util.ArrayList;
import java.util.List;

import uz.bahrom.a4pics1word.data.model.QuestionData;
import uz.bahrom.a4pics1word.mvp.contracts.GameContract;
import uz.bahrom.a4pics1word.mvp.models.GameModel;

public class GamePresenter implements GameContract.Presenter {
    private final GameContract.View gameView;
    private final GameContract.Model gameModel;

    private List<String> typedAnswers;
    private List<Boolean> typedVariants;

    public GamePresenter(GameContract.View gameView) {
        this.gameView = gameView;
        gameModel = new GameModel();
    }

    @Override
    public void loadCurrentQuestion() {
        gameView.clearOldData();
        gameView.showQuestionImagesToView(gameModel.getQuestionData().getImageQuestions());
        gameView.showVariantsToView(gameModel.getQuestionData().getVariant());
        gameView.setVisibilityToAnswer(gameModel.getQuestionData().getAnswer().length());

        initTypedArrays();
    }

    @Override
    public void answerBtnClick(int clickedPosition) {
        QuestionData question = gameModel.getQuestionData();
        String variant = question.getVariant();
        String typedLetter = typedAnswers.get(clickedPosition);//D

        for (int i = 0; i < MAX_VARIANT.value; i++) {
            if (String.valueOf(variant.charAt(i)).equals(typedLetter) && typedVariants.get(i)) {
                gameView.setEnabledVariantByPos(i, true);
                typedVariants.set(i, false);
                typedAnswers.set(clickedPosition, null);
                gameView.setTextToAnswer(clickedPosition, "");
                gameView.setDefaultColorToAnswers();
                return;
            }
        }
    }

    @Override
    public void variantBtnClick(int clickedPosition) {//Int pos
        int positionAnswer = typedAnswers.indexOf(null);
        if (positionAnswer == -1) return;

        QuestionData question = gameModel.getQuestionData();

        String variant = question.getVariant();
        String letter = "" + variant.charAt(clickedPosition);
        gameView.setTextToAnswer(positionAnswer, letter);
        typedAnswers.set(positionAnswer, letter);
        gameView.setEnabledVariantByPos(clickedPosition, false);
        typedVariants.set(clickedPosition, true);

        isWin();
    }

    @Override
    public void finishActivity() {
        gameView.closeActivity();
    }

    private void isWin() {
        QuestionData questionData = gameModel.getQuestionData();
        String answer = questionData.getAnswer();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < typedAnswers.size(); i++) {
            sb.append(typedAnswers.get(i));
        }
        String userAnswer = sb.toString();
        if (userAnswer.length() != answer.length()) return;

        if(userAnswer.equals(answer)){
            saveQuestionPos(gameModel.getCurrentQuestionPos() + 1);
            gameView.showDialog();
        }else {
            gameView.setWrongColorToAnswers();
            gameView.wrongAnswerAnimation();
        }
    }

    private void saveQuestionPos(int questionPos) {
        gameModel.saveCurrentQuestionPos(questionPos);
    }

    @Override
    public void loadNextQuestion() {
        loadCurrentQuestion();
    }

    private void initTypedArrays() {
        int answerSize = gameModel.getQuestionData().getAnswer().length();//work color
        typedAnswers = new ArrayList<>(answerSize);

        for (int i = 0; i < answerSize; i++) {
            typedAnswers.add(null);
        }

        typedVariants = new ArrayList<>(MAX_VARIANT.value);
        for (int i = 0; i < MAX_VARIANT.value; i++) {
            typedVariants.add(false);
        }
    }
}

