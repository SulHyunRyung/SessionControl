package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static MemberDAO dao;

    public RegisterServlet() {
        dao = MemberDAOImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 이미 로그인 된 상태면 접근 차단
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("userid") != null) {
            response.sendRedirect("main.jsp");
            return;
        }
        
        String referer = request.getHeader("Referer");
        
        if (referer == null || !referer.contains("register.jsp")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println("<script>");
            response.getWriter().println("alert('잘못된 접근입니다.');");
            response.getWriter().println("location.href = 'login.jsp';");
            response.getWriter().println("</script>");
            return;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String emailAgree = request.getParameter("emailAgree");
        if (emailAgree == null) {
            emailAgree = "N"; 
        }
        String[] interest = request.getParameterValues("interest");
        String phone = request.getParameter("phone");
        String introduce = request.getParameter("introduce");

        MemberVO existingMember = dao.selectByUserId(userid);
        
        if (existingMember != null) {
            String status = existingMember.getStatus();
            if ("inactive".equalsIgnoreCase(status)) {
            	// 탈퇴 회원 처리
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>");
                response.getWriter().println("alert('탈퇴한 회원의 ID는 사용할 수 없습니다.');");
                response.getWriter().println("history.back();");
                response.getWriter().println("</script>");
            } else {
            	// 중복 ID 처리
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().println("<script>");
                response.getWriter().println("alert('이미 존재하는 아이디입니다.');");
                response.getWriter().println("history.back();");
                response.getWriter().println("</script>");
            }
        } else {
            MemberVO member = new MemberVO(userid, password, email, emailAgree, interest, phone, introduce, "active");
            int result = dao.insert(member);

            response.setContentType("text/html; charset=UTF-8");
            if (result > 0) {
                response.getWriter().println("<script>");
                response.getWriter().println("alert('회원가입이 완료되었습니다.');");
                response.getWriter().println("location.href = 'login.jsp';");
                response.getWriter().println("</script>");
            } else {
                response.getWriter().println("<script>");
                response.getWriter().println("alert('잘못된 방식입니다.');");
                response.getWriter().println("history.back();");
                response.getWriter().println("</script>");
            }
        }
    }
}

