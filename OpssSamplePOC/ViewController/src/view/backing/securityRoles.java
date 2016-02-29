package view.backing;

import java.io.IOException;

import java.io.PrintWriter;

import java.security.AccessController;

import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Permissions;
import java.security.PrivilegedExceptionAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import javax.faces.event.ActionEvent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import oracle.adf.controller.security.TaskFlowPermission;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.security.jps.JpsContext;
import oracle.security.jps.JpsContextFactory;
import oracle.security.jps.JpsException;
import oracle.security.jps.config.ExtendedProperty;
import oracle.security.jps.principals.JpsApplicationRole;
import oracle.security.jps.service.policystore.ApplicationPolicy;
import oracle.security.jps.service.policystore.ApplicationRoleAttributes;
import oracle.security.jps.service.policystore.PolicyObjectNotFoundException;
import oracle.security.jps.service.policystore.PolicyStore;
import oracle.security.jps.service.policystore.entitymanager.ExtensionManager;
import oracle.security.jps.service.policystore.entitymanager.ResourceTypeManager;
import oracle.security.jps.service.policystore.info.AttributeEntry;
import oracle.security.jps.service.policystore.info.BasicPrincipalEntry;
import oracle.security.jps.service.policystore.info.Expression;
import oracle.security.jps.service.policystore.info.FunctionEntry;
import oracle.security.jps.service.policystore.info.OpssString;
import oracle.security.jps.service.policystore.info.PrincipalEntry;

import oracle.security.jps.service.policystore.info.RuleExpressionEntry;
import oracle.security.jps.service.policystore.info.resource.ResourceTypeEntry;

import weblogic.security.principal.WLSGroupImpl;

import oracle.security.jps.service.policystore.entitymanager.*;
import oracle.security.jps.service.policystore.info.*;
import oracle.security.jps.service.policystore.info.resource.ResourceActionsEntry;
import oracle.security.jps.service.policystore.info.resource.ResourceEntry;

import weblogic.security.principal.WLSUserImpl;

public class securityRoles {
    private RichInputText roleName;
    private RichInputText displayName;
    private RichInputText description;
    private RichInputText existingRoleName;
    private RichInputText newDisplayName;
    private RichInputText newDescription;
    private RichInputText deletingRoleName;

    private static final String CONTENT_TYPE = "text/html; charset=US-ASCII";
    private String APP_NAME = "OpssSamplePOC";
    private static final String RESOURCE_TYPE_1 = "WebPage";
    private static final String RESOURCE_TYPE_2 = "TaskFlows";
    private static final String RESOURCE_NAME_1 = "view.pageDefs.testRolesPageDef";
    private static final String RESOURCE_NAME_D_1 = "testRolesPageDef";
    private static final String RESOURCE_NAME_2 = "/WEB-INF/task-flow-roles.xml#task-flow-roles";
    private static final String RESOURCE_NAME_D_2 = "taskflowroles";
    private static final String PERMISSION_SET_NAME = "PepPermissionSet";
    private static final String ACTION_READ = "view";
    private static final String ACTION_WRITE = "write";
    private static final String PEP_POLICY = "pep_policy";
    private static final String PEP_POLICY_RULE = "pep_policy_rule";
    private static final String PEP_ATTR = "PepAttr";

    public securityRoles() {
        super();
    }

    public void setRoleName(RichInputText roleName) {
        this.roleName = roleName;
    }

    public RichInputText getRoleName() {
        return roleName;
    }

    public void setDisplayName(RichInputText displayName) {
        this.displayName = displayName;
    }

    public RichInputText getDisplayName() {
        return displayName;
    }

    public void setDescription(RichInputText description) {
        this.description = description;
    }

    public RichInputText getDescription() {
        return description;
    }

    public void createNewRole(ActionEvent actionEvent) {
        JpsContextFactory ctxf;
        try {
            ctxf = JpsContextFactory.getContextFactory();
            JpsContext ctx = ctxf.getContext();

            PolicyStore policyStore =
                ctx.getServiceInstance(PolicyStore.class);
            ApplicationPolicy applicationPolicy =
                policyStore.getApplicationPolicy("OpssSamplePOC");

            ExtendedProperty ep = new ExtendedProperty();
            String attribute = ApplicationRoleAttributes.SCOPE.name();
            List<String> values = new ArrayList<String>();
            values.add("user-defined-value");
            ep.setProperty(attribute, values);

            List<JpsApplicationRole> roles =
                (List<JpsApplicationRole>)applicationPolicy.getAllAppRoles();

            if (null != roles) {
                for (int i = 0; i < roles.size(); i++) {
                    System.out.println("roles->" + roles.get(i));
                }
            }

            applicationPolicy.createAppRole(roleName.getValue().toString(),
                                            displayName.getValue().toString(),
                                            description.getValue().toString(),
                                            ep);

            // Assigning Role to User
            PrincipalEntry principalEntry =
                new BasicPrincipalEntry(WLSUserImpl.class.getName(),
                                        "anshul");
            applicationPolicy.addPrincipalToAppRole(principalEntry,
                                                    roleName.getValue().toString());

            addPolicy(roleName.getValue().toString());
//            addNewPolicy(roleName.getValue().toString());
        } catch (JpsException e) {
            e.printStackTrace();
        }
    }

