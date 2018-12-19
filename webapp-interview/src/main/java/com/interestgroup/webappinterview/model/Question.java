package com.interestgroup.webappinterview.model;

import java.util.List;

public class Question {
    private int id;
    private int type;
    private boolean questionCodeRelated;
    private String questionTitle;
    private String questionDesception;
    private String answerTitle;
    private String evaluationStandard;
    private List<Option> options;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionDesception() {
        return questionDesception;
    }

    public void setQuestionDesception(String questionDesception) {
        this.questionDesception = questionDesception;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public String getEvaluationStandard() {
        return evaluationStandard;
    }

    public void setEvaluationStandard(String evaluationStandard) {
        this.evaluationStandard = evaluationStandard;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public boolean isQuestionCodeRelated() {
        return questionCodeRelated;
    }

    public void setQuestionCodeRelated(boolean questionCodeRelated) {
        this.questionCodeRelated = questionCodeRelated;
    }

}
