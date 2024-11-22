<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="member.MemberVO, member.MemberDAO, member.MemberDAOImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
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
		<h1>회원 정보 수정</h1>
		<form action="update.do" method="post">
			<table border="1">
				<tr>
					<th>아이디</th>
					<td><input type="text" name="userid" value="<%= member.getUserid() %>" readonly /></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="password" value="<%= member.getPassword() %>" required /></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" value="<%= member.getEmail() %>" required /></td>
				</tr>
				<tr>
					<th>이메일 수신 동의</th>
					<td><input type="radio" name="emailAgree" value="Y" <%= member.getEmailAgree().equals("Y") ? "checked" : "" %> /> 예 <input type="radio" name="emailAgree" value="N" <%= member.getEmailAgree().equals("N") ? "checked" : "" %> /> 아니오</td>
				</tr>
				<tr>
					<th>관심사</th>
					<td>
						<input type="checkbox" id="interest1" name="interest" value="게임" <%= member.getInterestJoin().contains("게임") ? "checked" : "" %> /> <label for="interest1">게임</label>
						<input type="checkbox" id="interest2" name="interest" value="음악" <%= member.getInterestJoin().contains("음악") ? "checked" : "" %> /> <label for="interest2">음악</label> 
						<input type="checkbox" id="interest3" name="interest" value="독서" <%= member.getInterestJoin().contains("독서") ? "checked" : "" %> /> <label for="interest3">독서</label> 
						<input type="checkbox" id="interest4" name="interest" value="여행" <%= member.getInterestJoin().contains("여행") ? "checked" : "" %> /> <label for="interest4">여행</label>
					</td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" name="phone" value="<%= member.getPhone() %>" required /></td>
				</tr>
				<tr>
					<th>자기소개</th>
					<td><textarea name="introduce" rows="4" cols="50"><%= member.getIntroduce() %></textarea></td>
				</tr>
			</table>
			<div class="button-container">
				<button type="submit" class="update-btn">정보 수정</button>
				<button type="button" class="cancel-btn" onclick="location.href='main.jsp'">취소</button>
			</div>
		</form>
	</div>
</body>
</html>
