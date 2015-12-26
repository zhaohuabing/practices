package oracle.adffaces.handson.managed.bean;

import java.io.IOException;

import java.util.Calendar;
import java.util.HashMap;

import java.util.Map;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.el.VariableResolver;

import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.apache.myfaces.trinidad.component.core.input.CoreSelectOneChoice;
import org.apache.myfaces.trinidad.component.core.output.CoreOutputFormatted;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.ReturnEvent;



/**
 * Bean that implements dialog framework functionality
 */
public class DialogBean {
    private CoreSelectOneChoice selectOneChoice1;
    private CoreSelectOneChoice selectOneChoice2;
    private Map stateSelectItems;
    private CountryBean countries;
    private CoreOutputFormatted userCreationDateComponent;
    private String skinSelection;

    // dynamically handle skinning on initialization
    public DialogBean() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        String skin = (String) ((HttpSession) fctx.getExternalContext().getSession(false)).getAttribute("skinFamily");
        if (skin == null){
            skinSelection = "oracle";        
        }
        else{
            skinSelection = skin;
        }
    }
    
    //helper function to obtain managed bean by name
    public Object getManagedBeanByName( String beanName ) {
        Object managedBean = null;
        HttpServletRequest request =
            (HttpServletRequest) FacesContext
                .getCurrentInstance()
                    .getExternalContext()
                        .getRequest();
     
        managedBean = request.getAttribute( beanName );
        
        if (managedBean == null) {
            // lookup bean in session scope
            managedBean = request.getSession().getAttribute( beanName );
        }

        return managedBean;
    }

    public Map getStateSelectItems(){
        if( stateSelectItems == null ){
            FacesContext ctx = FacesContext.getCurrentInstance();
            Application app  = ctx.getApplication();
            VariableResolver vr = app.getVariableResolver();
            countries = (CountryBean) vr.resolveVariable(ctx, "countrybean");
            stateSelectItems = countries.getStatesValueMap();
        }
        return stateSelectItems;
    }

    //obtain cities based on the stateValue selection. The stateValue selection
    //is set when the first listbox performs an autosubmit
    public Map getCitySelectItems(){
        Map cities = new HashMap();
        if(selectOneChoice1 != null && selectOneChoice1.getValue() != null){
            FacesContext ctx = FacesContext.getCurrentInstance();
            Application app  = ctx.getApplication();
            VariableResolver vr = app.getVariableResolver();
            countries = (CountryBean) vr.resolveVariable(ctx, "countrybean");            
            String stateValue = (String)selectOneChoice1.getValue();                
            cities = countries.lookupCitiesValueMap( stateValue );
        } 
        return cities;
    }
    
    public void setUserCreationDateComponent(CoreOutputFormatted userCreationDateComponent) {
        this.userCreationDateComponent = userCreationDateComponent;
    }

    public CoreOutputFormatted getUserCreationDateComponent() {
        return userCreationDateComponent;
    }
    
    // set the skin family based on a session attribute
    public void setSkinselection(String skinselection) {
        if (skinselection == null){
            skinselection = "oracle";
        }
        this.skinSelection = skinselection;
        FacesContext fctx = FacesContext.getCurrentInstance();
        ((HttpSession) fctx.getExternalContext().getSession(false)).setAttribute("skinFamily",skinselection);
       
       /**
        * Need to redirect to ensure new skin is read
        */
       
        try {
            fctx.getExternalContext().redirect("faces/Main.jspx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSkinselection() {
        return skinSelection;
    }

    /* *************************************************************************
     *                              ADD CUSTOM CODE
     * *************************************************************************/


    public void setSelectOneChoice1(CoreSelectOneChoice selectOneChoice1) {
        this.selectOneChoice1 = selectOneChoice1;
    }

    public CoreSelectOneChoice getSelectOneChoice1() {
        return selectOneChoice1;
    }

    public void setSelectOneChoice2(CoreSelectOneChoice selectOneChoice2) {
        this.selectOneChoice2 = selectOneChoice2;
    }

    public CoreSelectOneChoice getSelectOneChoice2() {
        return selectOneChoice2;
    }

    public void resetSelectCity(ValueChangeEvent valueChangeEvent) {
         selectOneChoice2.setValue(null);
    }

    //write message when dialog is submitted
    public String submitDialogAction() {
        RequestContext afContext = RequestContext.getCurrentInstance();
        String today = Calendar.getInstance().getTime().toString();
        Object message = new String("<b>New user created: "+ today +"</b>");
        afContext.returnFromDialog(message,null);
        return null;
    }

    //close the dialog and return to the calling form
    public void returnFromProcess(ReturnEvent returnEvent) {
        Object o = returnEvent.getReturnValue();
        if (o!=null){
            String message = (String) o;
            this.getUserCreationDateComponent().setValue(message);
        }
    }
}
