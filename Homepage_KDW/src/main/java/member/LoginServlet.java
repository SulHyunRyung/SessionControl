package member;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static MemberDAO dao;

    public LoginServlet() {
        dao = MemberDAOImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userid") == null) {
            response.setContentType("text/html;charset=UTF-8");
            try (var out = response.getWriter()) {
                out.println("<script>");
                out.println("alert('잘못된 접근입니다.');");
                out.println("location.href='login.jsp';"); 
                out.println("</script>");
            }
        } else {
            response.sendRedirect("main.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            MemberVO member = dao.selectByUserId(userid);

            // 탈퇴 회원 처리
            String status = member.getStatus().trim();
            if ("inactive".equalsIgnoreCase(status)) {
                out.println("<script>");
                out.println("alert('탈퇴한 회원입니다.');");
                out.println("location.href='login.jsp';");
                out.println("</script>");
                return;
            }

            // 비밀번호 체크
            if (member.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("userid", member.getUserid());
                session.setMaxInactiveInterval(60); 
                out.println("<script>");
                out.println("alert('" + userid + "님 환영합니다.');");
                out.println("location.href='main.jsp';");
                out.println("</script>");
            } else {
                out.println("<script>");
                out.println("alert('ID 혹은 비밀번호를 확인하세요.');");
                out.println("history.back();");
                out.println("</script>");
            }
        }
    }

}


