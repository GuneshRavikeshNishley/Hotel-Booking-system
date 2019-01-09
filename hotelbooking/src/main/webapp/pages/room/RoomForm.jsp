<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
                <c:if test="${isEdit}">
                    Edit Room
                </c:if>
                <c:if test="${isNew}">
                    Add New Room
                </c:if>
         </title>

        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">

            <div class="title-container">
                <h1>
                    <c:if test="${isEdit}">
                        Edit Room
                    </c:if>
                    <c:if test="${isNew}">
                        Add New Room
                    </c:if>
                </h1>
            </div>

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

             <c:if test="${isEdit}">
                <form action="update" method="post" role="form">
            </c:if>
            <c:if test="${isNew}">
                <form action="insert" method="post" role="form">
            </c:if>

            <c:if test="${isEdit}">
                <input type="hidden" name="id" value="<c:out value='${room.id}' />" />
            </c:if>

                <div class="form-group">
                    <label for="id">
                        Room Id: <input class="form-control" type="text" id="id" name="id" value="<c:out value='${room.id}' />" />
                    </label>
                </div>
                <div class="form-group">
                    <label for="type">
                        Type: <select name='type' class="form-control">
                                <option value="${room.type}" selected>${room.type}</option>
                                <c:forEach items="${types}" var="type">
                                    <c:if test="${type != selected}">
                                        <option value="${type}">${type}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                    </label>
                </div>

                <input type="submit" value="Save" class="btn btn-secondary"/>
            </form>
        </div>

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    </body>
</html>