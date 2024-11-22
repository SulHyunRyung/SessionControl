<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="member.MemberVO, member.MemberDAO, member.MemberDAOImpl" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<%
    if (session == null || session.getAttribute("userid") == null) {
%>
        <script>
            alert('잘못된 접근입니다.');
            location.href = 'login.jsp';
        </script>
<%
        return;
    }

    String userid = (String) session.getAttribute("userid");

    MemberDAO dao = MemberDAOImpl.getInstance();
    MemberVO member = dao.selectUserInfo(userid);

    if (member == null) {
%>
        <script>
            alert('사용자 정보를 찾을 수 없습니다.');
            location.href = 'login.jsp';
        </script>
<%
        return;
    }
%>

    <div class="main-container">
        <h1>안녕하세요, <%= member.getUserid() %>님!</h1>
        <table border="1">
            <tr>
                <th>아이디</th>
                <td><%= member.getUserid() %></td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td><%= member.getPassword() %></td>
            </tr>
            <tr>
                <th>이메일</th>
                <td><%= member.getEmail() %></td>
            </tr>
            <tr>
                <th>이메일 수신 동의</th>
                <td><%= member.getEmailAgree() %></td>
            </tr>
            <tr>
                <th>관심사</th>
                <td><%= member.getInterestJoin() %></td>
            </tr>
            <tr>
                <th>전화번호</th>
                <td><%= member.getPhone() %></td>
            </tr>
            <tr>
                <th>자기소개</th>
                <td><%= member.getIntroduce() %></td>
            </tr>
        </table>
        
        <div class="button-container">
            <form action="update.jsp" method="get">
                <button type="submit" class="update-btn">회원정보 수정</button>
            </form>
            <form action="logout.do" method="post">
                <button type="submit" class="logout-btn">로그아웃</button>
            </form>
            <form action="deleteAccount.do" method="post" onsubmit="return confirm('정말로 회원탈퇴를 하시겠습니까?');">
                <button type="submit" class="delete-btn">회원 탈퇴</button>
            </form>
        </div>
    </div>
</body>
</html>
