package com.ftn.sbnz.tests;

import com.ftn.sbnz.model.models.*;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
public class BackwardsTest {
    @Test
    public void testBackwardsWithMoreMentors() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kieSession = kContainer.newKieSession("bwKsession");

        // Create more professors
        Professor prof1 = new Professor("Prof1", "prof1@gmail.com", "password", "USA", null);
        Professor prof2 = new Professor("Prof2", "prof2@gmail.com", "password", "USA", null);
        Professor prof3 = new Professor("Prof3", "prof3@gmail.com", "password", "USA", null);
        Professor prof4 = new Professor("Prof4", "prof4@gmail.com", "password", "USA", null);
        Professor prof5 = new Professor("Prof5", "prof5@gmail.com", "password", "USA", null);

        // Create more mentor-student relationships
        Mentorship mentorship1 = new Mentorship(prof1, prof2);
        Mentorship mentorship2 = new Mentorship(prof1, prof3);
        Mentorship mentorship3 = new Mentorship(prof2, prof3);
        Mentorship mentorship4 = new Mentorship(prof2, prof4);
        Mentorship mentorship5 = new Mentorship(prof3, prof5);
        Mentorship mentorship6 = new Mentorship(prof4, prof5);

        Student jane = new Student("Jane", "Doe","jane@gmail.com", "password", 3.8,"USA",  Set.of("Data Science", "Software Engineering"), Map.of("GRE", 328.0), Set.of("Research in Data Science"),false);
// Create some requirements
        Requirement req3 = new Requirement(3.5,Set.of("Data Science"), Map.of("GRE", 315.0), Set.of("Research in Data Science"));
        Requirement req4 = new Requirement(3.7, Set.of("Software Engineering"), Map.of("GRE", 320.0), Set.of("Research in Software Engineering"));

        University mit = new University("MIT", "Cambridge, MA", "1", 11332, 3.0, 34.0, "100", 100.0, 100.0);
        University stanford = new University("Stanford", "Stanford, CA", "2", 17005, 3.9, 23.0, "98.7", 98.7, 99.9);

// Create some grad programs
        GradProgram dataScienceProgram = new GradProgram(55000.0, mit, req3,"Data Science",new HashSet<>());
        GradProgram softwareEngProgram = new GradProgram(60000.0, stanford, req4,"Software Engineering",new HashSet<>());

// Add the programs to professors

        // Create some additional requirements
        Requirement req5 = new Requirement(3.5, Set.of("Data Science"), Map.of("GRE", 315.0),Set.of("Research in Data Science"));
        Requirement req6 = new Requirement(3.7, Set.of("Software Engineering"), Map.of("GRE", 320.0), Set.of("Research in Software Engineering"));

// Create some additional grad programs
        GradProgram dataScienceProgram2 = new GradProgram(55000.0, mit, req5,"Data Science2",new HashSet<>());
        GradProgram softwareEngProgram2 = new GradProgram(60000.0, stanford, req6,"Software Engineering2",new HashSet<>());

// Add the programs to professors

// Insert the programs into the KieSession
        kieSession.insert(dataScienceProgram);
        kieSession.insert(softwareEngProgram);
        kieSession.insert(dataScienceProgram);
        kieSession.insert(softwareEngProgram);
        // Insert professors and mentor-student relationships into the KieSession
        kieSession.insert(prof1);
        kieSession.insert(prof2);
        kieSession.insert(prof3);
        kieSession.insert(prof4);
        kieSession.insert(prof5);
        kieSession.insert(mentorship1);
        kieSession.insert(mentorship2);
        kieSession.insert(mentorship3);
        kieSession.insert(mentorship4);
        kieSession.insert(mentorship5);
        kieSession.insert(mentorship6);
        kieSession.insert(jane);






        kieSession.setGlobal("mentor", prof1);
        List<Professor> results = new ArrayList<>();
        kieSession.setGlobal("results", results);
        // Fire all rules
        kieSession.fireAllRules();

        List<GradProgram> programs = new ArrayList<>();


        for(GradProgram p : programs) {
            QueryResults results1 = kieSession.getQueryResults("CheckStudentRequirements", jane,p );
            if (results1.size()>0)
                System.out.println("Student: " + jane.getName() + " meets requirements for program: " + p.getName());
        }

//        // Test the query for multiple mentors
//        QueryResults results1 = kieSession.getQueryResults("FindMentorsAndStudents", prof1, prof3);
//        printResults(results1);
//
//        QueryResults results2 = kieSession.getQueryResults("FindMentorsAndStudents", prof2, prof4);
//        printResults(results2);
//
//        QueryResults results3 = kieSession.getQueryResults("FindMentorsAndStudents", prof3, prof5);
//        printResults(results3);
//
//        QueryResults results4 = kieSession.getQueryResults("FindMentorsAndStudents", prof1, prof5);
//        printResults(results4);


        kieSession.dispose();
    }

    private void printResults(QueryResults results) {
        for (QueryResultsRow row : results) {
            Professor mentor = (Professor) row.get("$mentor");
            Professor student = (Professor) row.get("$student");
            System.out.println("Mentor: " + mentor.getName() + ", Student: " + student.getName());
        }
    }
}