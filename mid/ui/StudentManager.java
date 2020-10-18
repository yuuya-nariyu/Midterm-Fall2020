package mid.ui;

/*
Isaac Ojeda COSC - 2336 10/16/2020
This program Displays a menu of commonds that can be used to edit a file
first checking if the file exsit and if not creates the file there are
validations in place to keep the program running in case of user error.
*/
import java.util.List;
import mid.db.DAO;
import mid.db.DAOException;
import mid.db.StudentTextFile;
import mid.student.Student;

public class StudentManager {
    private static DAO<Student> file;
    
    public static void main(String args[]) {
        try {
            file = new StudentTextFile();
        } catch (DAOException e) {
            System.out.println("Error reading product data.");
            System.out.println(e.getMessage());
            System.out.println("Exiting application.\n");
            System.exit(0);
        }
        
        System.out.println("Welcome to the Student Manager\n");
        displayMenu();
        
        String action = "";
        while (!action.equalsIgnoreCase("exit")) {
            // get the input from the user
            action = Console.getString("Enter a command: ");
            System.out.println();

            if (action.equalsIgnoreCase("encrypted")) {
                displayEncryptedList();
            }else if (action.equalsIgnoreCase("unencrypted")) {
                displayUnEncryptedList();
            } else if (action.equalsIgnoreCase("add")) {
                addStudent();
            } else if (action.equalsIgnoreCase("delete")) {
                deleteStudent();
            } else if (action.equalsIgnoreCase("help")) {
                displayMenu();
            } else if (action.equalsIgnoreCase("exit")) {
                System.out.println("Bye.\n");
            } else {
                System.out.println("Error! Not a valid command.\n");
            }
        }
    }
    
        public static void displayMenu() {
        //Help menu for the user.
        System.out.println("COMMAND MENU");
        System.out.println("add   - Add a product");
        System.out.println("update   - List all products");
        System.out.println("delete   - Delete a product");
        System.out.println("help   - Show this menu");
        System.out.println("encrypted   - List all students and encrypted password");
        System.out.println("unencrypted   - List all students and unencrypted password");
        System.out.println("exit    - Exit this application\n");
    }
        
    public static void displayEncryptedList() {
        
    }
    
    public static void displayUnEncryptedList() {
        System.out.println("unencrypted list");
        
        List<Student> students;
        try {
            students = file.getAll();
        } catch (DAOException e) {
            System.out.println("Error unable to get list.");
            return;
        }
        
        Student s;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < students.size(); i++) {
            s = students.get(i);
            sb.append(s.getName());
            sb.append(s.getStudentID());
            sb.append(s.getUnencryptedStudentPW());
        }
        System.out.println(sb.toString());
        
    }
    
    public static void addStudent() {
        String firstName = Console.getString("Enter student's first name: ");
        String lastName = Console.getString("Enter student's last name: ");
        int studentID = Console.getInt("Enter student ID number: ");
        if(studentID < 10000){
            System.out.println("Error! Number must be greater than 9999.");
            Console.getInt("Enter student ID number: ");
        }else if(studentID > 99999) {
            System.out.println("Error! Number must be less than 100000.");
            Console.getInt("Enter student ID number: ");
        }
        String unEncryptedPW = Console.getString("Enter student's password: ");
        if(unEncryptedPW.length() < 7) {
            System.out.println("Error! Password must be greater than 7");
            Console.getString("Enter student's password: ");
        }else if(unEncryptedPW.length() > 10) {
            System.out.println("Error! Password must be less than 11");
            Console.getString("Enter student's password: ");
        }
        
        Student s = new Student(firstName, lastName, studentID, unEncryptedPW);
        
        try {
            file.add(s);
        } catch (DAOException ex) {
            System.out.println("Error Unable to add student.");
        }
        
        System.out.println(firstName + "\t" + lastName + " has been added.");
        
    }
    
    public static void deleteStudent() {
        String studentID = Console.getString("Enter student's ID to delete: ");
        
        try {
            Student s = file.get(studentID);
            if (s == null) {
                System.out.println("Error! No student has this ID");
            } else {
                file.delete(s);
                System.out.println(s.getName() + " has been deleted.");
            }
        } catch (DAOException e) {
            System.out.println("Error deleting product.");
        }
    }
    
    
}
