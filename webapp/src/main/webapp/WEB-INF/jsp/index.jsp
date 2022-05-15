<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
    <body>
        <h2><spring:message code="user.greeting" arguments="${user.username}" htmlEscape="true"/></h2>
        <h2><spring:message code="user.id" arguments="${user.id}"/></h2>
        <h3>Issues reported:</h3>
        <ul>
            <c:forEach items="${user.reportedIssues}" var="issue">
                <li><c:out value="${issue.despcription}" escapeXml="true" /> - <strong><c:out value="${issue.priority}"/></strong></li>
            </c:forEach>
        </ul>
        <h3>Issues assigned:</h3>
        <ul>
            <c:forEach items="${user.assignedIssues}" var="issue">
                <li><c:out value="${issue.despcription}" escapeXml="true" /> - <strong><c:out value="${issue.priority}"/></strong></li>
            </c:forEach>
        </ul>
    </body>
</html>