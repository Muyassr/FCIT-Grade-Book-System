
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FCITgradeBook {

    public static void main(String[] args) throws FileNotFoundException {

       

        File inputFile = new File("FCITgradeBook.in.txt");
        File outputFile = new File("FCITgradeBook.out.txt");
        PrintWriter write = new PrintWriter(outputFile);
        if (!inputFile.exists()) {
            write.println("Input file, " + inputFile + ", does not exist.");
            write.flush();
            write.close();
            System.exit(0);

        }

        Scanner read = new Scanner(inputFile);
        String command;

        int numCourses = read.nextInt();

        FCITcourseRoster[] courses = new FCITcourseRoster[numCourses];
         write.println("Welcome to the FCIT Grade Book.	");
        write.println("The following course(s) have been added to the database:");
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new FCITcourseRoster();
            String name = read.next();
            courses[i].setCourseNumber(name);
            write.println("	" + name);
        }

        write.println("");

        while (read.hasNext()) {
            command = read.next();

            if (command.equals("ADDRECORD")) {

                write.println("Command: ADDRECORD");

                String courseNumber = read.next();

                boolean found = false;
                for (int i = 0; i < courses.length; i++) {
                    if (courses[i].getCourseNumber().equals(courseNumber)) {
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    write.println("	ERROR: cannot add student. Course " + courseNumber + " does not exist.");
                } else {

                    //int courseInd = getCourseInd(courseNumber);
                    int id = read.nextInt();
                    String firstName = read.next();
                    String lastName = read.next();
                    int exam_1 = read.nextInt();
                    int exam_2 = read.nextInt();
                    int exam_3 = read.nextInt();
                    double final_grade = exam_1 * (0.3) + exam_2 * (0.3) + exam_3 * (0.4);
                    char letter = getLetterGrade(final_grade);

                    Student std = new Student();
                    std.setCourseNumber(courseNumber);
                    std.setFirstName(firstName);
                    std.setLastName(lastName);
                    std.setID(id);
                    std.setLetterGrade(letter);
                    std.getExamGrades()[0] = exam_1;
                    std.getExamGrades()[1] = exam_2;
                    std.getExamGrades()[2] = exam_3;
                    std.setFinalGrade(final_grade);
                    for (int i = 0; i < courses.length; i++) {
                        if (std.getCourseNumber().equals(courses[i].getCourseNumber())) {
                            courses[i].insert(std);
                        }
                    }

                    write.print("	" + firstName + " " + lastName + " (ID# " + id
                            + ") has been added to " + courseNumber + ".\n");
                    write.printf("        Final Grade:  %.2f (%s)\n", final_grade, letter);
                    Student.setNumStudents(Student.getNumStudents() + 1);
                    write.println("");
                }
            } else if (command.equals("DELETERECORD")) {

                write.println("Command: DELETERECORD");
                int id = read.nextInt();
                int counter = 0;
                boolean there = true;
                for (int i = 0; i < courses.length; i++) {
                    Student s = courses[i].findNode(id);
                    if (s != null) {
                        courses[i].delete(id);
                        
                        Student.setNumStudents(Student.getNumStudents()-1);
                        write.println("	" + s.getFirstName()
                                + " " + s.getLastName() + " (ID# " + s.getID()
                                + ") has been deleted from " + s.getCourseNumber() + ".\n");
                    } else {
                        counter++;
                    }
                }

                if (counter == courses.length) {
                    write.println("student is not in the database!\n");
                }

            } else if (command.equals("SEARCHBYID")) {

                write.println("Command: SEARCHBYID");

                int id = read.nextInt();
                Student s = null;
                //find the student      
                int counter = 0;
                for (int i = 0; i < courses.length; i++) {
                    s = courses[i].findNode(id);

                    if (s != null) {
                        break;
                    } else {
                        counter++;
                    }
                }

                if (counter == courses.length) {
                    write.println("	ERROR: there is no record for student ID# " + id + ".\n");
                } else {
                    write.println("Student Record for "
                            + s.getFirstName() + " " + s.getLastName()
                            + " (ID# " + s.getID() + "):");

                    for (int i = 0; i < courses.length; i++) {
                        Student std = courses[i].findNode(id);
                        if (std != null) {

                            write.println("	Course: " + courses[i].getCourseNumber());

                            write.println("		Exam 1:       " + std.getExamGrades()[0] + "\n"
                                    + "		Exam 2:       " + std.getExamGrades()[1] + "\n"
                                    + "		Final Exam:   " + std.getExamGrades()[2] + "");
                            write.printf("        \tFinal Grade:  %.2f\n", std.getFinalGrade());
                            write.println("		Letter Grade: " + std.getLetterGrade());
                        }
                    }

                }
                write.println("");
            } else if (command.equals("SEARCHBYNAME")) {
                write.println("Command: SEARCHBYNAME");

                String firstName = read.next();
                String lastName = read.next();
                Student s = null;
                int counter = 0;
                for (int i = 0; i < courses.length; i++) {
                    s = courses[i].findNodeByName(firstName, lastName);
                    if (s != null) {
                        break;
                    } else {
                        counter++;
                    }
                }

                if (counter == courses.length) {
                    write.println("	ERROR: there is no record for student \"" + firstName + " " + lastName + "\".");
                } else {
                    write.println("Student Record for "
                            + s.getFirstName() + " " + s.getLastName()
                            + " (ID# " + s.getID() + "):");

                    for (int i = 0; i < courses.length; i++) {
                        Student std = courses[i].findNodeByName(firstName, lastName);
                        if (std != null) {

                            write.println("	Course: " + courses[i].getCourseNumber());

                            write.println("		Exam 1:       " + std.getExamGrades()[0] + "\n"
                                    + "		Exam 2:       " + std.getExamGrades()[1] + "\n"
                                    + "		Final Exam:   " + std.getExamGrades()[2] + "");
                            write.printf("        \tFinal Grade:  %.2f\n", std.getFinalGrade());
                            write.println("		Letter Grade: " + std.getLetterGrade());
                        }
                    }

                }
                write.println("");

            } else if (command.equals("DISPLAYSTATS")) {
                String all = read.next();
                if (all.equals("ALL")) {

                    write.println("Command: DISPLAYSTATS (ALL)\n"
                            + "Statistical Results of all courses:\n"
                            + "Total number of student records: " + Student.getNumStudents());

                    courses[0].calculate_All_course(courses, write);

                } else {

                    for (int i = 0; i < courses.length; i++) {
                        if (all.equals(courses[i].getCourseNumber())) {
                            write.println("Command: DISPLAYSTATS (" + all + ")\n"
                                    + "Statistical Results of " + all + ":\n"
                                    + "Total number of student records: " + courses[i].countStudents());
                            courses[i].calculate_course( write);

                        }
                    }

                }

                write.println("");
            } else if (command.equals("DISPLAYSTUDENTS")) {

                String str = read.next();
                write.println("Command: DISPLAYSTUDENTS " + str);

                if (Student.getNumStudents() < 1) {
                    write.println("\t\tERROR: there are no students currently in the system.");

                } else {
                    write.println("");
                    if (str.equals("ALL")) {
                        for (int i = 0; i < courses.length; i++) {
                            write.println("Course Roster for " + courses[i].getCourseNumber() + ":");
                            courses[i].displayStudents(write);

                        }

                    } else {

                        for (int i = 0; i < courses.length; i++) {

                            if (str.equals("" + courses[i].getCourseNumber() + "")) {
                                if (courses[i].getHead() == null) {
                                    write.println("	ERROR: there are no student records for " + str + ".");
                                    continue;
                                } else {
                                    write.println("Course Roster for " + str + ":");
                                    courses[i].displayStudents(write);
                                }

                            }
                        }

                    }

                }
                write.println("");
            } else if (command.equals("QUIT")) {
                write.println("Thank you for using the the FCIT Grade Book.\n"
                        + "\n"
                        + "Goodbye.");
                write.flush();
                write.close();
                System.exit(0);

            }
        }

    }

    public static char getLetterGrade(double final_grade) {

        if (final_grade < 60) {
            return 'F';
        } else if (final_grade >= 70 && final_grade < 80) {
            return 'C';
        } else if (final_grade >= 80 && final_grade < 90) {
            return 'B';
        } else if (final_grade >= 90) {
            return 'A';
        } else if (final_grade >= 60 && final_grade < 70) {
            return 'D';
        }

        return 'Z';
    }

}
