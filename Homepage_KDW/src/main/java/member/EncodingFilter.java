package member;import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"/login.do", "/register.do", "/update.do"})
public class EncodingFilter implements Filter {
    private String encoding = "UTF-8";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
