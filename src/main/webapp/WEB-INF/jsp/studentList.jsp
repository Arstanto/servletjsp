<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<%@ include file="head.jsp"%>

<body>
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
        <%@ include file="menu.jsp"%>
        <main class="mdl-layout__content">
        <div class="page-content">
        <div class="mdl-grid center-items">
            <div class="mdl-cell mdl-cell-4-col">
            <table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2p">
                    <tr>
                        <th class="mdl-data-table__cell-non-numeric">NO</th>
                        <th>Name</th>
                        <th>Grade</th>
                        <th>Balance</th>
                        <th>StudentId</th>
                        <th>Action</th>
                    </tr>
                    <c:set var="count" value="0" scope="page" />
                    <c:forEach var="student" items="${studentList}">
                    <c:set var="count" value="${count +1}" scope="page" />
                    <tr>
                        <td><c:out value="${count}" /></td>
                        <td><c:out value="${student.name}" /></td>
                        <td><c:out value="${student.grade}" /></td>
                        <td><c:out value="${student.balance}" /></td>
                        <td><c:out value="${student.studentId}" /></td>
                        <td><a href="students/edit?id=<c:out value='${student.id}' />">edit</a>
                        <td><a href="students/delete?id=<c:out value='${student.id}' />">delete</a>
                    </tr>
                    </c:forEach>
            </table>
            </div>