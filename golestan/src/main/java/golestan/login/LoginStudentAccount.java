package golestan.login;

import golestan.*;
import golestan.information.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginStudentAccount extends  NeedFunctions {
    public void LoginMain(Student std, ArrayList<Student> students, ArrayList<Lesson> lessons, ArrayList<Term> terms, TotalEducation education, ArrayList<Professor> professors) throws IOException {
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
                        Schedule(professors , std);
                    }
                    break;
                case '3':
                    if (education.isTermInProgress() && education.isNewTerm()) {
                        Register(lessons, terms, std);
                    }
                    break;
                case '4':
                    EditInfo(students , education , std);
                    break;
                case '5':
                    return;
                case '6':
                    quit(students , professors , education , lessons , terms);
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

    private static void Schedule (ArrayList<Professor> professors , Student std)
    {
        Scanner input = new Scanner(System.in);

        printDaySchedule(weekday.SATURDAY , findArray(std.getLessons() , weekday.SATURDAY) , professors);
        printDaySchedule(weekday.SUNDAY , findArray(std.getLessons() , weekday.SUNDAY) , professors);
        printDaySchedule(weekday.MONDAY , findArray(std.getLessons() , weekday.MONDAY) , professors);
        printDaySchedule(weekday.TUESDAY , findArray(std.getLessons() , weekday.TUESDAY) , professors);
        printDaySchedule(weekday.WEDNESDAY , findArray(std.getLessons() , weekday.WEDNESDAY) , professors);
        printDaySchedule(weekday.THURSDAY , findArray(std.getLessons() , weekday.THURSDAY) , professors);

        System.out.println("\n\n\n           Press any key to exit.");
        input.next();
    }

    ////////////// Registering
    private static void Register (ArrayList<Lesson> lessons , ArrayList<Term> terms , Student std ) {
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
            System.out.printf("\n" +
                    "\tLesson : " + l.getLessonName() + "\n" +
                    "\tProfessor : " + l.getProfessor() + "\n" +
                    "\tCollege : " + l.getCollegeL() + "\n" +
                    "\tUnit : " + l.getUnit() + "\n" +
                    "\tLesson's code : " + l.getLessonCode() + "\n"  +
                    "\tWeekday : " + l.getWeekdays() + "\n"     );
            System.out.printf("Start time : %.2f\n" , l.getStartTime());
            System.out.printf("End time : %.2f\n" , l.getEndTime() );
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
                 if (s.getUsername().equals(std.getStudentID())) {
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
                    System.out.print(counter + "_ " + l.getLessonName() + "      Professor : " + l.getProfessor() + "       code : " + l.getLessonCode() + "   weekday : " + l.getWeekdays() );
                    System.out.printf("    start : %.2f" , l.getStartTime());
                    System.out.printf("    end : %.2f\n" , l.getEndTime());
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
                                /// check for duplicate select
                                boolean checkDuplicate = false;
                                for (Lesson stdL : std.getLessons()) {
                                    if (stdL.getLessonCode().equals(code)) {
                                        checkDuplicate = true;
                                        break;
                                    }
                                }
                                /// check for time and weekday
                                boolean checkTime = false;
                                for (Lesson stdL : std.getLessons()) {
                                    if (stdL.getWeekdays().equals(l.getWeekdays())) {
                                        if (stdL.getStartTime() <= l.getStartTime() && stdL.getEndTime() >= l.getStartTime()) {
                                            checkTime = true;
                                        }
                                        if (stdL.getStartTime() <= l.getEndTime() && stdL.getEndTime() >= l.getEndTime()) {
                                            checkTime = true;
                                        }
                                    }
                                }
                                if ( !checkDuplicate && !checkTime) {
                                    l.addParticipant(std.getStudentID());
                                    std.addLessons(copyLesson(l));
                                }
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
                    lesson.deleteParticiple(std.getStudentID());
                    std.deleteLesson(lesson);
                } catch (Exception ignored) {}
            }
            //////////// exit
            else if (ch.charAt(0) == '3') {
                return;
            }
        }
    }
    //////////////////////////

    private static void EditInfo(ArrayList<Student> students, TotalEducation education, Student std) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ Edit UserName\n" +
                    "\t2_ Edit Password\n" +
                    "\t3_ Edit Total name" +
                    "\t4_ Edit Field" +
                    "\t5_ Edit College" +
                    "\t6_ Edit Entry year" +
                    "\t7_ Back\n"             );
            String ch = input.next();
            switch (ch.charAt(0)) {
                case '1' :
                    while ( true ) {
                        System.out.println("\n\n\n\n");
                        System.out.println("Enter your User name : ");
                        String stdUserName = input.next();
                        if (isDuplicateStudentID(students, stdUserName)) {
                            std.setUsername(stdUserName);
                            break;
                        }
                    }
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
                    while ( true ) {
                        System.out.println("\n\n\n\n");
                        System.out.println("Enter your college name : ");
                        String faculty = input.next();
                        // check if there is a college with input name
                        condition cnd = isContain(education.getFaculties() , faculty);
                        if (cnd == condition.TRUE) {
                            std.setCollege(faculty);
                            break;
                        } else if (cnd == condition.EXIT){
                            return;
                        }
                    }
                    break;
                case '6' :
                    while (true) {
                        System.out.println("\n\n\n\n\n");
                        System.out.println("Enter your Entry year : ");
                        // check for Wrong input (int & between 1338 and 1400)
                        int entryYear = isInputInt();
                        if (entryYear != 0) {              // if input is wrong the return of isInputInt() function is zero
                            if (std.setEntryYear(entryYear)) {
                                break;
                            }
                        }
                    }
                    break;
                case '7' :
                    return;
            }
        }
    }

    static Lesson copyLesson (Lesson lesson) {
        Lesson newsLesson = new Lesson();
        newsLesson.setLessonName(lesson.getLessonName());
        newsLesson.setProfessor(lesson.getProfessor());
        newsLesson.setCollegeL(lesson.getCollegeL());
        newsLesson.setLessonCode(lesson.getLessonCode());
        newsLesson.setStartTime(lesson.getStartTime());
        newsLesson.setEndTime(lesson.getEndTime());
        newsLesson.setWeekdays(lesson.getWeekdays());
        newsLesson.setUnit(lesson.getUnit());
        newsLesson.setParticipant(lesson.getParticipant());
        return newsLesson;
    }

    private static ArrayList<Lesson> findArray (ArrayList<Lesson> lessons , weekday day) {
        ArrayList<Lesson> help = new ArrayList<>();
        for (Lesson lesson : lessons) {
            if (lesson.getWeekdays().contains(day)) {
                help.add(lesson);
            }
        }
        return help;
    }

    private static void printDaySchedule (weekday day , ArrayList<Lesson> lessons , ArrayList<Professor> professors ) {
        System.out.println(" " + day + " ) ");
        while (lessons.size() != 0) {
            Lesson l = lessons.get(0);
            for (Lesson lesson : lessons) {
                if (lesson.getStartTime() < l.getStartTime()) {
                    l = lesson;
                }
            }

            // found professor name with username
            String professorName = "";
            for (Professor professor : professors) {
                if (professor.getUsername().equals(l.getProfessor())) {
                    professorName = professor.getTotalName();
                }
            }
            // print the lesson
            System.out.print(l.getLessonName() + "   Professor : " + professorName);
            System.out.printf("[%.2f , %.2f" , l.getStartTime() , l.getEndTime());
            lessons.remove(l);
        }

    }
}
