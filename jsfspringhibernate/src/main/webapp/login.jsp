
<head>
)
<title>Security WebApp login page</title>
</head>
<body bgcolor="#cccccc">
	<blockquote>
		<img src=BEA_Button_Final_web.gif align=right>
		<h2>Please enter your user name and password:</h2>
		<p>
		<form method="POST" action="j_security_check">
			<table border=1>
				<tr>
					<td>Username:</td>
					<td><input type="text" name="j_username"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password"></td>
				</tr>
				<tr>
					<td colspan=2 align=right><input type=submit value="Submit"></td>
				</tr>
			</table>
		</form>
	</blockquote>
</body>
</html>