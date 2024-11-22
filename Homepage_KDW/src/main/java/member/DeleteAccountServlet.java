package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deleteAccount.do")
public class DeleteAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteAccountServlet() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userid") == null) {
            response.getWriter().write("<script>alert('잘못된 접근입니다.'); location.href='login.jsp';</script>");
            return;
        }

        String userid = (String) session.getAttribute("userid");

        MemberDAO dao = MemberDAOImpl.getInstance();

        boolean result = dao.deleteAccount(userid); 

        if (result) {
            response.getWriter().write("<script>alert('회원 탈퇴가 완료되었습니다.'); location.href='login.jsp';</script>");
            session.invalidate(); 
        } else {
            response.getWriter().write("<script>alert('회원 탈퇴 실패'); location.href='main.jsp';</script>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); 
    }
}
