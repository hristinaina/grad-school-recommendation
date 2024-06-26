package com.ftn.sbnz.model.models;

import com.ftn.sbnz.model.events.FinancialAid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GradProgram  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    private University university;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Requirement requirement;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FinancialAid> financialAids;
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Professor professor;

    public void addFinancialAid(FinancialAid financialAid){
        if (this.financialAids == null) {
            this.financialAids = new HashSet<>();
        }
        this.financialAids.add(financialAid);
    }

    public GradProgram(double price, University university, Requirement requirement, String name, Set<FinancialAid> financialAids) {
        this.price = price;
        this.university = university;
        this.requirement = requirement;
        this.name = name;
        this.financialAids = financialAids;
    }
}
