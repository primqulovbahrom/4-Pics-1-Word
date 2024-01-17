package uz.bahrom.a4pics1word.mvp.models;

import uz.bahrom.a4pics1word.data.model.QuestionData;
import uz.bahrom.a4pics1word.domain.Repository;
import uz.bahrom.a4pics1word.mvp.contracts.GameContract;

public class GameModel implements GameContract.Model {
    private final Repository repository;

    public GameModel() {
        this.repository = Repository.getInstance();
    }

    @Override
    public QuestionData getQuestionData() {
        return repository.getCurrentQuestion();
    }

    @Override
    public int getCurrentQuestionPos() {
        return repository.getCurrentQuestionPos();
    }


    @Override
    public void saveCurrentQuestionPos(int pos) {
        repository.saveCurrentQuestionPos(pos);
    }
}