    public void setExistingRoleName(RichInputText existingRoleName) {
        this.existingRoleName = existingRoleName;
    }

    public RichInputText getExistingRoleName() {
        return existingRoleName;
    }

    public void setNewDisplayName(RichInputText newDisplayName) {
        this.newDisplayName = newDisplayName;
    }

    public RichInputText getNewDisplayName() {
        return newDisplayName;
    }

    public void setNewDescription(RichInputText newDescription) {
        this.newDescription = newDescription;
    }

    public RichInputText getNewDescription() {
        return newDescription;
    }

    public void editRole(ActionEvent actionEvent) {
        JpsContextFactory ctxf;
        try {
            ctxf = JpsContextFactory.getContextFactory();
            JpsContext ctx = ctxf.getContext();

            PolicyStore policyStore =
                ctx.getServiceInstance(PolicyStore.class);
            ApplicationPolicy applicationPolicy =
                policyStore.getApplicationPolicy("OpssSamplePOC");

            applicationPolicy.alterAppRole(existingRoleName.getValue().toString(),
                                           newDisplayName.getValue().toString(),
                                           newDescription.getValue().toString());

        } catch (JpsException e) {
            e.printStackTrace();
        }
    }

    public void setDeletingRoleName(RichInputText deletingRoleName) {
        this.deletingRoleName = deletingRoleName;
    }

    public RichInputText getDeletingRoleName() {
        return deletingRoleName;
    }

    public void deleteRole(ActionEvent actionEvent) {
        JpsContextFactory ctxf;
        try {
            ctxf = JpsContextFactory.getContextFactory();
            JpsContext ctx = ctxf.getContext();

            PolicyStore policyStore =
                ctx.getServiceInstance(PolicyStore.class);
            ApplicationPolicy applicationPolicy =
                policyStore.getApplicationPolicy("OpssSamplePOC");

            applicationPolicy.removeAppRole(deletingRoleName.getValue().toString());

        } catch (JpsException e) {
            e.printStackTrace();
        }
    }

