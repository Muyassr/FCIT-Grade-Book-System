
import java.io.PrintWriter;


public class FCITcourseRoster {

    private Student head;
    private String courseNumber;

    public boolean isEmpty() {
        return head == null;
    }

    public void insert(Student s) {

        if (head == null || s.getID() < head.getID()) {
            s.setNext(head);
            head = s;
        } else {
            Student hlpPtr = head;
            while (hlpPtr.getNext() != null) {
                if (hlpPtr.getNext().getID() > s.getID()) {
                    break;
                }
                hlpPtr = hlpPtr.getNext();
            }
            s.setNext(hlpPtr.getNext());
            hlpPtr.setNext(s);
        }
    }

    public Student displayStd(int stdID, PrintWriter write) {
        return displayStd(head, stdID,  write);
    }

    private Student displayStd(Student p, int stdID, PrintWriter write) {
        Student helpPtr = p;
        while (helpPtr != null) {

            write.println("	Student PlaysALot (ID# " + helpPtr.getID() + ")");
            write.println("		Exam 1:       " + helpPtr.getExamGrades()[0]);
            write.println("		Exam 2:       " + helpPtr.getExamGrades()[1]);
            write.println("		final exam:       " + helpPtr.getExamGrades()[2]);
            write.printf("        Final Grade:  %.2f\n", helpPtr.getFinalGrade());
            write.println("		Letter Grade: " + helpPtr.getLetterGrade());

            helpPtr = helpPtr.getNext();
        }
        return null;
    }

    public Student findNode(int stdID) {
        return findNode(head, stdID);
    }

    private Student findNode(Student p, int stdID) {
        Student helpPtr = p;
        while (helpPtr != null) {
            if (helpPtr.getID() == stdID) {
                return helpPtr;
            }
            helpPtr = helpPtr.getNext();
        }
        return null;
    }

    public double calculate_All_course(FCITcourseRoster[] courses, PrintWriter write) {
        return calculate_All_course(head, courses,  write);
    }

    private double calculate_All_course(Student p, FCITcourseRoster[] courses, PrintWriter write) {

        double ave = 0;
        int count = 0;
        double highest = -1;
        double lowest = 10000000;
        int a_counter = 0;
        int b_counter = 0;
        int c_counter = 0;
        int d_counter = 0;
        int f_counter = 0;
        double a_perc = 0;
        double b_perc = 0;
        double c_perc = 0;
        double d_perc = 0;
        double f_perc = 0;
        double all_ave = 0;

        for (int i = 0; i < courses.length; i++) {
            Student helpPtr = courses[i].getHead();
            while (helpPtr != null) {
                ave += helpPtr.getFinalGrade();
                if (helpPtr.getFinalGrade() > highest) {
                    highest = helpPtr.getFinalGrade();
                }
                if (helpPtr.getFinalGrade() < lowest) {
                    lowest = helpPtr.getFinalGrade();
                }

                char letter = helpPtr.getLetterGrade();
                if (letter == 'A') {
                    a_counter++;
                } else if (letter == 'B') {
                    b_counter++;
                } else if (letter == 'C') {
                    c_counter++;
                } else if (letter == 'D') {
                    d_counter++;
                } else if (letter == 'F') {
                    f_counter++;
                }

              

                helpPtr = helpPtr.getNext();
            }

        }
          if (Student.getNumStudents() > 0) {
                    ave = (ave / Student.getNumStudents());
                    a_perc = (a_counter / (double) Student.getNumStudents()) * 100;
                    b_perc = (b_counter / (double) Student.getNumStudents()) * 100;
                    c_perc = (c_counter / (double) Student.getNumStudents()) * 100;
                    d_perc = (d_counter / (double) Student.getNumStudents()) * 100;
                    f_perc = (f_counter / (double) Student.getNumStudents()) * 100;

                } else {
                    highest = 0;
                    lowest = 0;
                }
        if (Student.getNumStudents() == 0) {
            highest = 0;
            lowest = 0;
        }

        write.printf("     Average Score: %.2f\n", ave);
        write.printf("     Highest Score: %.2f\n", highest);
        write.printf("     Lowest Score:   %.2f\n", lowest);
        write.printf("     Total 'A' Grades: %d (%.2f%% of class)\n", a_counter, a_perc);
        write.printf("     Total 'B' Grades: %d (%.2f%% of class)\n", b_counter, b_perc);
        write.printf("     Total 'C' Grades: %d (%.2f%% of class)\n", c_counter, c_perc);
        write.printf("     Total 'D' Grades: %d (%.2f%% of class)\n", d_counter, d_perc);
        write.printf("     Total 'F' Grades: %d (%.2f%% of class)\n", f_counter, f_perc);
        write.println();
        return ave;
    }

