package oracle.adffaces.handson.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.trinidad.component.core.data.CoreTable;
import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.model.SortableModel;

public class SingleSelectionTableHandler {
    
    private SortableModel tableModel;
    private CoreTable table1;

    public SingleSelectionTableHandler() {
        
        Collection rows = new ArrayList<CustomerBean>();
        buildModel(rows);
        tableModel = new SortableModel( rows );
        FacesContext ctx = FacesContext.getCurrentInstance();
        table1 = (CoreTable)ctx.getApplication().createComponent(CoreTable.COMPONENT_TYPE);
        
    }

    
    public void rebuildModel(ActionEvent evt){
        Collection rows = new ArrayList<CustomerBean>();
        buildModel(rows);
        tableModel.setWrappedData(rows);        
    }

/**
     * function that populates the table with data. In a real application this
     * method would use a database connection to fetch the data to work with
     * @param rows Collection of customer wor data
     */
     
    private void buildModel(Collection rows){
        CustomerBean cb = new CustomerBean();
        cb.setFullName( "Ric Smith" );
        cb.setCompany( "Oracle" );
        cb.setAddress( "500 Oracle Parkway Redwood Shores, CA 94065" );
        cb.setEmail( "ric.smith@oracle.com" );
        cb.setPhone( "(555) 455-2341" );
        rows.add( cb );
        cb = new CustomerBean();
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

    public void setTableModel(SortableModel tableModel) {
        this.tableModel = tableModel;
    }

    public SortableModel getTableModel() {
        return tableModel;
    }

    public String editReturnDialog_action() {

          RequestContext afContext = RequestContext.getCurrentInstance();
          afContext.returnFromDialog(null,null);
        return null;
    }

    public void setTable1(CoreTable table1) {
        this.table1 = table1;
    }

    public CoreTable getTable1() {
        return table1;
    }
}
