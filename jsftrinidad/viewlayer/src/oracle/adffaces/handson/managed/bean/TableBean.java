package oracle.adffaces.handson.managed.bean;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.el.VariableResolver;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.trinidad.component.core.nav.CoreCommandButton;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.ReturnEvent;

import oracle.adffaces.handson.model.SingleSelectionTableHandler;


public class TableBean {
    
    private CoreCommandButton commandButton1;

    public TableBean() {
    }

    //launch the dialog to edit the selected table row
    public void launchEditDialog(ActionEvent actionEvent) {

             FacesContext ctx = FacesContext.getCurrentInstance();
             ViewHandler viewHandler = ctx.getApplication().getViewHandler();
             //reference table editing page
             UIViewRoot dialog = viewHandler.createView(ctx, "/EditSingleTableRow.jspx");
             Application app = ctx.getApplication();
             VariableResolver resolver = app.getVariableResolver();
             SingleSelectionTableHandler handler = (SingleSelectionTableHandler)resolver.resolveVariable( ctx, "singleselectionhandler" );

             if(handler.getTable1().getSelectedRowData() != null){
                 RequestContext afContext = RequestContext.getCurrentInstance();
                 afContext.launchDialog(dialog, null, commandButton1, true, null); 
             }
         }
    

    public void setCommandButton1(CoreCommandButton commandButton1) {
        this.commandButton1 = commandButton1;
    }

    public CoreCommandButton getCommandButton1() {
        return commandButton1;
    }
    
    //return from dialog
    public void processReturnFromEdit(ReturnEvent returnEvent) {
         FacesContext ctx = FacesContext.getCurrentInstance();
         Application app = ctx.getApplication();
         VariableResolver resolver = app.getVariableResolver();
         SingleSelectionTableHandler handler = (SingleSelectionTableHandler)resolver.resolveVariable( ctx, "singleselectionhandler" );
         handler.getTable1().getSelectionState().clear();
         //dynamically implement partial page triggering
         RequestContext afContext = RequestContext.getCurrentInstance();
         afContext.addPartialTarget(handler.getTable1());
         afContext.partialUpdateNotify(commandButton1);        
    }
}
