package mid.student;

public class Student {
    private String name;
    private int studentID;
    private String unencryptedStudentPW;
    
    public Student(String firstName, String lastName, int studentID,
            String unencryptedStudentPW) {
        name = firstName + "\t" + lastName;
        this.studentID = studentID;
        this.unencryptedStudentPW = unencryptedStudentPW;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
        
        
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
    
    public int getStudentID() {
        return studentID;
    }
       
    public void setUnencryptedStudentPW(String unencryptedStudentPW) {
        this.unencryptedStudentPW = unencryptedStudentPW;
    }
    
    public String getUnencryptedStudentPW() {
        return unencryptedStudentPW;
    }
    
    public String encrpytStudentPW() {
        String encryptedStudentPW = unencryptedStudentPW;
        String firstLetter = name.substring(0, 1);
        String lastLetter = name.substring(name.length() - 1);
        String twoLetters = firstLetter + lastLetter;
        String firstTwoCharacters = encryptedStudentPW.substring(0, 2);
        double randomNum = Math.random() * 5 + 1;
        int ranNum = (int)randomNum;
        StringBuilder password = new StringBuilder();
        password.append(encryptedStudentPW);
        password.insert(5, firstTwoCharacters);
        password.delete(0, 2);
        password.insert(0, twoLetters);
        password.insert(5, "*");
        password.insert(encryptedStudentPW.length() + 2, Integer.toString(ranNum));
        
        encryptedStudentPW = password.toString();
        
        return encryptedStudentPW;
    }
}
