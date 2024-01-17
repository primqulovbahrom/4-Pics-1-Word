package uz.bahrom.a4pics1word.mvp.models;

import uz.bahrom.a4pics1word.data.model.QuestionData;
import uz.bahrom.a4pics1word.domain.Repository;
import uz.bahrom.a4pics1word.mvp.contracts.MainContract;

public class MainModel implements MainContract.Model {
    private final Repository repository;

    public MainModel() {
        repository = Repository.getInstance();
    }

    @Override
    public QuestionData getCurrentQuestionData() {
        return repository.getCurrentQuestion();
    }

    @Override
    public int getCurrentQuestionPos() {
        return repository.getCurrentQuestionPos();
    }

}
