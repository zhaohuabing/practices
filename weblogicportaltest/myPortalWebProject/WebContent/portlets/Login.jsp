<%@ page import="com.bea.portlet.WindowURL" %>

<h3 align="center">WebLogic Portal Login/Logout</h3>

<%
   WindowURL url = WindowURL.createWindowURL(request, response);

   if (request.getUserPrincipal() == null)
   {
      String errorMessage = (String) request.getAttribute("loginErrorMessage3");
%>
<form method="post" id="backingFileLoginForm" action="<%=url.toString()%>" type="POST">
   <table border="0" width="100%">
      <tr>
           <td align="center" colspan="2">
              <% if (errorMessage != null) { %>
              <font color="red">Login failed. Please try again.</font><br>
              <% } %>
              Please enter your username and password below.<br>
           </td>
      </tr>
      <tr>
           <td align="right">
              Username:
           </td>
           <td align="left">
              <input type="text" size=15 name="username" >
           </td>
      </tr>
      <tr>
           <td align="right">
              Password:
           </td>
           <td align="left">
              <input type="password" size=15 name="password" >
           </td>
      </tr>
      <tr>
           <td colspan="2" align="center">
              <input type="submit" value="Login">
           </td>
      </tr>
   </table>
</form>
<%
   }
   else
   {
%>
<form method="post" id="backingFileLoginForm" action="<%=url.toString()%>" type="POST">
   <table border="0" width="100%">
      <tr>
         <td align="center">
            <b><%=request.getUserPrincipal().getName()%></b>, Welcome to               WebLogic Portal!
         </td>
      </tr>
      <tr>
         <td align="center">
            <input type="hidden" name="logout" value="1">
            <input type="submit" value="Logout">
         </td>
      </tr>
   </table>
</form>
<%
}
%>