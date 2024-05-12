package com.ftn.sbnz.model.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String rank;
    private int numberOfStudents;
    private double studentPerStaff;
    private double internationalStudentPercent;
    private String overallScore;
    private double researchScore;
    private double citationScore;

    public University(String name, String location, String rank, int numberOfStudents, double studentPerStaff, double internationalStudentPercent, String overallScore, double researchScore, double citationScore) {
        this.name = name;
        this.location = location;
        this.rank = rank;
        this.numberOfStudents = numberOfStudents;
        this.studentPerStaff = studentPerStaff;
        this.internationalStudentPercent = internationalStudentPercent;
        this.overallScore = overallScore;
        this.researchScore = researchScore;
        this.citationScore = citationScore;
    }
}
