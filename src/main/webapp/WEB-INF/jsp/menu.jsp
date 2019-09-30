<%@page import="java.util.Calendar"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
    <!-- Title -->
    <span class="mdl-layout-title">Student Database</span>
    <div class="mdl-layout-spacer"></div>
    <nav class="mdl-navigation mdl-layout--large-screen-only">
        <a class="mdl-navigation__link" href="/servletjsp/students/new">add new student</a>
        <a class="mdl-navigation__link" href="/servletjsp/students">list all student</a>
    </nav>
</header>
<div class="mdl-layout__drawer">
    <span class="mdl-layout-title">Students</span>
    <nav class="mdl-navigation">
        <a class="mdl-navigation__link" href="/servletjsp/students/new">add new student</a>
        <a class="mdl-navigation__link" href="/servletjsp/students">list all student</a>
    </nav>
</div>