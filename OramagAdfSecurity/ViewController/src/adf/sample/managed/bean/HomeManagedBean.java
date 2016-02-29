package adf.sample.managed.bean;

import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.jbo.Key;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;
import oracle.jbo.uicli.binding.JUCtrlHierTypeBinding;
import oracle.jbo.uicli.binding.JUIteratorBinding;

import org.apache.myfaces.trinidad.component.UIXSwitcher;
import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;
import org.apache.myfaces.trinidad.model.RowKeySet;


public class HomeManagedBean {
    private RichTree locationsTree;
    private UIXSwitcher formSwitcher;
    private RichPanelGroupLayout formPanelGroup;
    private RichPopup popupP1;

    public HomeManagedBean() {
    }

    public void setLocationsTree(RichTree locationsTree) {
        this.locationsTree = locationsTree;
    }

    public RichTree getLocationsTree() {
        return locationsTree;
    }
    
  /**
   * Custom managed bean method that takes a SelectEvent input argument to generically
   * set the current row corresponding to the selected row in the tree. Note that this
   * method is a way to replace the "makeCurrent" EL expression (#{bindings.<tree binding>.
   * treeModel.makeCurrent}that Oracle JDeveloper adds to the tree component SelectionListener
   * property when dragging a collection from the Data Controls panel. Using this custom
   * selection listener allows developers to add pre- and post processing instructions. For
   * example, you may want to enforce PPR on a specific item after a new tree node has been
   * selected. This methods performs the following steps
   *
   * i.   get access to the tree component
   * ii.  get access to the ADF tree binding
   * iii. set the current row on the ADF binding
   * iv.  get the information about target iterators to synchronize
   * v.   synchronize target iterator
   *
   * @param selectionEvent object passed in by ADF Faces when configuring this method to
   * become the selection listener
   *
   * @author Frank Nimphius
  */
  public void onTreeSelect(SelectionEvent selectionEvent) {
    
    
    /* REPLACES */
    //#{bindings.allLocations.treeModel.makeCurrent} 
         
   /* custom pre processing goes here */
   /* --- */
     
  //get the tree information from the event object
  RichTree tree1 = (RichTree) selectionEvent.getSource();
  //in a single selection case ( a setting on the tree component ) the added set only
  //has a single entry. If there are more then using this method may not be desirable.
  //Implicitly we turn the multi select in a single select later, ignoring all set
  //entries than the first
  RowKeySet rks2 = selectionEvent.getAddedSet();
  //iterate over the contained keys. Though for a single selection use case we only expect
  //one entry in here
  Iterator rksIterator = rks2.iterator();
     
  //support single row selection case
  if (rksIterator.hasNext()){
    //get the tree node key, which is a List of path entries describing the
    //location of the node in the tree including its parents nodes
    List key = (List)rksIterator.next();
   //get the ADF tree  binding to work with
    JUCtrlHierBinding treeBinding = null;
    //The Trinidad CollectionModel is used to provide data to trees and tables. In the
    //ADF binding case, it contains the tree binding as wrapped data
    treeBinding = (JUCtrlHierBinding) ((CollectionModel)tree1.getValue()).getWrappedData();
    //find the node identified by the node path from the ADF binding layer. Note that
    //we don't need to know about the name of the tree binding in the PageDef file because
    //all information is provided
    JUCtrlHierNodeBinding nodeBinding = nodeBinding = treeBinding.findNodeByKeyPath(key);
    //the current row is set on the iterator binding. Because all bindings have an internal
    //reference to their iterator usage, the iterator can be queried from the ADF binding
    //object
    DCIteratorBinding _treeIteratorBinding = null;
    _treeIteratorBinding = treeBinding.getDCIteratorBinding();
    Key rowKey = nodeBinding.getRowKey();
    JUIteratorBinding iterator = nodeBinding.getIteratorBinding();
    iterator.setCurrentRowWithKey(rowKey.toStringFormat(true));
    //get selected node type information
    JUCtrlHierTypeBinding typeBinding =  nodeBinding.getHierTypeBinding();
                 
    // The tree node rule may have a target iterator defined. Target iterators are
    // configured using the Target Data Source entry in the tree node edit dialog
    // and allow developers to declaratively synchronize an independent iterator
    // binding with the node selection in the tree.
    //
    String targetIteratorSpelString = typeBinding.getTargetIterator();      
         
    //chances are that the target iterator option is not configured. We avoid
    //NPE by checking this condition
                     
    if (targetIteratorSpelString != null && !targetIteratorSpelString.isEmpty()) {
              
      //resolve SPEL string for target iterator
      DCIteratorBinding targetIterator = resolveTargetIterWithSpel(targetIteratorSpelString);
      //synchronize the row in the target iterator
      targetIterator.setCurrentRowWithKey(rowKey.toStringFormat(true));
    }
    
    /********************* DISPLAY INPUT FORM FOR SELECTED NODE **********************/
    
    //get the name of the selectected tree node object. In this sample the value is 
    //adf.sample.model.DepartmentsView,adf.sample.model.EmployeesView or 
    //adf.sample.model.LocationsView
    
    String selectedNodeObjectRef = typeBinding.getStructureDefName();
    //write selected node object reference to session
    
    AdfFacesContext adfFacesCtx = AdfFacesContext.getCurrentInstance();
    Map viewScope = adfFacesCtx.getViewScope();
    viewScope.put("nodeRef",selectedNodeObjectRef);
    
    //refresh form display
    adfFacesCtx.addPartialTarget(this.getFormPanelGroup());
   }       
  }

  /**
   * Helper method to resolve EL expression into DCIteratorBinding instance
   * @param spelExpr the SPEL expression starting with ${...}
   * @return DCIteratorBinding instance
  */
  private DCIteratorBinding resolveTargetIterWithSpel(String spelExpr){
   FacesContext fctx = FacesContext.getCurrentInstance();
   ELContext elctx = fctx.getELContext();
   ExpressionFactory elFactory = fctx.getApplication().getExpressionFactory();
    
   ValueExpression valueExpr = elFactory.createValueExpression(elctx, spelExpr,Object.class);
   DCIteratorBinding dciter = (DCIteratorBinding) valueExpr.getValue(elctx);   
   return dciter;
  }

    public void setFormPanelGroup(RichPanelGroupLayout formPanelGroup) {
        this.formPanelGroup = formPanelGroup;
    }

    public RichPanelGroupLayout getFormPanelGroup() {
        return formPanelGroup;
    }

    //based on the current state of the login link, 
    //log user in or out
    public void onLoginLogout(ActionEvent actionEvent) {
        RichCommandImageLink rcil = (RichCommandImageLink) actionEvent.getComponent();
        String commandLinkIcon = rcil.getIcon();
        if (commandLinkIcon.indexOf("glbl_login_msg.gif") >0){
          //login
          RichPopup.PopupHints hints = new RichPopup.PopupHints();
          popupP1.show(hints);
        }
        else{
          //logout         
          FacesContext fctx = FacesContext.getCurrentInstance();
          ExternalContext ectx = fctx.getExternalContext();
            try {
                ectx.redirect("/adfAuthentication?logout=true&end_url=/faces/Home.jspx");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }        
    }

    public void setPopupP1(RichPopup popupP1) {
        this.popupP1 = popupP1;
    }

    public RichPopup getPopupP1() {
        return popupP1;
    }
}
