package adf.sample.managed.bean;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;


public class UserProfileBean {
    private RichPopup employeeEditPopup;
    private RichTable reportingEmployeesTable;

    public UserProfileBean() {
    }

    public String UpdateEmployeeButton() {
        employeeEditPopup.hide();
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.addPartialTarget(reportingEmployeesTable);
        return null;
    }

    public String cancelEmployeeUpdateButton() {
        employeeEditPopup.hide();
        AdfFacesContext adfFacesContext = AdfFacesContext.getCurrentInstance();
        adfFacesContext.addPartialTarget(reportingEmployeesTable);
        return null;
    }

    public void setEmployeeEditPopup(RichPopup employeeEditPopup) {
        this.employeeEditPopup = employeeEditPopup;
    }

    public RichPopup getEmployeeEditPopup() {
        return employeeEditPopup;
    }

    public void setReportingEmployeesTable(RichTable reportingEmployeesTable) {
        this.reportingEmployeesTable = reportingEmployeesTable;
    }

    public RichTable getReportingEmployeesTable() {
        return reportingEmployeesTable;
    }

    public void onEmployeeCreate(ActionEvent actionEvent) {
        // Add event code here...
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String onEmployeeCreate() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateWithParams");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        
        //showPopup
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        employeeEditPopup.show(hints);
        
        return null;
    }

    public void onDialogCancel(ActionEvent actionEvent) {
        BindingContainer bindings = getBindings();
        DCIteratorBinding iter = (DCIteratorBinding) bindings.get("reporteesIterator");
        Row rw = iter.getCurrentRow();
        rw.refresh(Row.REFRESH_REMOVE_NEW_ROWS | Row.REFRESH_UNDO_CHANGES);
    }
}
