import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LoginStudentAccount {
    public void LoginMain (Student std , ArrayList<Lesson> lessons , ArrayList<Term> terms , TotalEducation education , ArrayList<Professor> professors) {
        Scanner input = new Scanner(System.in);
        System.out.println("         " + std.getTotalName() + "\n");

        if (!education.isTermInProgress()) {
            System.out.println("There is not term in progress,\n" +
                    "\t   because of that option 1 , 2 ans 3 are not available.");
        }

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
                    if (education.isTermInProgress()) {
                        Score(std);
                    }
                    break;
                case '2':
                    if (education.isTermInProgress()) {
                        Schedule(std.getLessons() , professors , std);
                    }
                    break;
                case '3':
                    if (education.isTermInProgress() && education.isNewTerm()) {
                        Register(education, lessons, terms, std);
                    }
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

    private static void Score (Student std) {
        Scanner input = new Scanner(System.in);
        for (Lesson l : std.getLessons()) {
            System.out.println("\n\n");
            System.out.print(l.getLessonName() + " Score : ");
            if (l.getStudent_score() != -1) {
                System.out.print(l.getStudent_score() + "\n");
            } else {
                System.out.println("The grade for this lesson is not given!");
            }
        }
        System.out.println("\n\n\n Press any key to exit.");
        input.next();
    }

    private static void Schedule (ArrayList<Lesson> lessons , ArrayList<Professor> professors , Student std) {
        Scanner input = new Scanner(System.in);
//        String professor = "";
//        for (Lesson l : lessons) {
//            for (Professor pr : professors) {
//                if (l.getProfessor().equals(pr.getUsername())) {
//                    professor = pr.getTotalName();
//                }
//            }
//            System.out.println("\n\n");
//            System.out.println("_ " + l.getLessonName() + "          Professor : " + professor);
//        }
//        System.out.println("\n\n\n          Press any key to exit.");
//        input.next();

        for (Lesson l : std.getLessons()) {
            for (Professor pr : professors ) {
                if (l.getProfessor().equals(pr.getUsername())) {
                    System.out.println("\n");
                    System.out.println("_ " + l.getLessonName() + "          professor : " + pr.getTotalName());
                }
            }
        }
        System.out.println("\n\n\n           Press any key to exit.");
        input.next();
    }

    /////////////////////////////// Registering
    private static void Register (TotalEducation education , ArrayList<Lesson> lessons , ArrayList<Term> terms , Student std ) {
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
                    selectLesson(lessons ,terms ,std);
                    break;
                case '3' :
                    return;
            }
        }
    }

    private static void availableLessons( ArrayList< Lesson> lessons ) {
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

    private static void selectLesson ( ArrayList<Lesson> lessons , ArrayList<Term> terms , Student std ) {
        Scanner input = new Scanner(System.in);

        // check grade point average of last term for max of Unit(24 or 20)
         int maxUnit = 20;
         int len = terms.size();
         try {
             for (Student s : terms.get(len - 1).getStudents()) {
                 if (s.getUsername().equals(std.getStudentNumber())) {
                     if (s.getGradePointAverage() >= 18) {
                         maxUnit = 24;
                     }
                 }
             }
         } catch (Exception ignored) {}

         // select lesson 
        while (true) {
            int sumUnit = 0;

            // show the list of selected lessons
            int counter = 1;
            try {
                for (Lesson l : std.getLessons()) {
                    System.out.println(counter + "_ " + l.getLessonName() + "       Professor : " + l.getProfessor() + "            code : " + l.getLessonCode());
                    counter++;
                    sumUnit += l.getUnit();
                }
                System.out.println("\nTotal Unit : " + sumUnit);
                System.out.println("\n\n\n\n");
            } catch (Exception ignored) {}

            // select lesson
            System.out.println("   Select Lesson");
            System.out.print("Enter lesson's code : ");
            String code = input.next();
            System.out.println("\n1_Add          2_ Delete          3_ Exit");
            String ch = input.next();

            ////////////add lesson
            if (ch.charAt(0) == '1') {
                    for (Lesson l : lessons) {
                        if (l.getLessonCode().equals(code)) {
                            if ((sumUnit + l.getUnit()) <= maxUnit) {
                                l.addParticiple(std.getStudentNumber());
                                std.addLessons(l);
                            }
                            break;
                        }
                    }
            }
            /////////// remove lesson
            else if (ch.charAt(0) == '2') {
                Lesson lesson = null;
                for (Lesson l : lessons) {
                    if (l.getLessonCode().equals(code)) {
                        lesson = l;
                        break;
                    }
                }
                try {
                    assert lesson != null;
                    lesson.deleteParticiple(std.getStudentNumber());
                    std.deleteLesson(lesson);
                } catch (Exception ignored) {}

            }
            //////////// exit
            else if (ch.charAt(0) == '3') {
                return;
            }
        }
    }
    ///////////////////////////////////////////////////////////

    private static void EditInfo (Student std) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ Edit UserName\n" +
                    "\t2_ Edit Password\n" +
                    "\t3_ Edit Total name" +
                    "\t4_ Edit Field" +
                    "\t5_ Edit College" +
                    "\t6_ Edit Entry year" +
                    "\t7_ Edit Student Number" +
                    "\t8_ Back\n"             );
            String ch = input.next();
            switch (ch.charAt(0)) {
                case '1' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new User name : "   );
                    std.setUsername(input.next());
                    break;
                case '2' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new Password : "   );
                    std.setPassword(input.next());
                    break;
                case '3' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new Total name : "   );
                    std.setTotalName(input.next());
                    break;
                case '4' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new Field : "   );
                    std.setField(input.next());
                    break;
                case '5' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new College : "   );
                    std.setCollege(input.next());
                    break;
                case '6' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new Entry Year : "   );
                    std.setEntryYear(input.next());
                    break;
                case '7' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new Student Number : "   );
                    std.setStudentNumber(input.next());
                    break;
                case '8' :
                    return;
            }
        }
    }
}
