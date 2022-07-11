<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting staff</title>
</head>
<body>
<h1>Delete staff</h1>
<p>
    <a href="/staff">Back to staff list</a>
</p>
<form method="post">
    <h3>Are you sure?</h3>
    <fieldset>
        <legend>staff information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td>${requestScope["staff"].getName()}</td>
            </tr>
            <tr>
                <td>Birth: </td>
                <td>${requestScope["staff"].getBirth()}</td>
            </tr>
            <tr>
                <td>Address: </td>
                <td>${requestScope["staff"].getAddress()}</td>
            </tr>
            <tr>
                <td>Phone: </td>
                <td>${requestScope["staff"].getPhone()}</td>
            </tr>
            <tr>
                <td>Email: </td>
                <td>${requestScope["staff"].getMail()}</td>
            </tr>
            <tr>
                <td>Department: </td>
                <td>${staff.getDepartment().getNameD()}</td>
            </tr>
            <tr>
                <td><input type="submit" value="Delete staff"></td>
                <td><a href="/staff">Back to staff list</a></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>