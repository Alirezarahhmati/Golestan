package golestan.login;

import golestan.*;
import golestan.information.*;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginTotalEducationAccount extends NeedFunctions {
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

    private static void NewFaculty(TotalEducation education) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the name of new Faculty : ");
            String nameFaculty = input.next();
            boolean checkDuplicate = false;
            for (String s : education.getFaculties()) {
                if (s.equals(nameFaculty)) {
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

    private static void EditInfo (TotalEducation education) {
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

    ///// semester
    private static void semester ( ArrayList<Student> students , ArrayList<Professor> professors , TotalEducation education , ArrayList<Lesson> lessons , ArrayList<Term> terms) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n\n\n\n" +
                    "\t1_ New Semester\n" +
                    "\t2_ Close Semester\n" +
                    "\t3_ Close Term\n" +
                    "\t4_ Exit\n"            );
            String ch = input.next();
            switch (ch.charAt(0)) {
                case '1' :
                    NewSemester(lessons ,education , professors);
                    break;
                case '2' :
                    closeSemester(education , students , lessons);
                    break;
                case '3' :
                    closeTerm(students , professors , education , lessons , terms);
                    break;
                case '4' :
                    return;
            }
        }
    }

    private static void NewSemester ( ArrayList<Lesson> lessons , TotalEducation education , ArrayList<Professor> professors)  {
        Scanner input = new Scanner(System.in);

        education.setTermInProgress(true);
        education.setNewTerm(true);

        //////////////// input information of lessons for new semester
        while (true) {
            // input name of lesson
            Lesson lesson = new Lesson();
            System.out.print("Enter the name of new lesson : ");
            String n = input.next();
            lesson.setLessonName(n);
            ///

            // input username of a professor for lesson
            while (true) {
                System.out.println("Enter user name of a professor account for new lesson : ");
                n = input.next();
                // check if there is a professor with input id
                if (isDuplicateProfessorUser(professors , n)) {
                    lesson.setProfessor(n);
                    break;
                } else {
                    System.err.println("There is not professor with input user name!");
                    System.out.println("Press e to exit or press any other key to continue.");
                    String ch = input.next();
                    if (ch.charAt(0) == 'e') {
                        return;
                    }
                }
            }

            // input faculty for lesson
            while (true) {
                System.out.println("Enter the college providing the lesson : ");
                n = input.next();
                // check if there is a college with input name
                condition c = isContain(education.getFaculties() , n);
                if (c == condition.TRUE) {
                    lesson.setCollegeL(n);
                    break;
                }else if (c == condition.EXIT) {
                    if (lesson.getLessonCode() == null) {
                        education.setTermInProgress(false);
                        education.setNewTerm(false);
                    }
                    return;
                }
            }

            // input code of lesson
            boolean checkDuplicate = true;
            while (checkDuplicate) {
                checkDuplicate = false;
                System.out.println("Enter lesson's code : ");
                n = input.next();
                /// check for duplicate input
                for (Lesson l : lessons) {
                    if (l.getLessonCode().equals(n)) {
                        System.err.println("Duplicate lesson's code!");
                        checkDuplicate = true;
                    }
                }
            }
            lesson.setLessonCode(n);

            // input unit of lesson
            while (true) {
                System.out.println("Enter the unit of this lesson : ");
                int unit = isInputInt();
                if ( unit != 0) {        // if input is wrong the return of isInputInt() function is zero
                    lesson.setUnit(unit);
                    break;
                }
            }

            // input time of lesson
            while (true) {
                System.out.println("Enter the start time of this lesson(between 7 and 20) : ");
                System.out.println("hour : ");
                int startHour = isInputInt();
                if (startHour < 7 || startHour > 20) {
                    System.err.println("Wrong input!");
                    continue;
                }
                System.out.println("minute : ");
                int startMinute = isInputInt();
                if (startMinute >= 60 || startMinute < 0) {
                    System.err.println("Wrong input!");
                    continue;
                }
                double startTime = startHour + ((double)startMinute / 100);

                System.out.println("Enter the end time of this lesson : ");
                System.out.println("hour : ");
                int endHour = isInputInt();
                if (endHour > 20 || endHour < 7) {
                    System.err.println("Wrong input!");
                    continue;
                }
                System.out.println("minute : ");
                int endMinute = isInputInt();
                if (endMinute < 0 || endMinute >= 60) {
                    System.err.println("Wrong input!");
                    continue;
                }
                double endTime = endHour + ((double)endMinute / 100);

                if (endHour < startHour) {
                    System.err.println("Your input end time is not match with start time!");
                    continue;
                }

                lesson.setStartTime(startTime);
                lesson.setEndTime(endTime);
                break;
            }

            // input the weekday of lesson
            boolean checkInput = false;
            while (!checkInput) {
                System.out.println("Enter one number for set weekday : ");
                System.out.println(   "1_ Saturday\n" +
                                    "\t2_ Sunday\n" +
                                    "\t3_ Monday\n" +
                                    "\t4_ Tuesday\n" +
                                    "\t5_ Wednesday\n" +
                                    "\t6_ Thursday\n"        );

                String ch = input.next();
                for (char c : ch.toCharArray()) {
                    switch (c) {
                        case '1' -> {
                            lesson.addWeekdays(weekday.SATURDAY);
                            checkInput = true;
                        }
                        case '2' -> {
                            lesson.addWeekdays(weekday.SUNDAY);
                            checkInput = true;
                        }
                        case '3' -> {
                            lesson.addWeekdays(weekday.MONDAY);
                            checkInput = true;
                        }
                        case '4' -> {
                            lesson.addWeekdays(weekday.TUESDAY);
                            checkInput = true;
                        }
                        case '5' -> {
                            lesson.addWeekdays(weekday.WEDNESDAY);
                            checkInput = true;
                        }
                        case '6' -> {
                            lesson.addWeekdays(weekday.THURSDAY);
                            checkInput = true;
                        }
                    }
                }
            }

            // print input information and cancel or add it
            System.out.println("\n\n\n\n\n" +
                    "\tLesson name : " + lesson.getLessonName() + "\n" +
                    "\tProfessor : " + lesson.getProfessor() + "\n" +
                    "\tCollege : " + lesson.getCollegeL() + "\n" +
                    "\tLesson's code : " + lesson.getLessonCode() + "\n" +
                    "\tUnit : " + lesson.getUnit() + "\n" +
                    "\tWeekday : " + lesson.getWeekdays() + "\n"  );
            System.out.printf("Start time : %.2f\n" , lesson.getStartTime());
            System.out.printf("End time : %.2f\n" , lesson.getEndTime());
            System.out.println("\tTo cancel saving this lesson press e and press any other key to confirm." );

            n = input.next();
            if (n.charAt(0) != 'e') {
                lessons.add(lesson);
            }

            System.out.println("To Exit press e and for add more lesson press any other key.");
            n = input.next();
            if (n.charAt(0) == 'e') {
                if (lesson.getLessonCode() == null) {
                    education.setTermInProgress(false);
                    education.setNewTerm(false);
                }
                return;
            }
        }
    }

    private static void closeTerm (ArrayList<Student> students , ArrayList<Professor> professors , TotalEducation education , ArrayList<Lesson> lessons , ArrayList<Term> terms) {
        Scanner input = new Scanner(System.in);
        // check if there is no term in progress
        if (!education.isNewTerm()) {
            System.out.println("There is not a term in progress.");
            System.out.println("Press any key to continue.");
            input.next();
            return;
        }

        // calculate grade point average of all student before close term
        for (Student std : students) {
            double sumGrade = 0;
            int counter = 0;
            for (Lesson l : std.getLessons()) {
                sumGrade += l.getStudent_score();
                counter++;
            }
            std.setGradePointAverage( sumGrade / counter);
        }

        // saving information of this term
        Term term = new Term();
        term.setStudents(students);
        term.setProfessors(professors);
        term.setLessons(lessons);

        terms.add(term);
        education.setTermInProgress(false);
    }

    private static void closeSemester (TotalEducation education , ArrayList<Student> students , ArrayList<Lesson> lessons) {

        for (Student std :  students) {
            int sumUnit = 0;
            for (Lesson l : std.getLessons()) {
                sumUnit += l.getUnit();
            }

            if (sumUnit < 12) {
//                ArrayList<Integer> indexOfDelete = new ArrayList<>();
//                for (Lesson stdL : std.getLessons()) {
//                    for (Lesson l : lessons) {
////                        Lesson lesson = null;
//                        if (l == stdL) {
////                            lesson = l;
//                            indexOfDelete.add(lessons.indexOf(l));
//                        }
////                        try {
////                            assert lesson != null;
////                            lesson.deleteParticiple(std.getStudentID());
////                        } catch (Exception ignored) {}
//                    }
//                }
//                for ( int i = 0 ; i < indexOfDelete.size(); i++) {
//                    lessons.get(indexOfDelete.get(i)).deleteParticiple(std.getStudentID());
//                }
                for (int i = 0; i < std.getLessons().size(); i++) {
                    for (int j = 0; j < lessons.size(); j++) {
                        if (std.getLessons().get(i) == lessons.get(j)) {
                            lessons.get(i).deleteParticiple(std.getStudentID());
                        }
                    }
                }
            }
        }
        education.setNewTerm(false);
    }
    ///////////
}
