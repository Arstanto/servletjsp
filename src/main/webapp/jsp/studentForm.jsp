<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="head.jsp"%>
<body>
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <%@ include file="menu.jsp"%>
    <div class="page-content">
        <div class="mdl-grid center-items">
            <div class="mdl-cell mdl-cell--4-coll">
                <div class="mdl-card mdl-shadow--6dp">
                    <div class="mdl-card__title mdl-color-primary mdl-color-text--white">
                        <h3 class="mdl-card__title">
                            <c:if test="${student != null}">Edit student</c:if>
                            <c:if test="${student == null}">Add new student</c:if>
                        </h3>
                    </div>
                    <div class="mdl-card__supporting-text">
                        <c:if test="${student != null}">
                            <form name="studentForm" action="update" method="post" onsubmit="return validateForm()">
                            <input type="hidden" name="id" value="<c:out value='${student.id}' />">
                        </c:if>
                        <c:if test="${student == null}">
                            <form name="studentForm" action="insert" method="post" onsubmit="return validateForm()">
                        </c:if>
                        <div class="mdl-textfield mdl-js-textfield">
                            <input class="mdl-textfield__input" type="text" name="name" value="<c:out value='${student.name}' />" id="name" />
                            <label class="mdl-textfield__label" for="name">Name</label>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield getmdl-select is-dirty">
                            <input type="text" value="<c:out value='${student.grade}' />" name="grade" class="mdl-textfield__input" id="grade" readonly />
                            <input type="hidden" value="<c:out value='${student.grade}' />" name="grade" id="grade" />
                            <label class="mdl-textfield__label" for="grade">Grade</label>
                            <ul for="grade" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
                                <c:forEach items="${gradeList}" var="grade">
                                    <li class="mdl-menu__item" data-val="${grade}">
                                        <c:out value="${grade}" />
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="mdl-textfield mdl-js-textfield">
                            <input class="mdl-textfield__input" type="text" name="balance" value="<c:out value='${student.balance}' />" id="name" />
                            <label class="mdl-textfield__label" for="balance">Balance</label>
                        </div>
                        <c:if test="${student != null}">
                            <div class="mdl-textfield mdl-js-textfield">
                                <input class="mdl-textfield__input" type="text" name="studentId" value="<c:out value='${student.studentId}' />" id="name" />
                                <label class="mdl-textfield__label" for="studentId">Student Id</label>
                            </div>
                        </c:if>
                    </div>
                    <input type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" value="submit"/>
                    </div>
                </div>
            </div>
        </div>
     </div>
     <script type="text/javascript">
        function validateForm() {
            var balance = document.forms["studentForm"]["balance"].value;
            if (balance == "") {
                alert("Balance must be filled out");
                return false;
            }
        }
     </script>
</body>
</html>