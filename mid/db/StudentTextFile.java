package mid.db;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import mid.student.Student;

public class StudentTextFile implements DAO<Student>{
    private List<Student> students = null;
    private Path studentPath = null;
    private File studentFile = null;
    private final String step = "\t";
    
    public StudentTextFile() throws DAOException {
        studentPath = Paths.get("student.txt");
        studentFile = studentPath.toFile();
        students = this.getAll();
    }

    @Override
    public List<Student> getAll() throws DAOException {
        if(students != null) {
            return students;
        }
        
        students = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader(studentFile))) {
            
            String line = in.readLine();
            while(line != null){
                String[] Student = line.split(step);
                String firstName = Student[0];
                String lastName = Student[1];
                String studentID = Student[2];
                String unencryptedStudentPW = Student[3];
                //String encryptedStudentPW = Student[4];
                
                Student s;
                s = new Student(firstName, lastName, Integer.parseInt(studentID),
                unencryptedStudentPW);
                
                line = in.readLine();
            }
        } catch (IOException e) {
            System.out.println("The student.txt file doesn't exist.");
            System.out.println("Creating student.txt file");
            studentFile = new File("student.txt");
            try {
                studentFile.createNewFile();
            } catch (IOException ex) {
                System.out.println("Unable to create file.");
            }
            throw new DAOException(e);
        }
        
        return students;
    }

    @Override
    public boolean add(Student t) throws DAOException {
        students.add(t);
        return this.saveAll();
    }

    @Override
    public boolean delete(Student t) throws DAOException {
        students.remove(t);
        return this.saveAll();
    }

    @Override
    public boolean update(Student t) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean saveAll() throws DAOException {
        try (PrintWriter out = new PrintWriter(
                               new BufferedWriter(
                               new FileWriter(studentFile)))) {
            
            for(Student s : students){
                out.print(s.getName() + step);
                out.print(s.getStudentID() + step);
                out.print(s.getUnencryptedStudentPW());
                
            }

            // write all products in the array list
            // to the file
            return true;            
        } catch (IOException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Student get(String studentID) throws DAOException {
        for(Student s: students) {
            if(Integer.toString(s.getStudentID()).equals(studentID)){
                return s;
            }
        }
        return null;
    }
    
}
