import java.util.ArrayList;
import java.util.Scanner;

public class LoginTotalEducationAccount {
    public void LoginMain (TotalEducation education , ArrayList<Lesson> lessons) {
        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ New Semester\n" +
                    "\t2_ Make Faculty\n" +
                    "\t3_ Edit Info\n" +
                    "\t4_ Logout\n" +
                    "\t5_ Quit\n");

            Scanner input = new Scanner(System.in);
            String ch = input.next();
            switch (ch.charAt(0)) {
                case '1':
                    NewSemester(lessons);
                    break;
                case '2':
                    NewFaculty();
                    break;
                case '3':
                    EditInfo(education);
                    break;
                case '4':
                    return;
                case '5':
                    System.exit(0);
            }
        }
    }

    static void NewSemester ( ArrayList<Lesson> lessons ) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the name of new lesson : ");
            String n = input.next();

        }
    }

    static void NewFaculty () { }

    static void EditInfo (TotalEducation education) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ Edit UserName\n" +
                    "\t2_ Edit Password\n" +
                    "\t3_ Back\n"             );
            String ch = input.next();
            switch (ch.charAt(0)) {
                case '1' :
                    System.out.print("\n\n\n\n\n" +
                        "\tEnter your new User name : "   );
                    education.setUsername(input.next());
                    break;
                case '2' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new Password : "   );
                    education.setPassword(input.next());
                    break;
                case '3' :
                    return;
            }
        }
    }

}
