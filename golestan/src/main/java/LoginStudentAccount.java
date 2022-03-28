import java.util.ArrayList;
import java.util.Scanner;

public class LoginStudentAccount {
    public void LoginMain (Student std , ArrayList<Lesson> lessons , ArrayList<Term> terms) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ Scores\n" +
                    "\t2_ Schedule\n" +
                    "\t3_ Register\n" +
                    "\t4_ Edit Info\n" +
                    "\t5_ Logout\n" +
                    "\t6_ Quit\n");


            String ch = input.next();
            switch (ch.charAt(0)) {
                case '1':
                    Score();
                    break;
                case '2':
                    Schedule();
                    break;
                case '3':
                    Register(lessons ,terms , std.getStudentNumber());
                    break;
                case '4':
                    EditInfo(std);
                    break;
                case '5':
                    return;
                case '6':
                    System.exit(0);
            }
        }
    }

    static void Score () {

    }

    static void Schedule () {

    }

    /////////////////////////////// Registering
    static void Register ( ArrayList<Lesson> lessons , ArrayList<Term> terms , String studentNumber ) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ Available Lessons\n" +
                    "\t2_ Select lessons\n" +
                    "\t3_ Back\n"            );

            String ch = input.next();
            switch (ch.charAt(0)) {
                case '1' :
                    availableLessons(lessons);
                    break;
                case '2' :
                    selectLesson(lessons ,terms ,studentNumber);
                    break;
                case '3' :
                    return;
            }
        }
    }

    static void availableLessons( ArrayList<Lesson> lessons ) {
        Scanner input = new Scanner(System.in);
        for (Lesson l : lessons) {
            System.out.println("____________________________________________________________________");
            System.out.print("\n" +
                    "\tLesson : " + l.getLessonName() + "\n" +
                    "\tProfessor : " + l.getProfessor() + "\n" +
                    "\tTime : " + l.getTime() + "\n" +
                    "\tCollege : " + l.getCollegeL() + "\n" +
                    "\tUnit : " + l.getUnit() + "\n" +
                    "\tLesson's code : " + l.getLessonCode() + "\n"  );
        }

        System.out.println("\n\n");
        System.out.println("Press any key to exit.");
        input.next();
    }

    static void selectLesson ( ArrayList<Lesson> lessons , ArrayList<Term> terms , String studentNumber ) {
        Scanner input = new Scanner(System.in);

        terms.
        while (true) {
            int sumUnit = 0;
            int counter = 1;
            for (Lesson l : lessons) {
                for (String s : l.getParticiple()) {
                    if (s == studentNumber) {
                        System.out.println(counter + "_ " + l.getLessonName() + "       Professor : " + l.getProfessor() + "            code : " + l.getLessonCode());
                        counter++;
                        sumUnit += l.getUnit();
                    }
                }
            }
            System.out.println("\nTotoal Unit : " + sumUnit);
            System.out.println("\n\n\n\n");

            System.out.println("   Select Lesson");
            System.out.print("Enter lesson's code : ");
            String code = input.next();
            for (Lesson l: lessons) {
                if (l.getLessonCode().equals( code )) {
                    l.addParticiple(studentNumber);
                    break;
                }
            }

        }
    }
    ///////////////////////////////////////////////////////////

    static void EditInfo (Student std) {

    }
}
