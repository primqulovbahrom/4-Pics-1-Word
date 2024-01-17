package uz.bahrom.a4pics1word.data.model;

import java.util.List;

public class QuestionData {
    private List<Integer> imageQuestions;
    private String answer;
    private String variant;

    public QuestionData(List<Integer> imageQuestions, String answer, String variant) {
        this.imageQuestions = imageQuestions;
        this.answer = answer;
        this.variant = variant;
    }

    public List<Integer> getImageQuestions() {
        return imageQuestions;
    }

    public String getAnswer() {
        return answer;
    }

    public String getVariant() {
        return variant;
    }
}
