package com.oracle.zhaohb.model.service;

import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jul 08 16:36:41 CST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class HRServiceAppModuleImpl extends ApplicationModuleImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public HRServiceAppModuleImpl() {
    }

    /**
     * Container's getter for DepartmentVO1.
     * @return DepartmentVO1
     */
    public ViewObjectImpl getDepartmentVO1() {
        return (ViewObjectImpl) findViewObject("DepartmentVO1");
    }
    
    public void testBusinessLogic() {
        
    }
}