    public void addPolicy(String roleName) {
        JpsContextFactory ctxf;
        try {
            ctxf = JpsContextFactory.getContextFactory();
            JpsContext ctx = ctxf.getContext();

            PolicyStore policyStore =
                ctx.getServiceInstance(PolicyStore.class);
            ApplicationPolicy applicationPolicy =
                policyStore.getApplicationPolicy("OpssSamplePOC");
            

            /* Creates a ResourceTypeEntry with the actions list */
            ResourceTypeManager resTypeMgr =
                applicationPolicy.getResourceTypeManager();
            /* Creates an actions list for action "read" and "write" */
            List<String> actions = new ArrayList<String>();
            actions.add(ACTION_READ);
//            actions.add(ACTION_WRITE);

            ResourceTypeEntry type1 =
                resTypeMgr.createResourceType(RESOURCE_TYPE_1, RESOURCE_TYPE_1,
                                              RESOURCE_TYPE_1, actions, null,
                                              ",","oracle.adf.share.security.authorization.RegionPermission");
            ResourceTypeEntry type2 =
                resTypeMgr.createResourceType(RESOURCE_TYPE_2, RESOURCE_TYPE_2,
                                              RESOURCE_TYPE_2, actions, null,
                                              ",","oracle.adf.controller.security.TaskFlowPermission");

            /* Create permission set */
            ResourceManager resMgr = applicationPolicy.getResourceManager();
            ResourceEntry pepResource1 =
                resMgr.createResource(RESOURCE_NAME_1, RESOURCE_NAME_D_1,
                                      RESOURCE_NAME_D_1, type1, null);
            ResourceEntry pepResource2 =
                resMgr.createResource(RESOURCE_NAME_2, RESOURCE_NAME_D_2,
                                      RESOURCE_NAME_D_2, type2, null);
            
            PermissionSetManager psm =
                applicationPolicy.getPermissionSetManager();
            List<ResourceActionsEntry> resActsList =
                new ArrayList<ResourceActionsEntry>();
            actions = new ArrayList<String>();
            actions.add(ACTION_READ);
            //uncomment the following line to grant "write" action
            //actions.add(ACTION_WRITE);
            resActsList.add(new BasicResourceActionsEntry(pepResource1,
                                                          actions));
            resActsList.add(new BasicResourceActionsEntry(pepResource2,
                                                          actions));
            PermissionSetEntry permSet =
                psm.createPermissionSet(PERMISSION_SET_NAME,
                                        PERMISSION_SET_NAME,
                                        PERMISSION_SET_NAME, resActsList);
                                                                              

            List<PermissionSetEntry> permSets =
                new ArrayList<PermissionSetEntry>();
            permSets.add(permSet);

            GrantManager gm =
                applicationPolicy.getEntityManager(GrantManager.class);
            Set<PrincipalEntry> pe = new HashSet<PrincipalEntry>();
            List<AppRoleEntry> are =
                applicationPolicy.searchAppRoles(roleName);
            pe.addAll(are);
            gm.grant(pe, null, PERMISSION_SET_NAME);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewPolicy(String roleName) {
        JpsContextFactory ctxf;
        try {

            ctxf = JpsContextFactory.getContextFactory();
            JpsContext ctx = ctxf.getContext();

            PolicyStore policyStore =
                ctx.getServiceInstance(PolicyStore.class);
            ApplicationPolicy appPolicy =
                policyStore.getApplicationPolicy("OpssSamplePOC");


            ResourceTypeManager resTypeMgr =
                appPolicy.getResourceTypeManager();

            /* Creates an actions list for action "read" and "write" */
            List<String> actions = new ArrayList<String>();
            actions.add(ACTION_READ);
            actions.add(ACTION_WRITE);


            /* Creates a ResourceTypeEntry with the actions list */
            ResourceTypeEntry type =
                resTypeMgr.createResourceType(RESOURCE_TYPE_1, RESOURCE_TYPE_1,
                                              RESOURCE_TYPE_1, actions, null,
                                              ",");

            /* Obtain an instance of ExtensionManager */
            ExtensionManager extensionMgr = appPolicy.getExtensionManager();

            AttributeEntry<OpssString> attrEntry =
                extensionMgr.createAttribute(PEP_ATTR, PEP_ATTR, PEP_ATTR,
                                             OpssString.class,
                                             AttributeEntry.AttributeCategory.DYNAMIC,
                                             true);

            FunctionEntry strEqFunc =
                extensionMgr.getFunction(RuleExpressionEntry.BuiltInFunctions.STRING_EQUAL.toString());

            Expression expr = new Expression(strEqFunc);
            expr.addExpressionComponent(attrEntry);
            expr.addExpressionComponent(new OpssString("hello"));

            BooleanExpressionEntry condition =
                new BooleanExpressionEntry(expr);


            ResourceManager resMgr = appPolicy.getResourceManager();
            /* Creates a ResourceEntry for the ResourceTypeEntry "type" */
            ResourceEntry pepResource =
                resMgr.createResource(RESOURCE_NAME_1, RESOURCE_NAME_D_1,
                                      RESOURCE_NAME_D_1, type, null);


            /* Create permission set */
            PermissionSetManager psm = appPolicy.getPermissionSetManager();
            List<ResourceActionsEntry> resActsList =
                new ArrayList<ResourceActionsEntry>();
            actions = new ArrayList<String>();
            actions.add(ACTION_READ);
            //uncomment the following line to grant "write" action
            //actions.add(ACTION_WRITE);
            resActsList.add(new BasicResourceActionsEntry(pepResource,
                                                          actions));
            PermissionSetEntry permSet =
                psm.createPermissionSet(PERMISSION_SET_NAME,
                                        PERMISSION_SET_NAME,
                                        PERMISSION_SET_NAME, resActsList);

            List<PermissionSetEntry> permSets =
                new ArrayList<PermissionSetEntry>();
            permSets.add(permSet);


            //create policy rule
            @SuppressWarnings("unchecked")
            PolicyRuleEntry grantRule =
                new BasicPolicyRuleEntry(PEP_POLICY_RULE, PEP_POLICY_RULE,
                                         PEP_POLICY_RULE,
                                         PolicyRuleEntry.EffectType.GRANT,
                                         condition);

            List<PrincipalEntry> principalList =
                new ArrayList<PrincipalEntry>();
            PrincipalEntry principalEntry =
                new BasicPrincipalEntry(WLSUserImpl.class.getName(),
                                        roleName);
            principalList.add(principalEntry);


            // obligations
            List<ObligationEntry> obs = new ArrayList<ObligationEntry>();
            {
                List<AttributeAssignment<? extends DataType>> assignments =
                    new ArrayList<AttributeAssignment<? extends DataType>>();
                assignments.add(new AttributeAssignment<OpssString>("attr_string",
                                                                    new OpssString("World")));
                assignments.add(new AttributeAssignment<OpssInteger>("attr_integer",
                                                                     new OpssInteger(33)));
                assignments.add(new AttributeAssignment<OpssBoolean>("attr_boolean",
                                                                     new OpssBoolean(false)));
                ObligationEntry obligation =
                    new BasicObligationEntry("BasicObligation",
                                             "String displayName",
                                             "String description",
                                             assignments);
                obs.add(obligation);
            }


            // creates policy
            PolicyManager pm = appPolicy.getPolicyManager();
            pm.createPolicy(PEP_POLICY, PEP_POLICY, PEP_POLICY, grantRule,
                            permSets, principalList, obs);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
