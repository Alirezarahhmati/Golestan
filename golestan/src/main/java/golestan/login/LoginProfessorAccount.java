package golestan.login;

import golestan.*;
import golestan.information.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginProfessorAccount extends NeedFunctions {
    public void LoginMain(Professor pr, ArrayList<Professor> professors, ArrayList<Lesson> lessons, ArrayList<Student> students, TotalEducation education , ArrayList<Term> terms) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("       " + pr.getTotalName());

        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ Courses\n" +
                    "\t2_ Edit Info\n" +
                    "\t3_ Logout\n" +
                    "\t4_ Quit\n"      );
            String ch = input.next();

            switch (ch.charAt(0)) {
                case '1' :
                    courses(lessons , students ,pr.getUsername());
                    break;
                case '2' :
                    editInfo(professors , pr , education);
                    break;
                case '3' :
                    return;
                case '4' :
                    quit(students , professors , education , lessons , terms);
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

        // show the list of participant of chosen lesson
        int count_participle = 1;
        String chosen_lesson = "";
        for (String s : professorLessons.get(in-1).getParticipant()) {
            chosen_lesson = professorLessons.get(in-1).getLessonCode();
            for (Student std : students) {
                if (std.getStudentID().equals(s)) {
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
                if (enter > 0 && enter <= professorLessons.get(in-1).getParticipant().size()) {
                    break;
                }
            } catch (Exception ignored) {}
        }

        String chosen_student = professorLessons.get(in-1).getParticipant().get(enter-1);
        for (Student std : students) {
            if (std.getStudentID().equals(chosen_student)) {
                for (Lesson l : std.getLessons()) {
                    if (l.getLessonCode().equals(chosen_lesson)) {
                        int score;
                        while (true) {
                            try {
                                System.out.println(std.getTotalName() + "\n");
                                System.out.println("Enter number : ");
                                score = input.nextInt();
                                if ( score <= 20 && score >= 0) {
                                    l.setStudent_score(score);
                                } else {
                                    System.err.println("Wrong input.");
                                    input.next();
                                }
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

    private  static void editInfo(ArrayList<Professor> professors, Professor pr, TotalEducation education) {
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
                    while ( true ) {
                        System.out.print("\n\n\n\n\n");
                        System.out.println("Enter your User name : ");
                        String prUserName = input.next();
                        if ( !isDuplicateProfessorUser(professors , prUserName)) {
                            pr.setUsername(prUserName);
                            break;
                        } else {
                            System.err.println("Wrong input!");
                        }
                    }
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
                    while (true) {
                        System.out.print("\n\n\n\n\n");
                        System.out.println("Enter your college : ");
                        String faculty = input.next();
                        // check if there is college with input name
                        condition cond = isContain(education.getFaculties() , faculty);
                        if (cond == condition.TRUE) {
                            pr.setCollegeP(input.next());
                            break;
                        } else if (cond == condition.EXIT) {
                            return;
                        }
                    }
                    break;
                case '6' :
                    return;
            }
        }
    }

    private static void schedule (Professor pr , ArrayList<Lesson> lessons) {
        Scanner input = new Scanner(System.in);

        printDaySchedule(weekday.SATURDAY , findArray(lessons , pr.getUsername() , weekday.SATURDAY) , pr.getTotalName());
        printDaySchedule(weekday.SUNDAY , findArray(lessons , pr.getUsername() , weekday.SUNDAY) , pr.getTotalName());
        printDaySchedule(weekday.MONDAY , findArray(lessons , pr.getUsername() , weekday.MONDAY) , pr.getTotalName());
        printDaySchedule(weekday.TUESDAY , findArray(lessons , pr.getUsername() , weekday.TUESDAY) , pr.getTotalName());
        printDaySchedule(weekday.WEDNESDAY , findArray(lessons , pr.getUsername() , weekday.WEDNESDAY) , pr.getTotalName());
        printDaySchedule(weekday.THURSDAY , findArray(lessons , pr.getUsername() , weekday.THURSDAY) , pr.getTotalName());

        System.out.println("press any key to exit.");
        input.next();
    }

    private static void printDaySchedule (weekday day , ArrayList<Lesson> lessons , String professorName) {
        System.out.println(" " + day + " ) ");
        while (lessons.size() != 0) {
            Lesson l = lessons.get(0);
            for (Lesson lesson : lessons) {
                if (lesson.getStartTime() < l.getStartTime()) {
                    l = lesson;
                }
            }

            // print the lesson
            System.out.print(l.getLessonName() + "   Professor : " + professorName);
            System.out.printf("[%.2f , %.2f" , l.getStartTime() , l.getEndTime());
            lessons.remove(l);
        }

    }

    private static ArrayList<Lesson> findArray (ArrayList<Lesson> lessons , String username , weekday day) {
        ArrayList<Lesson> help = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getProfessor().equals(username) && lesson.getWeekdays().contains(day)) {
                help.add(lesson);
            }
        }
        return help;
    }
}
