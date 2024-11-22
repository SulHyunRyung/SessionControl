package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogoutServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("userid") != null) {
            session.removeAttribute("userid");
            session.invalidate();
            try (var out = response.getWriter()) {
                out.println("<script>");
                out.println("alert('로그아웃 완료');");
                out.println("location.href='login.jsp';");
                out.println("</script>");
            }
        } else {
            try (var out = response.getWriter()) {
                out.println("<script>");
                out.println("alert('잘못된 접근입니다.');");
                out.println("location.href='login.jsp';");
                out.println("</script>");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
