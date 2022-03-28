import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class LoginTotalEducationAccount {
    public void LoginMain (ArrayList<Student> students , ArrayList<Professor> professors , TotalEducation education , ArrayList<Lesson> lessons , ArrayList<Term> terms) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ Semester\n" +
                    "\t2_ Make Faculty\n" +
                    "\t3_ Edit Info\n" +
                    "\t4_ Logout\n" +
                    "\t5_ Quit\n"            );

            String ch = input.next();
            switch (ch.charAt(0)) {
                case '1':
                    semester(students , professors , education , lessons , terms);
                    break;
                case '2':
                    NewFaculty(education);
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



    static void NewFaculty ( TotalEducation education ) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the name of new Faculty : ");
            String nameFaculty = input.next();
            boolean checkDuplicate = false;
            for (String s : education.getFaculties()) {
                if (s == nameFaculty) {
                    System.err.println("This Faculty its duplicate.");
                    checkDuplicate = true;
                }
            }
            if (!checkDuplicate) {
                education.addFaculties(nameFaculty);
            }
            System.out.println("For exit press e and for add more Faculty press any other key.");
            String ch = input.next();
            if (ch.charAt(0) == 'e') {
                return;
            }
        }
    }

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

    static void semester ( ArrayList<Student> students , ArrayList<Professor> professors , TotalEducation education , ArrayList<Lesson> lessons , ArrayList<Term> terms) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ New Semester" +
                    "\t2_ Close Semester" +
                    "\t3_ Exit"            );
            String ch = input.next();
            switch (ch.charAt(0)) {
                case '1' :
                    NewSemester(lessons);
                    break;
                case '2' :
                    closeSemester(students , professors , education , lessons , terms);
                    break;
                case '3' :
                    return;
            }
        }
    }

    static void NewSemester ( ArrayList<Lesson> lessons ) {
        Scanner input = new Scanner(System.in);
        if (lessons != null) {
            System.err.println("First you must close progress term.");
            System.out.println("Press any key to exit.");
            input.next();
        }

        while (true) {
            Lesson lesson = new Lesson();
            System.out.print("Enter the name of new lesson : ");
            String n = input.next();
            lesson.setLessonName(n);

            System.out.println("Enter a professor for new lesson : ");
            n = input.next();
            lesson.setProfessor(n);

            System.out.println("Enter the college providing the lesson : ");
            n = input.next();
            lesson.setCollegeL(n);

            System.out.println("Enter lesson's code : ");
            n = input.next();
            lesson.setLessonCode(n);

            System.out.println("Enter the unit of this lesson : ");
            int a = input.nextInt();
            lesson.setUnit(a);

            System.out.println("\n\n\n\n\n" +
                    "\tLesson name : " + lesson.getLessonName() + "\n" +
                    "\tProfessor : " + lesson.getProfessor() + "\n" +
                    "\tCollege : " + lesson.getCollegeL() + "\n" +
                    "\tLesson's code : " + lesson.getLessonCode() + "\n" +
                    "\tUnit : " + lesson.getUnit() + "\n" +
                    "\tTo cancel saving this lesson press e and press any other key to confirm." );

            n = input.next();
            if (n.charAt(0) != 'e') {
                lessons.add(lesson);
            }

            System.out.println("To Exit press e and for add more lesson press any other key.");
            n = input.next();
            if (n.charAt(0) == 'e') {
                return;
            }
        }
    }

    static void closeSemester (ArrayList<Student> students , ArrayList<Professor> professors , TotalEducation education , ArrayList<Lesson> lessons , ArrayList<Term> terms) {
        Scanner input = new Scanner(System.in);
        if (!education.isNewTerm()) {
            System.out.println("There is not a term in progress.");
            System.out.println("Press any key to continue.");
            input.next();
            return;
        }

        Term term = new Term();
        term.setStudents(students);
        term.setProfessors(professors);
        term.setLessons(lessons);

        terms.add(term);
    }
}
