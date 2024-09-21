import java.util.ArrayList;

public class Lab7Ex1 {

  public static void main(String[] parms) {
    ArrayList<Student> students = new ArrayList<>();

    add(students, "Archie", "Andrews", 708, 3.21);
    add(students, "Chris", "Cross", 321, 4.28);
    add(students, "Danny", "Du", 899, 3.27);
    add(students, "Alexandra", "Andrews", 506, 4.02);
    add(students, "Betty", "Boschwitz", 102, 3.58);
    add(students, "Amelia", "Andrew", 325, 3.18);
    add(students, "Duplicate", "Student", 708, 1.11);
    // students.add(null, "Not Null", 1, 1.11);
    // students.add("Not Null", null, 2, 1.11);
    // students.add("Bad", "Number", -3, 1.11);
    // students.add("Bad", "GPA", 4, -1.11);

    System.out.println("Before sorting:");
    printList(students);
    
    sort(students);

    System.out.println("\nAfter sorting:");
    printList(students);

    System.out.println("\nEnd of processing.");
  }
  
  public static void add(ArrayList<Student> students, String first, String last, int num, double gpa) {
    students.add(new Student(first, last, num, gpa));
  }
  
  public static void sort(ArrayList<Student> students) {
    Student s;

    for (int i = 0; i < students.size(); i++) {
      for (int j = 0; j < students.size() - i - 1; j++) {
        if (students.get(j + 1).numberLessThanOther(students.get(j))) {
          s = students.get(j);
          students.set(j, students.get(j + 1));
          students.set(j + 1, s);
        }
      }
    }
  }

  public static void printList(ArrayList<Student> students) {
    for (int i = 0; i < students.size(); i++) {
      System.out.printf("%3d - %s\n", i + 1, students.get(i));
    }
  }
}

class Student {
  private String first, last;
  private int num;
  private double gpa;

  public Student(String first, String last, int num, double gpa) {
    this.first = first;
    this.last = last;
    this.num = num;
    this.gpa = gpa;
  }

  public boolean numberLessThanOther(Student other) {
    // If this student's number is less than the other student's
    // number, then this student comes before the other student.
    return num < other.num;
  }

  public String toString() {
    return String.format("%d: %s, %s (GPA=%.2f)", num, last, first, gpa);
  }
}
