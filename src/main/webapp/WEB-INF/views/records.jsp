<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<h1>Records</h1>

<c:url var="addUrl" value="/main/record/add" />
<table style="border: 1px solid; width: 100%; text-align:center">
    <thead style="background:#d3dce3">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Country</th>
        <th colspan="2"></th>
        <th>Card ID</th>
        <th>Card Number</th>
        <th colspan="3"></th>
    </tr>
    </thead>
    <tbody style="background:#ccc">
    <c:forEach items="${persons}" var="person">
        <c:url var="editUrl" value="/main/record/edit?id=${person.id}" />
        <c:url var="deleteUrl" value="/main/record/delete?id=${person.id}" />

        <c:if test="${!empty person.cards}">
            <c:forEach items="${person.cards}" var="card">
                <tr>
                    <td><c:out value="${person.id}" /></td>
                    <td><c:out value="${person.name}" /></td>
                    <td><c:out value="${person.country}" /></td>
                    <td><a href="${editUrl}">Edit Person</a></td>
                    <td><a href="${deleteUrl}">Delete Person</a></td>

                    <td><c:out value="${card.id}" /></td>
                    <td><c:out value="${card.number}" /></td>
                    <c:url var="addCardUrl" value="/main/card/add?id=${person.id}" />
                    <c:url var="editCardUrl" value="/main/card/edit?pid=${person.id}&cid=${card.id}" />
                    <c:url var="deleteCardUrl" value="/main/card/delete?id=${card.id}" />
                    <td><a href="${addCardUrl}">Add Card</a></td>
                    <td><a href="${editCardUrl}">Edit Card</a></td>
                    <td><a href="${deleteCardUrl}">Delete Card</a></td>
                </tr>
            </c:forEach>
        </c:if>

        <c:if test="${empty person.cards}">
            <tr>
                <td><c:out value="${person.id}" /></td>
                <td><c:out value="${person.name}" /></td>
                <td><c:out value="${person.country}" /></td>
                <td><a href="${editUrl}">Edit Person</a></td>
                <td><a href="${deleteUrl}">Delete Person</a></td>

                <td>N/A</td>
                <td>N/A</td>

                <c:url var="addCardUrl" value="/main/card/add?id=${person.id}" />
                <td><a href="${addCardUrl}">Add Card</a></td>
                <td></td>
                <td></td>
            </tr>
        </c:if>

    </c:forEach>
    </tbody>
</table>

<c:if test="${empty persons}">
    No records found.
</c:if>

<p><a href="${addUrl}">Create new record</a></p>

</body>
</html>