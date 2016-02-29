package oracle.adffaces.handson.model;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import javax.faces.el.ValueBinding;

import javax.faces.event.ActionEvent;

import org.apache.myfaces.trinidad.component.core.data.CoreColumn;
import org.apache.myfaces.trinidad.component.core.data.CoreTable;
import org.apache.myfaces.trinidad.component.core.data.CoreTableSelectMany;
import org.apache.myfaces.trinidad.component.core.nav.CoreCommandButton;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.event.SelectionListener;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.SortableModel;

public class MultiSelectionTableHandler {
     
    private CoreTableSelectMany tableSelectMany;
    private CoreTable table;
    private SortableModel tableModel;
    private CoreCommandButton editButton;
    private CoreCommandButton commitButton;
    
    public MultiSelectionTableHandler() {
    
        Collection<CustomerBean> rows = new ArrayList<CustomerBean>();
        buildModel(rows);
        tableModel = new SortableModel( rows );
        FacesContext ctx = FacesContext.getCurrentInstance();
        table = (CoreTable)ctx.getApplication().createComponent(CoreTable.COMPONENT_TYPE);    
    }

    //get a list of selected rows and loop through it
    public void editSelected(ActionEvent evt){
        RowKeySet rowKeySet = table.getSelectionState();
        Set set = rowKeySet.getKeySet();
        resetSelected();
        for(Iterator itr = set.iterator(); itr.hasNext();){
           int index = Integer.parseInt((String)itr.next());
           CustomerBean bean = (CustomerBean)((List)tableModel.getWrappedData()).get(index);
           bean.setSelected(true);  
        }    
        disableOtherButtons((CoreCommandButton)evt.getComponent());
    }

    public void commitEdited(ActionEvent evt){
        resetSelected();
        table.getSelectionState().clear();
        disableOtherButtons((CoreCommandButton)evt.getComponent());
    }

    //not used in OOW hands-on, allows to delete selected table rows
    public void deleteSelectedRows(ActionEvent evt){
        Set<String> selection = table.getSelectionState().getKeySet();        
        for(String rowKey:selection) {
            table.setRowKey(rowKey);
            CustomerBean bean = (CustomerBean) table.getRowData();
            ((Collection)tableModel.getWrappedData()).remove(bean);
        }
        table.getSelectionState().clear();  
    }

    public void rebuildModel(ActionEvent evt){
        Collection rows = new ArrayList<CustomerBean>();
        buildModel(rows);
        tableModel.setWrappedData(rows);        
        table.getSelectionState().clear();
    }


    public void setTableSelectMany(CoreTableSelectMany tableSelectMany) {
        this.tableSelectMany = tableSelectMany;
    }

    public CoreTableSelectMany getTableSelectMany() {
        return tableSelectMany;
    }

    public void setTable(CoreTable table) {
        this.table = table;
    }

    public CoreTable getTable() {
        return table;
    }

    public void setTableModel(SortableModel tableModel) {
        this.tableModel = tableModel;
    }

    public SortableModel getTableModel() {
        return tableModel;
    }

    //disables the select button after pressing it. Enables it when 
    //commit is pressed
    private void disableOtherButtons(CoreCommandButton button){
        if(button == commitButton){
            editButton.setDisabled(false);
            commitButton.setDisabled(true);
        } else {
            editButton.setDisabled(true); 
            commitButton.setDisabled(false);
        }
    }
    
    private void resetSelected(){
        Collection<CustomerBean> customers = (Collection<CustomerBean>)tableModel.getWrappedData();
        for( CustomerBean customer:customers ){
            customer.setSelected(false);
        }
    }

