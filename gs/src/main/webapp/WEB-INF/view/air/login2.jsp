<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>로그인</title>
  
  <link rel='stylesheet prefetch' href='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css'>
  <link rel="stylesheet" href="assets/css/login.css">

  
</head>

<body>
<div class='login'>
  <div class='login_title'>
    <span>Login to your account</span>
  </div>
  <form class="form-signin" method="post" action="j_spring_security_check">
	  <div class='login_fields'>
	    <div class='login_fields__user'>
	      <div class='icon'>
	        <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/user_icon_copy.png'>
	      </div>
	      <input placeholder='Username' type='text' name="username"/>
	        <div class='validation'>
	          <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/tick.png'>
	        </div>
	    </div>
	    <div class='login_fields__password'>
	      <div class='icon'>
	        <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/lock_icon_copy.png'>
	      </div>
	      <input placeholder='Password' type='password' name="password">
	      <div class='validation'>
	        <img src='https://s3-us-west-2.amazonaws.com/s.cdpn.io/217233/tick.png'>
	      </div>
	    </div>
	    <div class='login_fields__submit'>
	      <input type='submit' value='Log In'>
	      <div class='forgot'>
	        <a href='#'>Forgotten password?</a>
	      </div>
	    </div>
	  </div>
	  <div class='success'>
	    <h2>Authentication Success</h2>
	    <p>Welcome back</p>
	  </div>
	  <div class='disclaimer'>
	    <c:if test="${not empty error}">
			<p>${error}</p>
		</c:if>
		<c:if test="${not empty msg}">
			<p>${msg}</p>
		</c:if>
	  </div>
	</form>
</div>

<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js'></script>
<script src="assets/js/login.js"></script>

</body>
</html>
