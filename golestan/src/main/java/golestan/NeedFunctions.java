package golestan;

import golestan.information.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class NeedFunctions {
    public enum condition {
        TRUE,
        WRONG,
        EXIT
    }

    public enum weekday {
        SATURDAY,
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY
    }
    protected static condition isContain(ArrayList<String> list, String find) {
        Scanner input = new Scanner(System.in);
        if (list.contains(find)) {
            return condition.TRUE;
        } else {
          System.err.println("Wrong input!");
            System.out.println("Press e for exit and press any other key to continue.");
            String n = input.next();
            if (n.charAt(0) == 'e') {
                return condition.EXIT;
            }
          return condition.WRONG;
        }
    }

    protected static int isInputInt() {
        Scanner input = new Scanner(System.in);
        int x = 0;
        try {
            x = input.nextInt();
        } catch (Exception e) {
            System.err.println("Wrong input!");
        }
        return x;
    }

    protected static boolean isDuplicateStudentID(ArrayList<Student> students, String studentUserName) {
        for (Student std : students) {
            if (std.getUsername().equals(studentUserName)) {
                System.err.println("This UserName is duplicate!");
                return false;
            }
        }
        return true;
    }

    protected static boolean isDuplicateProfessorUser(ArrayList<Professor> professors, String professorUserName) {
        Scanner input = new Scanner(System.in);

        for (Professor p : professors) {
            if (p.getUsername().equals(professorUserName)) {
                return true;
            }
        }
        return false;
    }


    ////////    quit and save all information in file
    protected static void quit (ArrayList<Student> students , ArrayList<Professor> professors , TotalEducation education , ArrayList<Lesson> lessons , ArrayList<Term> terms) throws IOException {
        File studentFile = new File("Student.txt");
        makeEmptyFile(studentFile);
        writeStudentInformationOnFile(students , studentFile);

        File professorFile = new File("Professor.txt");
        makeEmptyFile(professorFile);
        writeProfessorInformationOnFile(professors , professorFile);

        File educationFile = new File("TotalEducation.txt");
        makeEmptyFile(educationFile);
        writeEducationInformationOnFile(education , educationFile);

        File lessonFile = new File("Lesson.txt");
        makeEmptyFile(lessonFile);
        writeLessonInformationOnFile(lessons , lessonFile);

        File termFile = new File("Term.txt");
        makeEmptyFile(termFile);
        writeTermInformationOnFile(terms , termFile);

        System.exit(0);
    }

    private static void writeStudentInformationOnFile(ArrayList<Student> students, File studentFile) throws IOException{
        // now we write all information on empty file
        FileWriter myWriter = new FileWriter(studentFile);
        myWriter.write("{\n");
        for (Student student : students) {
            myWriter.write(student.getUsername() + "\n" + student.getPassword() + "\n" + student.getTotalName() + "\n"
                    + student.getStudentID() + "\n" + student.getField() + "\n" + student.getCollege() + "\n" + student.getEntryYear() + "\n"
                    + student.getGradePointAverage() + "\n");

            myWriter.write("{\n");
            ////////////////////////////////////// write lesson
            myWriter.write("[\n");
            for (Lesson lesson : student.getLessons()) {
                myWriter.write(lesson.getLessonName() + "\n" + lesson.getProfessor() + "\n" + lesson.getCollegeL() + "\n"
                        + lesson.getLessonCode() + "\n" + lesson.getUnit() + "\n" + lesson.getStudent_score() + "\n" + lesson.getStartTime() + "\n"
                        + lesson.getEndTime() + "\n");

                myWriter.write("{\n");
                for (String s : lesson.getParticipant()) {
                    myWriter.write(s + "\n");
                }
                myWriter.write("}\n");

                myWriter.write("{\n");
                for (weekday weekday : lesson.getWeekdays()) {
                    myWriter.write(weekday.toString() + "\n");
                }
                myWriter.write("}\n");

                myWriter.write("\n");
            }
            myWriter.write("]\n");
            myWriter.write("}\n");
            //////////////////////////////////////////

            myWriter.write("\n");
        }
        myWriter.write("}\n");
        myWriter.close();
    }

    private static void writeProfessorInformationOnFile(ArrayList<Professor> professors, File professorFile) throws IOException {
        // now we write all information on empty file
        FileWriter myWriter = new FileWriter(professorFile);
        myWriter.write("{\n");
        for (Professor professor : professors) {
            myWriter.write(professor.getUsername() + "\n" + professor.getPassword() + "\n" + professor.getTotalName() + "\n"
                    + professor.getGroup() + "\n" + professor.getCollegeP() + "\n");

            myWriter.write("\n");
        }
        myWriter.write("}\n");
        myWriter.close();
    }

    private static void writeEducationInformationOnFile(TotalEducation education, File educationFile) throws IOException {
        // now we write all information on empty file
        FileWriter myWriter = new FileWriter(educationFile);
        myWriter.write(education.getUsername() + "\n" + education.getPassword() + "\n" + education.isNewTerm() + "\n" +
                education.isTermInProgress() + "\n");

        myWriter.write("{\n");
        for (String faculty : education.getFaculties()) {
            myWriter.write(faculty + "\n");
        }
        myWriter.write("}\n");
        myWriter.write("\n");
        myWriter.close();
    }

    private static void writeLessonInformationOnFile(ArrayList<Lesson> lessons, File lessonFile) throws IOException {
        // now we write all information on empty file
        FileWriter myWriter = new FileWriter(lessonFile);

        myWriter.write("[\n");
        for (Lesson lesson : lessons) {
            myWriter.write(lesson.getLessonName() + "\n" + lesson.getProfessor() + "\n" + lesson.getCollegeL() + "\n"
                    + lesson.getLessonCode() + "\n" + lesson.getUnit() + "\n" + lesson.getStudent_score() + "\n" + lesson.getStartTime() + "\n"
                    + lesson.getEndTime() + "\n");

            myWriter.write("{\n");
            for (String s : lesson.getParticipant()) {
                myWriter.write(s + "\n");
            }
            myWriter.write("}\n");

            myWriter.write("{\n");
            for (weekday weekday : lesson.getWeekdays()) {
                myWriter.write(weekday.toString() + "\n");
            }
            myWriter.write("}\n");

            myWriter.write("\n");
        }
        myWriter.write("]\n");
        myWriter.close();
    }

    private static void writeTermInformationOnFile(ArrayList<Term> terms, File termFile) throws IOException {
        FileWriter myWriter = new FileWriter(termFile);
        // write all information in Term
        for (Term term : terms) {
            myWriter.write("Term[\n");
            writeProfessorInformationOnFile(term.getProfessors(), termFile);
            writeStudentInformationOnFile(term.getStudents(), termFile);
            writeLessonInformationOnFile(term.getLessons(), termFile);
            myWriter.write("]Term\n");
        }

        myWriter.close();
    }

    protected static void makeEmptyFile (File file) {
        // create an empty file
        try {
            if (!file.createNewFile()) {             /// check if there is a file so delete it and make another
                file.delete();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

}
