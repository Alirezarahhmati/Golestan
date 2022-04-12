import java.util.ArrayList;

import java.util.Scanner;
public class Main {
    public static void main(String[] args)  {

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Professor> professors = new ArrayList<>();
        TotalEducation education = new TotalEducation();
        ArrayList<Lesson> lessons = new ArrayList<>();
        ArrayList<Term> terms = new ArrayList<>();

        ///// page1 --> Login or SignUp or Exit
        while (true) {
            char ch = page1();
            switch (ch) {
                case '1' -> LoginFunction(students , professors , education , lessons ,terms);
                case '2' -> SignUpFunction(students, professors, education);
                case '3' -> {
                    return;
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

    static void LoginFunction ( ArrayList<Student> students , ArrayList<Professor> professors , TotalEducation education , ArrayList<Lesson> lessons , ArrayList<Term> terms){
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
                        a.LoginMain(std , lessons ,terms , education , professors);
                    }
                    break;
                case '2' :
                    Professor pr = Check_LoginTo_Professor_Account( professors );
                    if ( pr != null ) {
                        LoginProfessorAccount a = new LoginProfessorAccount();
                        a.LoginMain(pr , lessons , students);
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
                    Student_SignUp(students);
                    break;
                case '2':
                    Professor_SignUp(professors);
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

    static void Student_SignUp (ArrayList<Student> students)
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
            System.out.println("Enter your Student number : ");
            String stdNumber = input.next();
            boolean checkRepeat = false;
            for (Student s : students) {
                if (s.getStudentNumber().equals(stdNumber)) {
                    System.err.println("This Student Number is not correct.");
                    System.out.println("Press any key to continue.");
                    input.next();
                    checkRepeat = true;
                }
            }
            if ( !checkRepeat ) {
                std.setStudentNumber(stdNumber);
                check = true;
            }
        }

        System.out.print("\n");
        System.out.println("Enter your college name : ");
        std.setCollege(input.next());

        System.out.print("\n");
        System.out.println("Enter your Entry year : ");
        std.setEntryYear(input.next());

        check = false;
        while ( !check ) {
            System.out.print("\n");
            System.out.println("Enter your User name : ");
            String stdUserName = input.next();
            boolean checkRepeat = false;
            for (Student s : students) {
                if (s.getUsername().equals(stdUserName)) {
                    System.err.println("This User name is duplicate.");
                    System.out.println("Press any key to continue.");
                    input.next();
                    checkRepeat = true;
                }
            }
            if ( !checkRepeat ) {
                std.setUsername(stdUserName);
                check = true;
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
                            "\tStudent number : " + std.getStudentNumber() + "\n" +
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

    static void Professor_SignUp (ArrayList<Professor> professors)
    {
        Professor pr = new Professor();
        Scanner input = new Scanner(System.in);

        System.out.print("\n\n\n\n\n");
        System.out.println("Enter your college : ");
        pr.setCollegeP(input.next());

        System.out.print("\n\n\n\n\n");
        System.out.println("Enter your total name : ");
        pr.setTotalName(input.next());

        System.out.print("\n\n\n\n\n");
        System.out.println("Enter your gorup : ");
        pr.setGroup(input.next());

        boolean check = false;
        while ( !check ) {
            System.out.print("\n\n\n\n\n");
            System.out.println("Enter your User name : ");
            String prUserName = input.next();
            boolean checkRepeat = false;
            for (Professor p : professors) {
                if (p.getUsername().equals(prUserName)) {
                    System.err.println("This User name is duplicate.");
                    System.out.println("Press any key to continue.");
                    input.next();
                    checkRepeat = true;
                }
            }
            if ( !checkRepeat ) {
                pr.setUsername(prUserName);
                check = true;
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
}