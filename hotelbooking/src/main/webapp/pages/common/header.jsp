<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Car Rent System</title>
</head>
<body>
    <center>
        <h1>Rooms Management</h1>
        <h2>
            <a href="new">Add New Room</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All Rooms</a>

        </h2>
    </center>
    <c:if test="${message != null}">
        <center>
            <div>
                <span><c:out value='${message}' /></span>
            <div>
        </center>
    </c:if>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Rooms</h2></caption>
            <tr>
                <th>Type</th>

            </tr>
            <c:forEach var="room" items="${listRoom}">
                <tr>
                    <td><c:out value="${room.type}" /></td>
                    <td>
                        <a href="edit?id=<c:out value='${room.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="delete?id=<c:out value='${room.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>