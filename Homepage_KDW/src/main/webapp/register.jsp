<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
    <div class="container">
        <h2>회원가입</h2>
        <form id="registerForm" action="register.do" method="post">
            <label for="userid">아이디</label>
            <input type="text" id="userid" name="userid" required>
            
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" required>
            
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" required>
            
            <div class="checkbox-inline">
                <input type="checkbox" id="emailAgree" name="emailAgree" value="Y">
                <label for="emailAgree">이메일 수신 동의</label>
            </div>

            <label>관심사항</label>
            <div class="checkbox-group">
                <input type="checkbox" id="interest1" name="interest" value="게임">
                <label for="interest1">게임</label>
                
                <input type="checkbox" id="interest2" name="interest" value="음악">
                <label for="interest2">음악</label>
                
                <input type="checkbox" id="interest3" name="interest" value="독서">
                <label for="interest3">독서</label>
                
                <input type="checkbox" id="interest4" name="interest" value="여행">
                <label for="interest4">여행</label>
            </div>
            
            <label for="phone">전화번호</label>
            <input type="text" id="phone" name="phone">
            
            <label for="introduce">자기소개</label>
            <textarea id="introduce" name="introduce"></textarea>
            
            <input type="submit" value="회원가입">
        </form>
    </div>
</body>
</html>
