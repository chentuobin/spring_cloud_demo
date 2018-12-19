package com.interestgroup.microservicequestion.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "type")
    private int type;
    @Column(name = "question_code_related")
    private boolean questionCodeRelated;
    @Column(name = "question_title")
    private String questionTitle;
    @Column(name = "question_desception")
    private String questionDesception;
    @Column(name = "answer_title")
    private String answerTitle;
    @Transient
    private String evaluationStandard;
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "question_id")
    private List<Option> options;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
