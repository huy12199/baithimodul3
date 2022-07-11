<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Staff List</title>
</head>
<body>
<h1>Staff</h1>
<form action=/staff?action=search method="post">
    <input type="text" name="searchName"> <button type="submit">Search</button>

</form>
<p>
    <a href="/staff?action=create">Create new staff</a>
</p>
<table border="1">
    <tr>
        <td>Name</td>
        <td>Birth</td>
        <td>Address</td>
        <td>Phone Number</td>
        <td>Email</td>
        <td>Department</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items='${requestScope["staffs"]}' var="staff">
        <tr>
            <td>${staff.getName()}</td>
            <td>${staff.getBirth()}</td>
            <td>${staff.getAddress()}</td>
            <td>${staff.getPhone()}</td>
            <td>${staff.getMail()}</td>
            <td>${staff.getDepartment().getNameD()}</td>
            <td><a href="/staff?action=edit&id=${staff.getId()}">edit</a></td>
            <td><a href="/staff?action=delete&id=${staff.getId()}">delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>