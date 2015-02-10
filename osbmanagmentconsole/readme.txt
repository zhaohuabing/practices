Ò»¡¢Do not deploy this app as a war package.
Because the configuration files cannot be find as a war deployed in weblogic, Please deploy the app expanded instead of war. 
1.Copy the ¡°OSBManagmentConsole¡± dir to "MIDDLEWARE_HOME\user_projects\domains\"DOMAIN_NAME"\servers\"SERVER_NAME"\upload" dir ,for example "D:\Oracle\Middleware\user_projects\domains\practices_domain\servers\AdminServer\upload".
2.Deploy the app by weblogic Administrator Console.

¶þ¡¢JSF 2.0 library deployment
This app need JSF library deployed on the Weblogic Server. Use the Weblogic Administrator Console to deploy the JSF2.0("MIDDLEWARE_HOME\wlserver_10.3\common\deployable-libraries\jsf-2.0.war").

Èý¡¢authorization
Create a "osbmonitor" user in weblogic security realm and login. 
