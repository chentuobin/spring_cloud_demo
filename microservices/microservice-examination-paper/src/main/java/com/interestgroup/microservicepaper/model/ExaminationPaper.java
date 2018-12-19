package com.interestgroup.microservicepaper.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "examination_paper")
public class ExaminationPaper {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "examination_paper_id")
    private List<Section> sections;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
