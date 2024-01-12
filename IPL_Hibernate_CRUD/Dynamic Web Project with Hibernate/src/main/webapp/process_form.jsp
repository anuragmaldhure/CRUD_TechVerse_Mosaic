<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Process Form</title>
</head>
<!-- invoke matching setters -->
<jsp:setProperty property="*" name="player_bean"/>
<body>
<!-- 	invoke B.L. of player bean to add new player -->
<h5>Adding Player status : ${sessionScope.player_bean.validateAndAddPlayer()}</h5>
</body>
</html>