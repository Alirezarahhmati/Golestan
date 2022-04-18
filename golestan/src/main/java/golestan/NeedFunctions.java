package golestan;

import golestan.information.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NeedFunctions {
    public enum condition {
        TRUE,
        WRONG,
        EXIT
    }
    protected static condition isContain(ArrayList<String> list, String find) {
        Scanner input = new Scanner(System.in);
        if (list.contains(find)) {
            return condition.TRUE;
        } else {
          System.err.println("Wrong input!");
            System.out.println("Press e for exit and press any other key to continue.");
            String n = input.next();
            if (n.charAt(0) == 'e') {
                return condition.EXIT;
            }
          return condition.WRONG;
        }
    }

    protected static int isInputInt() {
        Scanner input = new Scanner(System.in);
        int x = 0;
        try {
            x = input.nextInt();
        } catch (Exception e) {
            System.err.println("Wrong input!");
        }
        return x;
    }

    protected static boolean isDuplicateStudentID(ArrayList<Student> students, String studentUserName) {
        for (Student std : students) {
            if (std.getUsername().equals(studentUserName)) {
                System.err.println("This UserName is duplicate!");
                return false;
            }
        }
        return true;
    }

    protected static boolean isDuplicateProfessorUser(ArrayList<Professor> professors, String professorUserName) {
        Scanner input = new Scanner(System.in);

        for (Professor p : professors) {
            if (p.getUsername().equals(professorUserName)) {
                return true;
            }
        }
        return false;
    }
}
