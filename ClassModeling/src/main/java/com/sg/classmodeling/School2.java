/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.classmodeling;

/**
 *
 * @author rianu
 */
public class School2 {
    private int enrollement;
    private int numberOfTeachers;
    private String[] coursesOffered;
    private String sportsNickName;
    private String[] clubsOffered;
    private Student[]studentRoster ;
    
    public void enrollStudent(Student student) {
        
    }
    
    public void unenrollStudent(Student student) {
        
    }

    public int getEnrollement() {
        return enrollement;
    }

    public void setEnrollement(int enrollement) {
        this.enrollement = enrollement;
    }

    public int getNumberOfTeachers() {
        return numberOfTeachers;
    }

    public void setNumberOfTeachers(int numberOfTeachers) {
        this.numberOfTeachers = numberOfTeachers;
    }

    public String[] getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(String[] coursesOffered) {
        this.coursesOffered = coursesOffered;
    }

    public String getSportsNickName() {
        return sportsNickName;
    }

    public void setSportsNickName(String sportsNickName) {
        this.sportsNickName = sportsNickName;
    }

    public String[] getClubsOffered() {
        return clubsOffered;
    }

    public void setClubsOffered(String[] clubsOffered) {
        this.clubsOffered = clubsOffered;
    }

    public Student[] getStudentRoster() {
        return studentRoster;
    }

    public void setStudentRoster(Student[] studentRoster) {
        this.studentRoster = studentRoster;
    }
    
    
    
}
