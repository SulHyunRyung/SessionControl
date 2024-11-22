<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 페이지</title>
    <link rel="stylesheet" href="css/login.css">
</head>
<body>
<%
    if (session != null && session.getAttribute("userid") != null) {
%>
        <script>
            location.href = 'main.jsp';
        </script>
<%
        return;
    }
%>

    <div class="login-container">
        <h2>로그인</h2>
        <form id="loginForm" action="login.do" method="post">
            <label for="userid">아이디</label>
            <input type="text" id="userid" name="userid" required>

            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>

            <div class="button-container">
                <button type="submit" class="login-btn">로그인</button>
                <button type="button" class="signup-btn" onclick="location.href='register.jsp'">회원가입</button>
            </div>
        </form>
    </div>
</body>
</html>
