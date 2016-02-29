package adf.sample.managed.bean;

import java.io.Serializable;

import java.util.HashMap;

import oracle.adf.controller.security.TaskFlowPermission;
import oracle.adf.share.ADFContext;
import oracle.adf.share.security.SecurityContext;


/**
 *  Dynamic regions can have security implemented - e.g. checking ADF Security permissions for a user. This sample
 *  becomes even more useful if UI Shell is used to dynamically switch between task flows, in which case using 
 *  the managed bean for ADF Security checking allows developers to implement sensible fallbacks
 */

public class UserSearchBean {
    
    private String taskFlowId = "/WEB-INF/regions/browse-employees-btf.xml#browse-employees-btf";
    private String emptyTaskFlow = "";
    
    private SecurityTaskFlowPermission taskFlowPermissionContext = new SecurityTaskFlowPermission();

    public UserSearchBean() {
    }

    public String getDynamicTaskFlowId() {        
        
        //check user permission to access taskflow. If task flow is not accessible, show
        //empty task flow. This check is redunant and implemented for defense in depth
        //security. 
        
        if (getTaskFlowPermissionContext().get(taskFlowId) == true){
            return taskFlowId;
        }
        
        return emptyTaskFlow;
    }

    public void setTaskFlowPermissionContext(UserSearchBean.SecurityTaskFlowPermission taskFlowPermissionContext) {
        this.taskFlowPermissionContext = taskFlowPermissionContext;
    }

    public UserSearchBean.SecurityTaskFlowPermission getTaskFlowPermissionContext() {
        return taskFlowPermissionContext;
    }

    private class SecurityTaskFlowPermission extends HashMap implements Serializable{

        @SuppressWarnings("compatibility:5589447287091354646")
        private static final long serialVersionUID = 1377012755698161708L;

        public Boolean get(Object key) {
            Boolean hasPermission = Boolean.FALSE;  
            
            ADFContext adfCtx = ADFContext.getCurrent();
            SecurityContext secCtx = adfCtx.getSecurityContext();
            
            //TaskFlowPermission tf document, "view"
            TaskFlowPermission tfPwermission = new TaskFlowPermission((String) key, "view"); 
            
            boolean permCheckSuccess = secCtx.hasPermission(tfPwermission);
                                   
            hasPermission = (Boolean.valueOf(permCheckSuccess)) == Boolean.TRUE ? Boolean.TRUE : Boolean.FALSE;
                      
            return hasPermission;
        }
    }
}
