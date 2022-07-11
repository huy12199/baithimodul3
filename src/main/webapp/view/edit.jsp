<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit customer</title>
</head>
<body>
<h1>Edit customer</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/staff">Back to staff list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Staff information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name" value="${requestScope["staff"].getName()}"></td>
            </tr>
            <tr>
                <td>Birth: </td>
                <td><input type="date" name="birth" id="birth" value="${requestScope["staff"].getBirth()}"></td>
            </tr>
            <tr>
                <td>Address: </td>
                <td><input type="text" name="address" id="address" value="${requestScope["staff"].getAddress()}"></td>
            </tr>
            <tr>
                <td>Phone: </td>
                <td><input type="text" name="phone" id="phone" value="${requestScope["staff"].getPhone()}"></td>
            </tr>
            <tr>
                <td>Email: </td>
                <td><input type="text" name="email" id="email" value="${requestScope["staff"].getMail()}"></td>
            </tr>
            <tr>
                <td>Department:</td>
                <td><input type="text" name="departments" id="department" value="${requestScope["staff"].getDepartment().getId()}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Update staff"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>