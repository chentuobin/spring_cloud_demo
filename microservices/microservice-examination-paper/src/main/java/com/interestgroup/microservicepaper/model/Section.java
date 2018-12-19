package com.interestgroup.microservicepaper.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Section {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "description")
    private String description;
    @Column(name = "sequence")
    private int sequence;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "section_question", joinColumns = {
            @JoinColumn(name = "section_id", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "question_id", referencedColumnName = "ID")})
    private List<Question> questions;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
}
