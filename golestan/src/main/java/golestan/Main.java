package golestan;

import golestan.information.*;
import golestan.login.LoginProfessorAccount;
import golestan.login.LoginStudentAccount;
import golestan.login.LoginTotalEducationAccount;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends NeedFunctions {
    public static void main(String[] args) throws IOException {

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Professor> professors = new ArrayList<>();
        TotalEducation education = new TotalEducation();
        ArrayList<Lesson> lessons = new ArrayList<>();
        ArrayList<Term> terms = new ArrayList<>();

        readAllInformationOnFile(students , professors , education , lessons , terms);

        ///// page1 --> Login or SignUp or Exit
        while (true) {
            char ch = page1();
            switch (ch) {
                case '1' -> LoginFunction(students , professors , education , lessons ,terms);
                case '2' -> SignUpFunction(students, professors, education);
                case '3' -> {
                    quit(students , professors , education , lessons , terms);
                }
            }
        }
    }

    static char page1 () {
        System.out.print("\n\n\n\n\n");
        System.out.println("          1_ Login");
        System.out.println("          2_ Sign Up");
        System.out.println("          3_ Exit");

        Scanner input = new Scanner(System.in);
        String ch = input.next();
        return ch.charAt(0);
    }

    static void Exit () {
        System.exit(0);
    }

    static void LoginFunction(ArrayList<Student> students, ArrayList<Professor> professors, TotalEducation education, ArrayList<Lesson> lessons, ArrayList<Term> terms) throws IOException
    {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n\n\n\n");
            System.out.println("1_ Student");
            System.out.println("2_ Professor");
            System.out.println("3_ Total Education");
            System.out.println("4_ Back");

            String ch = input.next();
            switch ( ch.charAt(0) ) {
                case '1' :
                    Student std = Check_LoginTo_Student_Account(students);
                    if ( std != null )  {
                        LoginStudentAccount a = new LoginStudentAccount();
                        a.LoginMain(std , students , lessons ,terms , education , professors);
                    }
                    break;
                case '2' :
                    Professor pr = Check_LoginTo_Professor_Account( professors );
                    if ( pr != null ) {
                        LoginProfessorAccount a = new LoginProfessorAccount();
                        a.LoginMain(pr , professors , lessons , students , education , terms);
                    }
                    break;
                case '3' :
                    if ( Check_LoginTo_TotalEducation_Account( education )) {
                        LoginTotalEducationAccount a = new LoginTotalEducationAccount();
                        a.LoginMain(students , professors , education , lessons , terms);
                    }
                    break;
                case '4' :
                    return;
            }
        }
    }

    static void SignUpFunction (ArrayList<Student> students , ArrayList<Professor> professors , TotalEducation education) {
        while (true) {
            System.out.print("\n\n\n\n\n");
            System.out.println("          1_ Student");
            System.out.println("          2_ Professor");
            System.out.println("          3_ Total Education");
            System.out.print("\n\n");
            System.out.println("e : Exit          b : Back");

            Scanner input = new Scanner(System.in);
            String ch = input.next();
            switch (ch.charAt(0)) {
                case '1':
                    Student_SignUp(students , education);
                    break;
                case '2':
                    Professor_SignUp(professors , education);
                    break;
                case '3':
                    TotalEducation_SignUp(education);
                    break;
                case 'e':
                    Exit();
                case 'b':
                    return;
            }
        }
    }

    static void Student_SignUp (ArrayList<Student> students , TotalEducation education)
     {
        Scanner input = new Scanner(System.in);
        Student std = new Student();

        System.out.print("\n\n\n\n\n");
        System.out.println("Enter your total name : ");
        std.setTotalName(input.nextLine());

        System.out.print("\n");
        System.out.println("Enter your field : ");
        std.setField(input.nextLine());

        boolean check = false;
        while ( !check ) {
            System.out.print("\n");
            System.out.println("Enter your Student ID : ");
            String stdNumber = input.next();
            boolean checkRepeat = false;
            for (Student s : students) {
                if (s.getStudentID().equals(stdNumber)) {
                    System.err.println("This Student Number is not correct.");
                    System.out.println("Press any key to continue.");
                    input.next();
                    checkRepeat = true;
                }
            }
            if ( !checkRepeat ) {
                std.setStudentID(stdNumber);
                check = true;
            }
        }


        while ( true ) {
            System.out.print("\n");
            System.out.println("Enter your college name : ");
            String faculty = input.next();
            // check if there is a college with input name
            condition c = isContain(education.getFaculties() , faculty);
            if ( c == condition.TRUE) {
                std.setCollege(faculty);
                break;
            } else if ( c == condition.EXIT ) {
                return;
            }
        }

        while (true) {
            System.out.print("\n");
            System.out.println("Enter your Entry year : ");
            // check for Wrong input (int & between 1338 and 1400)
            int entryYear = isInputInt();
            if (entryYear != 0) {
                if ( std.setEntryYear(entryYear) ) {
                    break;
                }
            }
        }

        while ( true ) {
            System.out.print("\n");
            System.out.println("Enter your User name : ");
            String stdUserName = input.next();
            if (isDuplicateStudentID(students, stdUserName)) {
                std.setUsername(stdUserName);
                break;
            }
        }

        System.out.print("\n");
        System.out.println("Enter your password : ");
        std.setPassword(input.next());

         System.out.print("\033[H\033[2J");
         System.out.flush();

         // show input information to confirm it or delete it
         System.out.print(  "Your input information is : \n" +
                            "\tName and last name : " + std.getTotalName() + "\n" +
                            "\tStudent number : " + std.getStudentID() + "\n" +
                            "\tField : " + std.getField() + "\n" +
                            "\tCollege : " + std.getCollege() + "\n" +
                            "\tEntry year : " + std.getEntryYear() + "\n" +
                            "\tUser name : " + std.getUsername() + "\n" +
                            "\tpassword : " + std.getPassword() +
                            "\tpress  e : to cancel signing up      or press any key to confirm.");

         String s = input.next();
         if (s.charAt(0) != 'e' ){
             students.add(std);
         }
    }

    static void Professor_SignUp (ArrayList<Professor> professors , TotalEducation education)
    {
        Professor pr = new Professor();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("\n\n\n\n\n");
            System.out.println("Enter your college : ");
            String faculty = input.next();
            // check if there is college with input name
            condition cnd = isContain(education.getFaculties() , faculty);
            if ( cnd == condition.TRUE) {
                pr.setCollegeP(faculty);
                break;
            } else if ( cnd == condition.EXIT ) {
                return;
            }
        }

        System.out.print("\n\n\n\n\n");
        System.out.println("Enter your total name : ");
        pr.setTotalName(input.next());

        System.out.print("\n\n\n\n\n");
        System.out.println("Enter your group : ");
        pr.setGroup(input.next());

        while ( true ) {
            System.out.print("\n\n\n\n\n");
            System.out.println("Enter your User name : ");
            String prUserName = input.next();
            if ( !isDuplicateProfessorUser(professors , prUserName) ) {
                pr.setUsername(prUserName);
                break;
            } else {
                System.err.println("Wrong input!");
            }
        }

        System.out.print("\n\n\n\n\n");
        System.out.println("Enter your password : ");
        pr.setPassword(input.next());

        // show input information to confirm it or delete it
        System.out.print(  "Your input information is : \n" +
                "\tName and last name : " + pr.getTotalName() + "\n" +
                "\tGroup : " + pr.getGroup() + "\n" +
                "\tCollege : " + pr.getCollegeP() + "\n" +
                "\tUser name : " + pr.getUsername() + "\n" +
                "\tpassword : " + pr.getPassword() +
                "\tpress  e : to cancel signing up      or press any key to confirm.");

        String s = input.next();
        if (s.charAt(0) != 'e' ){
            professors.add(pr);
        }
    }

    static void TotalEducation_SignUp(TotalEducation education) {
        Scanner input = new Scanner(System.in);
        if ( education.getUsername() != null ) {
            if (Check_LoginTo_TotalEducation_Account(education)) {
                System.err.println("Do you sure to change your user name and password ? ");
                System.out.println("To exit press e and for continue press any other key");
                if (input.next().charAt(0) == 'e') {
                    return;
                }
            }
        }

        System.out.print("Enter User name : ");
        String userName = input.next();
        System.out.print("\nEnter password : ");
        String password = input.next();

        System.out.println("User name : " + userName);
        System.out.println("password : " + password);
        System.out.println("\n\n\n press e to cancel signing up and Back  or press any other key to continue.");
        if (input.next().charAt(0) != 'e' ) {
            education.setUsername(userName);
            education.setPassword(password);
        }
    }

    static Student Check_LoginTo_Student_Account (ArrayList<Student> students ) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your User Name : ");
        String userName = input.next();
        System.out.print("Enter your Password : ");
        String password = input.next();

        for (Student s : students) {
            if ( s.getUsername().equals(userName) && s.getPassword().equals(password) ) {
                return s;
            }
        }
        System.err.println("\nYour input user name or password is not correct!");
        System.out.println("press any key to continue");
        input.next();

        return null;
    }

    static Professor Check_LoginTo_Professor_Account (ArrayList<Professor> professors ) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your User Name : ");
        String userName = input.next();
        System.out.print("Enter your Password : ");
        String password = input.next();

        for (Professor p : professors) {
            if ( p.getUsername().equals(userName) && p.getPassword().equals(password) ) {
                return p;
            }
        }
        System.err.println("\nYour input user name or password is not correct!");
        System.out.println("press any key to continue");
        input.next();

        return null;
    }

    static boolean Check_LoginTo_TotalEducation_Account ( TotalEducation education ) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your User Name : ");
        String userName = input.next();
        System.out.print("Enter your Password : ");
        String password = input.next();
        try {
            if (education.getUsername().equals(userName) && education.getPassword().equals(password)) {
                return true;
            }
        }catch (Exception ignored) {}

        System.err.println("\nYour input user name or password is not correct!");
        System.out.println("press any key to continue");
        input.next();

        return false;
    }

    static void readAllInformationOnFile (ArrayList<Student> students , ArrayList<Professor> professors , TotalEducation education , ArrayList<Lesson> lessons , ArrayList<Term> terms) throws FileNotFoundException {
        File studentFile = new File("Student.txt");
        if (studentFile.exists()) {
            readStudentFile(students , studentFile);
        }

        File professorFile = new File("Professor.txt");
        if (professorFile.exists()) {
            readProfessorFile(professors , professorFile);
        }

        File educationFile = new File("TotalEducation.txt");
        if (educationFile.exists()) {
            readTotalEducationFile(education , educationFile);
        }
        
        File lessonFile = new File("Lesson.txt");
        if (lessonFile.exists()) {
            readLessonFile(lessons , lessonFile);
        }
        
        File termFile = new File("Term.txt");
        if (termFile.exists()) {
            readTermFile(terms , termFile);
        }
    }

    private static void readTermFile(ArrayList<Term> terms , File file) throws FileNotFoundException {
        Scanner myReader = new Scanner(file);

        while (myReader.hasNextLine()) {
            Term term = new Term();

            myReader.nextLine();
            readProfessorFile(term.getProfessors() , file);
            readStudentFile(term.getStudents() , file);
            readLessonFile(term.getLessons() , file);
            myReader.nextLine();
        }
    }

    private static void readLessonFile(ArrayList<Lesson> lessons, File lessonFile) throws FileNotFoundException {
        Scanner myReader = new Scanner(lessonFile);

        myReader.nextLine();
        String help = myReader.nextLine();
        while (!help.equals("]")) {
            Lesson lesson = new Lesson();

            if (!myReader.hasNextLine()) {
                break;
            }

            lesson.setLessonName(help);
            lesson.setProfessor(myReader.nextLine());
            lesson.setCollegeL(myReader.nextLine());
            lesson.setLessonCode(myReader.nextLine());
            try {
                lesson.setUnit(myReader.nextInt());
            }catch (Exception ignored) {}
            myReader.nextLine();
            try {
                lesson.setStudent_score(myReader.nextDouble());
            } catch (Exception ignored) {}
            myReader.nextLine();

            myReader.nextLine();
            while (true) {
                String s = myReader.nextLine();
                if (s.equals("}")) {
                    break;
                } else {
                    lesson.addParticipant(s);
                }
            }

            myReader.nextLine();
            lessons.add(lesson);
            help = myReader.nextLine();
        }
    }

    private static void readTotalEducationFile(TotalEducation education, File educationFile) throws FileNotFoundException {
        Scanner myReader = new Scanner(educationFile);
        if (!myReader.hasNextLine()) {
            return;
        }

        education.setUsername(myReader.nextLine());
        education.setPassword(myReader.nextLine());
        education.setNewTerm(myReader.nextBoolean());
        myReader.nextLine();
        education.setTermInProgress(myReader.nextBoolean());
        myReader.nextLine();

        myReader.nextLine();
        while (myReader.hasNextLine()) {
            String s = (myReader.nextLine());
            if (s.equals("}")) {
                break;
            }else {
                education.addFaculties(s);
            }
        }
        myReader.nextLine();
    }

    private static void readProfessorFile(ArrayList<Professor> professors, File professorFile) throws FileNotFoundException {
        Scanner myReader = new Scanner(professorFile);
        myReader.nextLine();
        String help = myReader.nextLine();
        while (!help.equals("}")) {
            Professor professor = new Professor();
            if (!myReader.hasNextLine()) {
                break;
            }

            professor.setUsername(help);
            professor.setPassword(myReader.nextLine());
            professor.setTotalName(myReader.nextLine());
            professor.setGroup(myReader.nextLine());
            professor.setCollegeP(myReader.nextLine());
            myReader.nextLine();

            professors.add(professor);
            help = myReader.nextLine();
        }
    }

    private static void readStudentFile(ArrayList<Student> students, File studentFile) throws FileNotFoundException {
        Scanner myReader = new Scanner(studentFile);

        myReader.nextLine();
        String help = myReader.nextLine();
        while (!help.equals("}")) {
            Student student = new Student();
            if (!myReader.hasNextLine()) {
                break;
            }

            student.setUsername(help);
            student.setPassword(myReader.nextLine());
            student.setTotalName(myReader.nextLine());
            student.setStudentID(myReader.nextLine());
            student.setField(myReader.nextLine());
            student.setCollege(myReader.nextLine());
            try {
                student.setEntryYear(myReader.nextInt());
            }catch (Exception ignored) {}
            myReader.nextLine();
            try {
                student.setGradePointAverage(myReader.nextDouble());
            }catch (Exception ignored) {}
            myReader.nextLine();
            myReader.nextLine();
//            readLessonFile(student.getLessons() , studentFile);
            /////////////////////////////////////////
            String help2 = myReader.nextLine();
            if (help2.equals("[")) {
                String help1 = myReader.nextLine();
                while (!help1.equals("]")) {
                    Lesson lesson = new Lesson();

                    if (!myReader.hasNextLine()) {
                        break;
                    }

                    lesson.setLessonName(help1);
                    lesson.setProfessor(myReader.nextLine());
                    lesson.setCollegeL(myReader.nextLine());
                    lesson.setLessonCode(myReader.nextLine());
                    try {
                        lesson.setUnit(myReader.nextInt());
                    } catch (Exception ignored) {
                    }
                    myReader.nextLine();
                    try {
                        lesson.setStudent_score(myReader.nextDouble());
                    } catch (Exception ignored) {
                    }
                    myReader.nextLine();

                    myReader.nextLine();
                    while (true) {
                        String s = myReader.nextLine();
                        if (s.equals("}")) {
                            break;
                        } else {
                            lesson.addParticipant(s);
                        }
                    }

                    myReader.nextLine();
                    student.addLessons(lesson);
                    help1 = myReader.nextLine();
                }
                myReader.nextLine();
            }
            ////////////////////////////////////////
            myReader.nextLine();

            students.add(student);
            help = myReader.nextLine();
        }
    }
}