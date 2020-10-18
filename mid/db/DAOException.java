package mid.db;

public class DAOException extends Exception{
    DAOException() {}
    
    DAOException(Exception ex) {
        super(ex);
    }
    
}