    public double calculate_course( PrintWriter write) {
        return calculate_course(head, write);
    }

    private double calculate_course(Student p, PrintWriter write) {
        Student helpPtr = p;
        double ave = 0;
        int count = 0;
        double highest = -1;
        double lowest = 10000000;
        int a_counter = 0;
        int b_counter = 0;
        int c_counter = 0;
        int d_counter = 0;
        int f_counter = 0;
        double a_perc = 0;
        double b_perc = 0;
        double c_perc = 0;
        double d_perc = 0;
        double f_perc = 0;

        while (helpPtr != null) {

            ave += helpPtr.getFinalGrade();
            if (helpPtr.getFinalGrade() > highest) {
                highest = helpPtr.getFinalGrade();
            }
            if (helpPtr.getFinalGrade() < lowest) {
                lowest = helpPtr.getFinalGrade();
            }

            char letter = helpPtr.getLetterGrade();
            if (letter == 'A') {
                a_counter++;
            } else if (letter == 'B') {
                b_counter++;
            } else if (letter == 'C') {
                c_counter++;
            } else if (letter == 'D') {
                d_counter++;
            } else if (letter == 'F') {
                f_counter++;
            }

            if (Student.getNumStudents() > 0) {
                ave = (ave / countStudents());
                a_perc = (a_counter / (double) countStudents()) * 100;
                b_perc = (b_counter / (double) countStudents()) * 100;
                c_perc = (c_counter / (double) countStudents()) * 100;
                d_perc = (d_counter / (double) countStudents()) * 100;
                f_perc = (f_counter / (double) countStudents()) * 100;

            } else {
                highest = 0;
                lowest = 0;
            }

            helpPtr = helpPtr.getNext();
        }

        write.printf("     Average Score: %.2f\n", ave);
        write.printf("     Highest Score: %.2f\n", highest);
        write.printf("     Lowest Score: %.2f\n", lowest);
        write.printf("     Total 'A' Grades: %d (%.2f%% of class)\n", a_counter, a_perc);
        write.printf("     Total 'B' Grades: %d (%.2f%% of class)\n", b_counter, b_perc);
        write.printf("     Total 'C' Grades: %d (%.2f%% of class)\n", c_counter, c_perc);
        write.printf("     Total 'D' Grades: %d (%.2f%% of class)\n", d_counter, d_perc);
        write.printf("     Total 'F' Grades: %d (%.2f%% of class)\n", f_counter, f_perc);
        write.println();

        return ave;
    }

    public int countStudents() {
        return countStudents(head);
    }

    private int countStudents(Student p) {
        Student helpPtr = p;
        int count = 0;
        while (helpPtr != null) {

            count++;
            helpPtr = helpPtr.getNext();
        }
        return count;
    }

    public Student displayStudents( PrintWriter write) {
        return displayStudents(head,  write);
    }

    private Student displayStudents(Student p, PrintWriter write) {
        Student helpPtr = p;
        while (helpPtr != null) {

            write.println("	 " + helpPtr.getFirstName() + " " + helpPtr.getLastName() + " (ID# " + helpPtr.getID() + "):");

            write.println("		Exam 1:       " + helpPtr.getExamGrades()[0] + "\n"
                    + "		Exam 2:       " + helpPtr.getExamGrades()[1] + "\n"
                    + "		Final Exam:   " + helpPtr.getExamGrades()[2] + "");
            write.printf("        \tFinal Grade:  %.2f\n", helpPtr.getFinalGrade());
            write.println("		Letter Grade: " + helpPtr.getLetterGrade());

            helpPtr = helpPtr.getNext();
        }
        return null;
    }

    public Student findNodeByName(String fname, String lname) {
        return findNodeByName(head, fname, lname);
    }

    private Student findNodeByName(Student p, String fname, String lname) {
        Student helpPtr = p;
        while (helpPtr != null) {
            if (helpPtr.getFirstName().equals(fname) && helpPtr.getLastName().equals(lname)) {
                return helpPtr;
            }
            helpPtr = helpPtr.getNext();
        }
        return null;
    }

    public void delete(int id) {
        head = delete(head, id);
    }

    private Student delete(Student head, int id) {
        if (!isEmpty()) {

            if (head.getID() == id) {
                head = head.getNext();
            } else {
                Student helpPtr = head;
                while (helpPtr.getNext() != null) {
                    if (helpPtr.getNext().getID() == id) {
                        helpPtr.setNext(helpPtr.getNext().getNext());
                        break;
                    }
                    helpPtr = helpPtr.getNext();
                }
            }
            return head;
        }
        return head;
    }

    public Student getHead() {
        return head;
    }

    public void setHead(Student head) {
        this.head = head;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

}
