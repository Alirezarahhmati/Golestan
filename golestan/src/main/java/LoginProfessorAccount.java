import java.util.ArrayList;
import java.util.Scanner;

public class LoginProfessorAccount {
    public void LoginMain (Professor pr , ArrayList<Lesson> lessons , ArrayList<Student> students) {
        Scanner input = new Scanner(System.in);
        System.out.println("       " + pr.getTotalName());

        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ Courses\n" +
                    "\t2_ Edit Info\n" +
                    "\t3_ Logout\n"     );
            String ch = input.next();

            switch (ch.charAt(0)) {
                case '1' :
                    courses(lessons , students ,pr.getUsername());
                    break;
                case '2' :
                    editInfo(pr);
                    break;
                case '3' :
                    return;
            }
        }
    }

    private static void courses (ArrayList<Lesson> lessons , ArrayList<Student> students , String professorUser) {
        Scanner input = new Scanner(System.in);
        ArrayList<Lesson> professorLessons = new ArrayList<>();

        for (Lesson l : lessons) {
            if (l.getProfessor().equals(professorUser)) {
                professorLessons.add(l);
            }
        }

        int counter = 1;
        for (Lesson l : professorLessons) {
            System.out.println("\n\n");
            System.out.println(counter + "_ " + l.getLessonName());
            counter++;
        }

        int in;
        String ch;
        while (true) {
            System.out.println("\n\n");
            System.out.println("Enter e for exit or enter one of top number for show the list of student.");
            ch = input.next();

            if (ch.charAt(0) == 'e') {
                return;
            }

            try {
                in = Integer.parseInt(ch);
                if (in > 0 && in <= professorLessons.size()) {
                    break;
                }
            } catch (Exception ignored) {}
        }

        // show the list of participle of chosen lesson
        int count_participle = 1;
        String chosen_lesson = "";
                for (String s : professorLessons.get(in-1).getParticiple()) {
                    chosen_lesson = professorLessons.get(in-1).getLessonName();
                    for (Student std : students) {
                        if (std.getStudentNumber().equals(s)) {
                            System.out.println("\n\n");
                            System.out.println(count_participle + "_ " + std.getTotalName());
                            count_participle++;
                        }
                    }
                }

        int enter;
        while (true) {
            System.out.println("\n\n");
            System.out.println("Enter e for exit or enter one of top for enter grade.");
            ch = input.next();

            if (ch.charAt(0) == 'e') {
                return;
            }

            try {
                enter = Integer.parseInt(ch);
                if (enter > 0 && enter <= professorLessons.get(in-1).getParticiple().size()) {
                    break;
                }
            } catch (Exception ignored) {}
        }

        String chosen_student = professorLessons.get(in-1).getParticiple().get(enter-1);
        for (Student std : students) {
            if (std.getStudentNumber().equals(chosen_student)) {
                for (Lesson l : std.getLessons()) {
                    if (l.getLessonName().equals(chosen_lesson)) {
                        int score;
                        while (true) {
                            try {
                                System.out.println(std.getTotalName() + "\n");
                                System.out.println("Enter number : ");
                                score = input.nextInt();
                                l.setStudent_score(score);
                                return;
                            } catch (Exception e) {
                                System.err.println("Wrong input.");
                                input.next();
                            }
                        }

                    }
                }
            }
        }
    }

    private  static void editInfo (Professor pr) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ Edit UserName\n" +
                    "\t2_ Edit Password\n" +
                    "\t3_ Edite Total name\n" +
                    "\t4_ Edite Group\n" +
                    "\t5_ Edite College\n" +
                    "\t6_ Exit\n"            );

            String ch = input.next();
            switch (ch.charAt(0)) {
                case '1' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new User name : "   );
                    pr.setUsername(input.next());
                    break;
                case '2' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new Password : "   );
                    pr.setPassword(input.next());
                    break;
                case '3' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new Total name : "   );
                    pr.setTotalName(input.next());
                    break;
                case '4' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new Group : "   );
                    pr.setGroup(input.next());
                    break;
                case '5' :
                    System.out.print("\n\n\n\n\n" +
                            "\tEnter your new College : "   );
                    pr.setCollegeP(input.next());
                    break;
                case '6' :
                    return;
            }
        }
    }
}
