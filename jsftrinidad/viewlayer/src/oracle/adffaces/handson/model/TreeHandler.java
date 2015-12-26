package oracle.adffaces.handson.model;
/**
 * TreeHandler.java containes the tree definition and the table data. Note that 
 * in a real production environment, the tree and the table data would be read 
 * dynamically from a data source like EJB, JDBC or POJO
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.myfaces.trinidad.component.core.data.CoreTable;
import org.apache.myfaces.trinidad.model.ChildPropertyTreeModel;
import org.apache.myfaces.trinidad.model.SortableModel;
import org.apache.myfaces.trinidad.model.TreeModel;


public class TreeHandler {

    private GenericTreeNode root;
    private TreeModel treemodel;
    private SortableModel tableModel;
    private Collection<EmployeeBean> rows;
    private GenericTreeNode selectedNode;
    private CoreTable detailTable;

    public TreeHandler() {
        createTree();
        initiateTable();
    }
    
    private void createTree(){
    
        root = new GenericTreeNode();
    
        ResourceBundle rb = ResourceBundle.getBundle("oracle.adffaces.handson.resources.treedata");
        for(Enumeration e = rb.getKeys(); e.hasMoreElements();){
            String name = (String)e.nextElement();
            GenericTreeNode location_node = new GenericTreeNode();
            location_node.setDescription(name);
            location_node.setNodeType("location");
            String departments = rb.getString( name );
            location_node.setChildren(createSubNode(departments));
            root.getChildren().add(location_node);
        }
    }
    
    private Collection  createSubNode(String departments){
        List<GenericTreeNode> listOfDepartments = new ArrayList<GenericTreeNode>();
        String[] departmentsArray = departments.split(",");
        for( String department:departmentsArray ){
            GenericTreeNode departmentNode = new GenericTreeNode(department.replaceAll("\"", ""),"department");
            listOfDepartments.add(departmentNode);
        }
        return Collections.unmodifiableList(listOfDepartments);
    }
    
    public void setRoot(GenericTreeNode root) {
        this.root = root;
    }

    public GenericTreeNode getRoot() {
        return root;
    }

    public void setTreemodel(TreeModel treemodel) {
        this.treemodel = treemodel;
    }

    public TreeModel getTreemodel() {
        
        List nodes = new ArrayList();
        nodes.add(root);
        treemodel = new ChildPropertyTreeModel(nodes, "children") {
                    public boolean isContainer() {
                        return ((GenericTreeNode)getRowData()).getChildCount() > 
                            0;
                    }

                };
        
        return treemodel;
    }



/*
 *      ---------------- CREATE DETAIL TABLE ------------------------
 * 
 */

    public void setTableModel(SortableModel tableModel) {
        this.tableModel = tableModel;
    }

    public SortableModel getTableModel() {
        return tableModel;
    }
    
    /**
     * Call for master - detail refresh
     * @param department
     */
    private void buildDetail(String department){
        
        rows = new ArrayList<EmployeeBean>();
        
        /* Some static data */
        
        if (department.equalsIgnoreCase("Administration")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Nimphius");
            ebean.setEmail("fnimphius");
            ebean.setHiredate("12-Jan-2001");
            ebean.setSalary("10000");
            ebean.setPhone("1234-567-89");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("Louis");
             ebean.setEmail("rlouis");
             ebean.setHiredate("10-May-2001");
             ebean.setSalary("12000");
             ebean.setPhone("1234-567-89");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("Smith");
              ebean.setEmail("rsmith");
              ebean.setHiredate("12-Dec-2005");
              ebean.setSalary("8000");
              ebean.setPhone("1234-567-89");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("shmeltzer");
               ebean.setEmail("sshmeltzer");
               ebean.setHiredate("12-Jan-1999");
               ebean.setSalary("11000");
            ebean.setPhone("1234-567-89");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Purchasing")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Ronald");
            ebean.setEmail("gronald");
            ebean.setHiredate("12-Jan-2001");
            ebean.setSalary("10000");
            ebean.setPhone("1234-567-89");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("Duncan");
             ebean.setEmail("sduncan");
             ebean.setHiredate("10-May-2001");
             ebean.setPhone("1234-567-89");             
             ebean.setSalary("12000");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("Carlin");
              ebean.setEmail("jcarlin");
              ebean.setHiredate("12-Dec-2005");
              ebean.setPhone("1234-567-89");              
              ebean.setSalary("8000");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("Stalman");
               ebean.setEmail("rstalman");
               ebean.setHiredate("12-Jan-1999");
               ebean.setPhone("1234-567-89");               
               ebean.setSalary("11000");
               rows.add(ebean);
               /* ----------------- */
            this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Executive")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Ellison");
            ebean.setEmail("lellison");
            ebean.setHiredate("12-Jan-1976");
            ebean.setPhone("1234-567-89");
            ebean.setSalary("100000");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("Kurian");
             ebean.setEmail("tkurian");
             ebean.setHiredate("10-Aug-1987");
             ebean.setSalary("52000");
             ebean.setPhone("1234-567-89");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("Job");
              ebean.setEmail("cjob");
              ebean.setHiredate("12-Jan-1999");
              ebean.setPhone("1234-567-89");              
              ebean.setSalary("300000");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("farrell");
               ebean.setEmail("tfarrell");
               ebean.setPhone("1234-567-89");
               ebean.setHiredate("12-Jan-1990");
               ebean.setSalary("25000");
               rows.add(ebean);
               /* ----------------- */
            this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Treasury")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Fry");
            ebean.setEmail("bfry");
            ebean.setPhone("1234-567-89");
            ebean.setHiredate("12-Jan-2001");
            ebean.setSalary("10000");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("ramackers");
             ebean.setEmail("gramackers");
             ebean.setHiredate("10-May-2001");
             ebean.setPhone("1234-567-89");
             ebean.setSalary("12000");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("clevenger");
              ebean.setEmail("clevenger");
              ebean.setHiredate("12-Dec-2005");
              ebean.setPhone("1234-567-89");
              ebean.setSalary("8000");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("Muench");
               ebean.setEmail("smuench");
               ebean.setHiredate("12-Jan-1999");
               ebean.setPhone("1234-567-89");               
               ebean.setSalary("11000");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("CorporateTax")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Gordon");
            ebean.setEmail("rgordon");
            ebean.setHiredate("12-Jan-2000");
            ebean.setPhone("1234-567-89");            
            ebean.setSalary("10000");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("Chu");
             ebean.setEmail("kchu");
             ebean.setHiredate("10-May-2001");
             ebean.setPhone("1234-567-89");
             ebean.setSalary("12000");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("Gamer");
              ebean.setEmail("pgamer");
              ebean.setHiredate("12-Dec-2005");
              ebean.setPhone("1234-567-89");              
              ebean.setSalary("8000");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("williams");
               ebean.setEmail("gwilliams");
               ebean.setHiredate("12-Jan-1999");
               ebean.setPhone("1234-567-89");
               ebean.setSalary("11000");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Manufacturing")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Mills");
            ebean.setEmail("drmills");
            ebean.setHiredate("12-Jan-2001");
            ebean.setPhone("1234-567-89");
            ebean.setSalary("10000");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("Kodali");
             ebean.setEmail("rkodali");
             ebean.setHiredate("10-May-2001");
             ebean.setPhone("1234-567-89");
             ebean.setSalary("12000");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("schalk");
              ebean.setEmail("cschalk");
              ebean.setHiredate("12-Dec-2005");
              ebean.setPhone("1234-567-89");
              ebean.setSalary("8000");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("Munsinger");
               ebean.setEmail("lmunsinger");
               ebean.setHiredate("12-Jan-1999");
               ebean.setPhone("1234-567-89");
               ebean.setSalary("11000");
               rows.add(ebean);
               /* ----------------- */
            this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Construction")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Dyole");
            ebean.setEmail("sdoyle");
            ebean.setHiredate("12-Jan-1976");
            ebean.setPhone("1234-567-89");
            ebean.setSalary("100000");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("Garder");
             ebean.setEmail("sgarder");
             ebean.setHiredate("10-Aug-1987");
             ebean.setPhone("1234-567-89");
             ebean.setSalary("52000");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("miller");
              ebean.setEmail("smiller");
              ebean.setHiredate("12-Jan-1999");
              ebean.setPhone("1234-567-89");
              ebean.setSalary("300000");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("donell");
               ebean.setEmail("fdonell");
               ebean.setHiredate("12-Jan-1990");
               ebean.setPhone("1234-567-89");
               ebean.setSalary("25000");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Support")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("liebrand");
            ebean.setEmail("bliebrand");
            ebean.setHiredate("12-Jan-2001");
            ebean.setPhone("1234-567-89");
            ebean.setSalary("10000");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("bush");
             ebean.setEmail("gbush");
             ebean.setHiredate("10-May-2001");
             ebean.setPhone("1234-567-89");
             ebean.setSalary("12000");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("kennedy");
              ebean.setEmail("fkennedy");
              ebean.setHiredate("12-Dec-2005");
              ebean.setPhone("1234-567-89");
              ebean.setSalary("8000");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("bill");
               ebean.setEmail("mbill");
               ebean.setHiredate("12-Jan-1999");
               ebean.setPhone("1234-567-89");
               ebean.setSalary("11000");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("NOC")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Jean");
            ebean.setEmail("sjean");
            ebean.setHiredate("12-Jan-2001");
            ebean.setPhone("1234-567-89");
            ebean.setSalary("10000");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("borne");
             ebean.setEmail("aborne");
             ebean.setHiredate("10-May-2001");
             ebean.setPhone("1234-567-89");
             ebean.setSalary("12000");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("Smith");
              ebean.setEmail("ssmith");
              ebean.setHiredate("12-Dec-2005");
              ebean.setPhone("1234-567-89");
              ebean.setSalary("8000");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("didier");
               ebean.setEmail("sdidier");
               ebean.setHiredate("12-Jan-1999");
               ebean.setSalary("11000");
               ebean.setPhone("1234-567-89");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Helpdesk")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Richards");
            ebean.setEmail("drichards");
            ebean.setHiredate("12-Jan-2001");
            ebean.setPhone("1234-567-89");
            ebean.setSalary("10000");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("joplin");
             ebean.setEmail("sjoplin");
             ebean.setHiredate("10-May-2001");
             ebean.setSalary("12000");
             ebean.setPhone("1234-567-89");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("Moore");
              ebean.setEmail("mmoore");
              ebean.setHiredate("12-Dec-2005");
              ebean.setSalary("8000");
              ebean.setPhone("1234-567-89");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("jorg");
               ebean.setEmail("yjorg");
               ebean.setHiredate("12-Jan-1999");
               ebean.setSalary("11000");
               ebean.setPhone("1234-567-89");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Sales")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Waldman");
            ebean.setEmail("jwaldman");
            ebean.setHiredate("12-Jan-1976");
            ebean.setSalary("100000");
            ebean.setPhone("1234-567-89");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("Schartz");
             ebean.setEmail("aschwarts");
             ebean.setHiredate("10-Aug-1987");
             ebean.setSalary("52000");
             ebean.setPhone("1234-567-89");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("Winer");
              ebean.setEmail("awiner");
              ebean.setHiredate("12-Jan-1999");
              ebean.setSalary("300000");
              ebean.setPhone("1234-567-89");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("Brown");
               ebean.setEmail("bbrown");
               ebean.setHiredate("12-Jan-1990");
               ebean.setSalary("25000");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Retail")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Johnson");
            ebean.setEmail("rjohnson");
            ebean.setHiredate("12-Jan-2001");
            ebean.setSalary("10000");
            ebean.setPhone("1234-567-89");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("king");
             ebean.setEmail("bjking");
             ebean.setHiredate("10-May-2001");
             ebean.setSalary("12000");
             ebean.setPhone("1234-567-89");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("jackson");
              ebean.setEmail("mjackson");
              ebean.setHiredate("12-Dec-2005");
              ebean.setSalary("8000");
              ebean.setPhone("1234-567-89");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("summerville");
               ebean.setEmail("jsummerville");
               ebean.setHiredate("12-Jan-1999");
               ebean.setSalary("11000");
               ebean.setPhone("1234-567-89");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Contracting")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Gordon");
            ebean.setEmail("fgordon");
            ebean.setHiredate("12-Jan-2000");
            ebean.setSalary("10000");
            ebean.setPhone("1234-567-89");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("Lankenau");
             ebean.setEmail("blankenau");
             ebean.setHiredate("10-May-2001");
             ebean.setSalary("12000");
             ebean.setPhone("1234-567-89");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("Mueller");
              ebean.setEmail("gmueller");
              ebean.setHiredate("12-Dec-2005");
              ebean.setSalary("8000");
              ebean.setPhone("1234-567-89");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("williams");
               ebean.setEmail("awilliams");
               ebean.setHiredate("12-Jan-1999");
               ebean.setSalary("11000");
               ebean.setPhone("1234-567-89");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Manufacturing")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Mills");
            ebean.setEmail("amills");
            ebean.setHiredate("12-Jan-2001");
            ebean.setSalary("10000");
            ebean.setPhone("1234-567-89");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("Roberta");
             ebean.setEmail("aroberta");
             ebean.setHiredate("10-May-2001");
             ebean.setSalary("12000");
             ebean.setPhone("1234-567-89");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("chan");
              ebean.setEmail("dchan");
              ebean.setHiredate("12-Dec-2005");
              ebean.setSalary("8000");
              ebean.setPhone("1234-567-89");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("quan");
               ebean.setEmail("iquan");
               ebean.setHiredate("12-Jan-1999");
               ebean.setSalary("11000");
               ebean.setPhone("1234-567-89");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Operations")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("Dulles");
            ebean.setEmail("dulles");
            ebean.setHiredate("12-Jan-1976");
            ebean.setSalary("100000");
            ebean.setPhone("1234-567-89");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("Kerry");
             ebean.setEmail("akerry");
             ebean.setHiredate("10-Aug-1987");
             ebean.setSalary("52000");
             ebean.setPhone("1234-567-89");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("Heintz");
              ebean.setEmail("bheintz");
              ebean.setHiredate("12-Jan-1999");
              ebean.setSalary("300000");
              ebean.setPhone("1234-567-89");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("boll");
               ebean.setEmail("tboll");
               ebean.setHiredate("12-Jan-1990");
               ebean.setSalary("25000");
               ebean.setPhone("1234-567-89");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
        
        if (department.equalsIgnoreCase("Payroll")){
            EmployeeBean ebean = new EmployeeBean();
            ebean.setEname("elses");
            ebean.setEmail("belses");
            ebean.setHiredate("12-Jan-2001");
            ebean.setSalary("10000");
            ebean.setPhone("1234-567-89");
            rows.add(ebean);
            /* ----------------- */
            
             ebean = new EmployeeBean();
             ebean.setEname("king");
             ebean.setEmail("sking");
             ebean.setHiredate("10-May-2001");
             ebean.setSalary("12000");
             ebean.setPhone("1234-567-89");
             rows.add(ebean);
             /* ----------------- */
             
              ebean = new EmployeeBean();
              ebean.setEname("do");
              ebean.setEmail("cdo");
              ebean.setHiredate("12-Dec-2005");
              ebean.setSalary("8000");
              ebean.setPhone("1234-567-89");
              rows.add(ebean);
              /* ----------------- */
              
               ebean = new EmployeeBean();
               ebean.setEname("miller");
               ebean.setEmail("kmiller");
               ebean.setHiredate("12-Jan-1999");
               ebean.setSalary("11000");
               ebean.setPhone("1234-567-89");
               rows.add(ebean);
               /* ----------------- */
                this.updateTable(rows);
            return;
        }
    }

    public void treenodeSelected(ActionEvent actionEvent) {
        
        this.selectedNode = ((GenericTreeNode)treemodel.getRowData());
        //set the table model
        this.buildDetail(this.selectedNode.getDescription());
    }

    private void initiateTable() {
    
        rows = new ArrayList<EmployeeBean>();
        EmployeeBean ebean = new EmployeeBean();
        rows.add(ebean);
           
        tableModel = new SortableModel(rows);
        FacesContext ctx = FacesContext.getCurrentInstance();
        detailTable = (CoreTable)ctx.getApplication().createComponent(CoreTable.COMPONENT_TYPE);  

        
    }
    
    private void updateTable(Collection<EmployeeBean> rows){
        tableModel = new SortableModel(rows);
        FacesContext ctx = FacesContext.getCurrentInstance();
        detailTable = (CoreTable)ctx.getApplication().createComponent(CoreTable.COMPONENT_TYPE);  
    }

    public void setDetailTable(CoreTable detailTable) {
        this.detailTable = detailTable;
    }

    public CoreTable getDetailTable() {
        return detailTable;
    }
}
