<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
        <title>Test page</title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="../css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <div class="wrap">
            <section>
                <div class="container">
                    <h1>Rooms Management</h1>

                    <c:if test="${message != null}">
                        <div class="alert <c:out value='${message.type.reference}' />">
                          <strong><c:out value='${message.text}' /></strong>
                        </div>
                        <center>
                            <div>
                                <span></span>
                            <div>
                        </center>
                    </c:if>
                    <table class="table table-hover">
                        <caption><h2>List of Rooms</h2></caption>
                        <tr>
                            <th>Room Id</th>
                            <th>Type</th>
                            <th>Action</th>


                        </tr>
                        <c:forEach var="room" items="${listRoom}">
                            <tr>
                                <td><c:out value="${room.id}" /></td>
                                <td><c:out value="${room.type}" /></td>

                                <td>
                                    <a href="edit?id=<c:out value='${room.id}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="delete?id=<c:out value='${room.id}' />">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <a href="new" role="button" class="btn btn-info btn-lg" formaction="<%=request.getContextPath()%>/room/new"> Add Room</a>
                </div>
            </section>
        </div>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>