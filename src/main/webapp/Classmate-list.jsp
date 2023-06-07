<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String successMessage = (String) request.getAttribute("successMessage"); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Management System</title>
</head>
<body>
    <h1>Student Management System</h1>

    <%-- Form to insert a new student --%>
    <h2>Add New Student</h2>
    <form action="one" method="post" enctype="multipart/form-data">
        <label>Name:</label>
        <input type="text" name="name" required><br>

        <label>Roll Number:</label>
        <input type="text" name="rollNo" required><br>

        <label>Physical Address:</label>
        <input type="text" name="physicalAddress" required><br>

        <label>Gender:</label>
        <input type="radio" name="gender" value="Male" checked> Male
        <input type="radio" name="gender" value="Female"> Female<br>

        <label>Date of Birth:</label>
        <input type="date" name="dob" required><br>

        <label>Age:</label>
        <input type="number" name="age" required><br>

        <label>Mobile Number:</label>
        <input type="text" name="mobileNo" required><br>

        <label>Email ID:</label>
        <input type="email" name="emailId" required><br>

        <label>Signature:</label>
        <input type="text" name="signature"><br>

        <label>Remarks:</label>
        <textarea name="remarks"></textarea><br>

        <label>Choose Photo:</label>
        <input type="file" name="photo"><br>

        <input type="submit" value="Add Student">
    </form>

    <%-- Form to search for a student --%>
    <h2>Search Student</h2>
    <form action="one" method="get">
        <label>Search by Name, Roll Number, or Mobile Number:</label>
        <input type="text" name="search" required>
        <input type="submit" value="Search">
    </form>

    <%-- Display table of students --%>
    <h2>Student List</h2>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Roll Number</th>
            <th>Physical Address</th>
            <th>Gender</th>
            <th>Date of Birth</th>
            <th>Age</th>
            <th>Mobile Number</th>
            <th>Email ID</th>
            <th>Signature</th>
            <th>Remarks</th>
            <th>Photo</th>
            <th>Action</th>
        </tr>
        <%-- Iterate over the list of students and display the data --%>
        <c:forEach items="${studentList}" var="Student">
            <tr>
                <td>${student.name}</td>
                <td>${student.rollNo}</td>
                <td>${student.physicalAddress}</td>
                <td>${student.gender}</td>
                <td>${student.dob}</td>
                <td>${student.age}</td>
                <td>${student.mobileNo}</td>
                <td>${student.emailId}</td>
                <td>${student.signature}</td>
                <td>${student.remarks}</td>
                <td><img src="${student.photo}" width="50" height="50"></td>
                <td>
                    <a href="StudentServlet?action=edit&id=${student.id}">Edit</a>
                    <a href="StudentServlet?action=delete&id=${student.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
<head>
    <title>Thank You</title>
</head>
<body>
    <h1>Thank You</h1>
    <p><%= successMessage %></p>
</body>
</html>

