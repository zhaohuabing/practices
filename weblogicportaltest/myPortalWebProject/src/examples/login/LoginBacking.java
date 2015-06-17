package examples.login;

import com.bea.netuix.servlets.controls.content.JspContentContext;
import com.bea.netuix.servlets.controls.content.backing.AbstractJspBacking;
import com.bea.p13n.security.Authentication;
import com.bea.portlet.GenericURL;
import com.bea.portlet.PostbackURL;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginBacking extends AbstractJspBacking {
  private static final long serialVersionUID = 1L;
  public static final String REDIRECT_ACTION = "redirect";

  public boolean handlePostbackData(HttpServletRequest request,     HttpServletResponse response) {
        if (isRequestTargeted(request)) {
          if (request.getParameter(GenericURL.STATE_PARAM) == null) {
              String username = request.getParameter("username");
              String password = request.getParameter("password");

              PostbackURL url = PostbackURL.createPostbackURL(request, response);

              if (username != null && password != null) {
                try {
                  Authentication.login(username, password, request, response);
                }
                catch (LoginException le) {
                 request.setAttribute("loginErrorMessage3", new String("true"));
                 return false;
                }
              }
              else if (request.getParameter("logout") != null) {
                 Authentication.logout(request);
              }

              url.addParameter(GenericURL.LOADSTATE_PARAM, "false");
              url.addParameter(GenericURL.PAGE_LABEL_PARAM, "login");

              try {
                  JspContentContext jspContext =                    JspContentContext.getJspContentContext(request);
                jspContext.sendRedirect(url.toString());
              }
              catch (Exception ie) {
                 ie.printStackTrace();
              }
           }
       }
       return true;
   }
}