    private void buildModel(Collection rows){
        CustomerBean cb = new CustomerBean();
        cb.setFullName( "Ric Smith" );
        cb.setCompany( "Oracle" );
        cb.setAddress( "500 Oracle Parkway Redwood Shores, CA 94065" );
        cb.setEmail( "ric.smith@oracle.com" );
        cb.setPhone( "(555) 455-2341" );
        rows.add( cb );
        cb = new CustomerBean();
        cb.setFullName( "Frank Nimphius" );
        cb.setCompany( "Oracle" );
        cb.setAddress( "500 Oracle Parkway Redwood Shores, CA 94065" );
        cb.setEmail( "frank.nimphius@oracle.com" );
        cb.setPhone( "(555) 455-2342" );
        rows.add( cb );
        cb = new CustomerBean();
        cb.setFullName( "Patrice Daux" );
        cb.setCompany( "Oracle" );
        cb.setAddress( "500 Oracle Parkway Redwood Shores, CA 94065" );
        cb.setEmail( "patrice.daux@oracle.com" );
        cb.setPhone( "(555) 455-2343" );
        rows.add( cb );
        cb = new CustomerBean();
        cb.setFullName( "Thomas David" );
        cb.setCompany( "Acme" );
        cb.setAddress( "200 Noway Parkway Foster City, CA 94404" );
        cb.setEmail( "tdavid@acme.com" );
        cb.setPhone( "(555) 452-2331" );
        rows.add( cb );
        cb = new CustomerBean();
        cb.setFullName( "Mike Jones" );
        cb.setCompany( "Sky Dive USA" );
        cb.setAddress( "2213 Mile High Parkway Redwood City, CA 94065" );
        cb.setEmail( "mjones@sdive.com" );
        cb.setPhone( "(523) 551-3331" );
        rows.add( cb );
        cb = new CustomerBean();
        cb.setFullName( "Crazy Eddie" );
        cb.setCompany( "USA Motorcycle Inc." );
        cb.setAddress( "2213 Ruff and Tuff Parkway Redwood City, CA 94065" );
        cb.setEmail( "ceddie@crazyman.com" );
        cb.setPhone( "(623) 651-6331" );
        rows.add( cb );
        cb = new CustomerBean();
        cb.setFullName( "Mountain Jack" );
        cb.setCompany( "Lumbar Inc." );
        cb.setAddress( "270 Moutain Ave. Redwood City, CA 94065" );
        cb.setEmail( "mjack@lumbar.com" );
        cb.setPhone( "(723) 751-6331" );
        rows.add( cb );
        cb = new CustomerBean();
        cb.setFullName( "Bill Bo" );
        cb.setCompany( "Cigars and Stuff Inc." );
        cb.setAddress( "111 Smokey Ave. Redwood City, CA 94065" );
        cb.setEmail( "bbo@cigars.com" );
        cb.setPhone( "(757) 851-5331" );
        rows.add( cb );
        cb = new CustomerBean();
        cb.setFullName( "Slick Willie" );
        cb.setCompany( "Used Cars" );
        cb.setAddress( "123 Cheaper and cheaper Ave. Redwood City, CA 94065" );
        cb.setEmail( "swillie@ucars.com" );
        cb.setPhone( "(858) 951-5331" );
        rows.add( cb );
        cb = new CustomerBean();
        cb.setFullName( "Mac Dads" );
        cb.setCompany( "Great Burgers" );
        cb.setAddress( "5464 Greasey Ave. Redwood City, CA 94065" );
        cb.setEmail( "mdads@gburgers.com" );
        cb.setPhone( "(768) 967-5331" );
        rows.add( cb );
        cb = new CustomerBean();
        cb.setFullName( "Pete Smith" );
        cb.setCompany( "Pete's Pizza" );
        cb.setAddress( "234 Cheesey Ave. Redwood City, CA 94065" );
        cb.setEmail( "psmith@pizza.com" );
        cb.setPhone( "(268) 267-2331" );
        rows.add( cb );    
        cb = new CustomerBean();
        cb.setFullName( "Jimmy Day" );
        cb.setCompany( "Daily Paper" );
        cb.setAddress( "6543 Writers way. Redwood City, CA 94065" );
        cb.setEmail( "jday@dpaper.com" );
        cb.setPhone( "(368) 268-2331" );
        rows.add( cb ); 
        cb = new CustomerBean();
        cb.setFullName( "Buckey Bates" );
        cb.setCompany( "Buck's Brew House" );
        cb.setAddress( "3223 Brew way. Redwood City, CA 94065" );
        cb.setEmail( "bbates@bbhouse.com" );
        cb.setPhone( "(432) 765-2132" );
        rows.add( cb ); 
    }

    public void setEditButton(CoreCommandButton editButton) {
        this.editButton = editButton;
    }

    public CoreCommandButton getEditButton() {
        return editButton;
    }

    public void setCommitButton(CoreCommandButton commitButton) {
        this.commitButton = commitButton;
    }

    public CoreCommandButton getCommitButton() {
        return commitButton;
    }
}
