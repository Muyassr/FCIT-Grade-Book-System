/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Moyasr_tam
 */
public class Student {

    private String courseNumber;
    private int ID;
    private String firstName;
    private String lastName;
    private int[] examGrades = new int[3];
    private double finalGrade;
    private char letterGrade;
    private static int numStudents=0;
    private Student next;


    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int[] getExamGrades() {
        return examGrades;
    }

    public void setExamGrades(int[] examGrades) {
        this.examGrades = examGrades;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public char getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(char letterGrade) {
        this.letterGrade = letterGrade;
    }

    public static int getNumStudents() {
        return numStudents;
    }

    public static void setNumStudents(int numStudents) {
        Student.numStudents = numStudents;
    }

    public Student getNext() {
        return next;
    }

    public void setNext(Student next) {
        this.next = next;
    }
    
     
    
    
}
