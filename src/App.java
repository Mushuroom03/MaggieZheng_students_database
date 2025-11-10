import java.sql.*;

public class App {

    private final PostgreConnection psqlConn;

    // Constructor
    public App(PostgreConnection connection) { this.psqlConn = connection; }

    // Retrieve and print out all the students.
    public void getAllStudents() throws SQLException {
        String SQL = "SELECT * FROM students ORDER BY student_id";
        // Connect to PostgreSQL using psqlConn variable
        try (Connection conn = psqlConn.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery()) {
                //Format for nice output
                System.out.print("-------------------------------------------------------------------------------------------------------\n");
                System.out.printf("| %-12s | %-15s | %-15s | %-30s | %-12s |%n", 
                "student_id", 
                "first_name", 
                "last_name", 
                "email", 
                "enrollment_date");
                System.out.print("-------------------------------------------------------------------------------------------------------\n");
                // Print out all existing rows in table
                while (rs.next()){
                    System.out.printf("| %-12d | %-15s | %-15s | %-30s | %-15s |%n", 
                    rs.getInt("student_id"),
                    rs.getString("first_name"), 
                    rs.getString("last_name"), 
                    rs.getString("email"), 
                    rs.getDate("enrollment_date"));
                }
                System.out.print("-------------------------------------------------------------------------------------------------------\n");
            }
        
    }

    // Add student to students table
    public void addStudent(String first_name, String last_name, String email, Date date) throws SQLException {
        // SQL Query
        String SQL = "INSERT INTO students(first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
        // Connect using psqlConn
        try (Connection conn = psqlConn.getConnection();
        PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, email);
            ps.setDate(4, date);
            // Ensure update goes through
            ps.executeUpdate();
            System.out.println("Student " + first_name + " " + last_name + " has been added!");
        }
    }

    // Change student's email. 
    public void updateStudentEmail(int student_id, String new_email) throws SQLException {
        // boolean to keep track of which message to display later
        boolean updated = true;
        // SQL query
        String SQL = "UPDATE students SET email=? WHERE student_id=?";
        try (Connection conn = psqlConn.getConnection();
        // Get values from PostgreSQL
        PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setString(1, new_email);
            ps.setInt(2, student_id);
            ps.executeUpdate();
            
        } catch (SQLException e){
            // If the email is already in use by someone else, cannot be changed to it
            if("23505".equals(e.getSQLState())){
                System.out.println("Email already being used by another student.");
                updated = false;
            }
        }

        if (updated == true){
            System.out.println("Email for student with ID " + student_id + " has successfully been changed!");
        }
    }   

    // Remove student from students
    public void removeStudent(int student_id) throws SQLException {
        // SQL Query
        String SQL = "DELETE FROM students WHERE student_id=?";
        // Connect through psqlConn
        try (Connection conn = psqlConn.getConnection();
        
        PreparedStatement ps = conn.prepareStatement(SQL)) {
            ps.setInt(1, student_id);
            ps.executeUpdate();
            System.out.println("Student with ID " + student_id + " has been successfully removed.");
        }
    }

    public static void main(String[] args) throws Exception {
        // CHANGE THIS LINE
        String url = "jdbc:postgresql://localhost:5432/A3Q1";

        PostgreConnection conn = new PostgreConnection(url, args[0], args[1]);
        App app = new App(conn);

        // Display all students
        app.getAllStudents();

        // Adding students
        app.addStudent("Maggie", "Zheng", "mushu@gmail.com", java.sql.Date.valueOf(java.time.LocalDate.now()));
        app.addStudent("Helia", "Martin", "teehee@gmail.com", java.sql.Date.valueOf(java.time.LocalDate.now()));
        app.addStudent("Dave", "Chen", "yippee@gmail.com", java.sql.Date.valueOf(java.time.LocalDate.now()));
        app.addStudent("Mira", "Who", "miravrse@gmail.com", java.sql.Date.valueOf(java.time.LocalDate.now()));

        app.getAllStudents();

        // Updating student's email
        app.updateStudentEmail(6, "yesyippee@gmail.com");
        app.updateStudentEmail(4, "mooshoo@gmail.com");
        // Check if catches error of email and uniqueness
        app.updateStudentEmail(5, "miravrse@gmail.com");

        app.getAllStudents();

        // Removing student
        app.removeStudent(4);
        app.removeStudent(5);
        app.removeStudent(6);
        app.removeStudent(7);

        app.getAllStudents();
    }
}

