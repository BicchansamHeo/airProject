<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<form:form commandName="BoardVO" method="post">
		<h2>예제 테이블</h2>
		<table>
        	<thead>
	            <tr>
					<th class="text-center" width="20%" data-priority="1">번호</th>
					<th class="text-center" width="*" data-priority="2">제목</th>
	            </tr>
            </thead>
            <tbody>
	            <c:forEach var="list" items="${boardModelList}" varStatus="status">
		            <tr>
						<td>${list.boardSeq}</td>
						<td>${list.title}</td>
					</tr>
				</c:forEach>
            </tbody>
        </table>
		</form:form>
	</body>
</html>
