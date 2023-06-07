package com.nt.ouat1;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/one")
public class StudentServlet extends jakarta.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_management";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "shree";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "edit":
                    // Code to retrieve and display student details for editing
                    break;
                case "delete":
                    // Code to delete a student record
                    break;
            }
        } else {
            // Code to retrieve and display all student records
            List<Student> studentList = getAllStudents();
            request.setAttribute("studentList", studentList);
            request.getRequestDispatcher("Classmate-list.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("add")) {
            String name = request.getParameter("name");
            String rollNo = request.getParameter("rollNo");
            String physicalAddress = request.getParameter("physicalAddress");
            String gender = request.getParameter("gender");
            String dob = request.getParameter("dob");
            int age = Integer.parseInt(request.getParameter("age"));
            String mobileNo = request.getParameter("mobileNo");
            String emailId = request.getParameter("emailId");
            String signature = request.getParameter("signature");
            String remarks = request.getParameter("remarks");
            Part photo = request.getPart("photo");

            // Save the photo to the server directory
            String photoFileName = savePhoto(photo);

            // Create a new Student object
            Student student = new Student(name, rollNo, physicalAddress, gender, dob, age, mobileNo, emailId,
                    signature, remarks, photoFileName);

            // Insert the student into the database
            insertStudent(student);

            // Redirect to the student list page
            response.sendRedirect("Student");
        }
    }

    private List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM students");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String rollNo = resultSet.getString("roll_no");
                String physicalAddress = resultSet.getString("physical_address");
                String gender = resultSet.getString("gender");
                String dob = resultSet.getString("dob");
                int age = resultSet.getInt("age");
                String mobileNo = resultSet.getString("mobile_no");
                String emailId = resultSet.getString("email_id");
                String signature = resultSet.getString("signature");
                String remarks = resultSet.getString("remarks");
                String photoFileName = resultSet.getString("photo");

                Student student = new Student(name, rollNo, physicalAddress, gender, dob, age, mobileNo, emailId,
                        signature, remarks, photoFileName);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    private void insertStudent(Student student) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO students (name, roll_no, physical_address, gender, dob, age, mobile_no, email_id, signature, remarks, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getRollNo());
            statement.setString(3, student.getPhysicalAddress());
            statement.setString(4, student.getGender());
            statement.setString(5, student.getDob());
            statement.setInt(6, student.getAge());
            statement.setString(7, student.getMobileNo());
            statement.setString(8, student.getEmailId());
            statement.setString(9, student.getSignature());
            statement.setString(10, student.getRemarks());
            statement.setString(11, student.getPhoto());
            

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "shree");
    }

    private String savePhoto(Part photo) throws IOException {
        String fileName = Paths.get(photo.getSubmittedFileName()).getFileName().toString();
        String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
        String photoFilePath = getServletContext().getRealPath("photos/" + uniqueFileName);

        try (InputStream inputStream = photo.getInputStream()) {
            Files.copy(inputStream, Paths.get(photoFilePath));
        }

        return uniqueFileName;
    }
}

