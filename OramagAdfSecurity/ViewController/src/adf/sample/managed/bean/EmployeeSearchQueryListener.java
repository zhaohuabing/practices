package adf.sample.managed.bean;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;

import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.nav.RichTrain;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.QueryEvent;

import oracle.binding.BindingContainer;


public class EmployeeSearchQueryListener {
    private RichTrain trainComponent;
    
    public EmployeeSearchQueryListener() {
    }

    /**
     * Method that hooks into the search query to enable employee edit navigation 
     * if employees are found for search criteria
     * @param queryEvent
     */
    public void queryListener(QueryEvent queryEvent) {
        //#{bindings.ImplicitViewCriteriaQuery.processQuery}
        // Add event code here...
        
        FacesContext fctx = FacesContext.getCurrentInstance();
        ELContext elctx = fctx.getELContext();
        ExpressionFactory exprFactory = fctx.getApplication().getExpressionFactory();
        MethodExpression me = exprFactory.createMethodExpression(elctx, "#{bindings.ImplicitViewCriteriaQuery.processQuery}",
                                                                 Object.class, new Class[]{QueryEvent.class});
        me.invoke(elctx, new Object[] {queryEvent});
        
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        DCIteratorBinding allEmployeesIter = (DCIteratorBinding) bindings.get("allEmployeesIterator");
        
        boolean skipEditForm = allEmployeesIter.getEstimatedRowCount() > 0 ? false : true;
        
        ADFContext adfCtx = ADFContext.getCurrent();
        
        adfCtx.getPageFlowScope().put("skipEditEmployees", skipEditForm);

        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.addPartialTarget(trainComponent);
    }

    public void setTrainComponent(RichTrain trainComponent) {
        this.trainComponent = trainComponent;
    }

    public RichTrain getTrainComponent() {
        return trainComponent;
    }
}
