package security;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SSOTokenFilter implements Filter {
    private static final String HW_SSO_TOKEN = "SamplePerimeterAtnToken";
    
    public SSOTokenFilter() {
        super();
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException,
                                                   ServletException {
        HttpServletRequest httpReq = (HttpServletRequest)request;
        HttpServletResponse httpResp = (HttpServletResponse)response;
            
        if (foundTokenCookie(httpReq)) {
            authenticatedWithIdentityAsserter(chain, httpReq, httpResp);
        } else {
            setTokenCookieForIdentityAsserter(response, httpReq);
        }
    }

    private void authenticatedWithIdentityAsserter(FilterChain chain,
                                                   HttpServletRequest httpReq,
                                                   HttpServletResponse httpResp) throws IOException,
                                                                                        ServletException {
        chain.doFilter(httpReq, httpResp);
    }

    private void setTokenCookieForIdentityAsserter(ServletResponse response,
                                                   HttpServletRequest httpReq) throws IOException {
        Cookie cookie =
            new Cookie(HW_SSO_TOKEN, "username=welcome");
        cookie.setMaxAge(-1);
        ((HttpServletResponse)response).addCookie(cookie);
        ((HttpServletResponse)response).sendRedirect(httpReq.getRequestURI());
    }

    public boolean foundTokenCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie1 = cookies[i];
            if (cookie1.getName().equals(HW_SSO_TOKEN)) {
                     return true;
            }
        }
        return false;
    }


    public void destroy() {
    }
}


