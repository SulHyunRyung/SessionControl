package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/update.do")
public class UpdateAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateAccountServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
        if (request.getSession(false) == null || request.getSession(false).getAttribute("userid") == null) {
            response.getWriter().println("<script type='text/javascript'>"
                    + "alert('잘못된 접근입니다.');"
                    + "window.location.href = 'login.jsp';"
                    + "</script>");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        // 세션 확인
        if (request.getSession(false) == null || request.getSession(false).getAttribute("userid") == null) {
            // 세션이 없거나 사용자 정보가 없으면 로그인 페이지로 리디렉션
            response.getWriter().println("<script type='text/javascript'>"
                    + "alert('잘못된 접근입니다.');"
                    + "window.location.href = 'login.jsp';"
                    + "</script>");
            return;
        }

        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String emailAgree = request.getParameter("emailAgree");
        String phone = request.getParameter("phone");
        String introduce = request.getParameter("introduce");
        String[] interest = request.getParameterValues("interest");

        MemberVO vo = new MemberVO();
        vo.setUserid(userid);
        vo.setPassword(password);
        vo.setEmail(email);
        vo.setEmailAgree(emailAgree);
        vo.setPhone(phone);
        vo.setIntroduce(introduce);
        vo.setInterest(interest);

        MemberDAO dao = new MemberDAOImpl();
        int result = dao.updateAccount(vo);
        if (result > 0) {
            response.getWriter().println("<script type='text/javascript'>"
                    + "alert('회원 정보가 수정되었습니다.');"
                    + "window.location.href = 'main.jsp';"
                    + "</script>");
        } else {
            response.getWriter().println("<script type='text/javascript'>"
                    + "alert('잘못된 방식입니다.');"
                    + "window.location.href = 'update.jsp';"
                    + "</script>");
        }
    }
}
