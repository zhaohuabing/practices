package security;

import oracle.adf.share.security.authorization.ADFPermission;

/**
 * Permission for all UI Components
 */
public class UIPermission extends ADFPermission {
    @SuppressWarnings("compatibility:-3777225856662933844")
    private static final long serialVersionUID = -1154047629682485537L;

    public UIPermission(java.lang.String name, java.lang.String actions) 
    {
        super(name, actions);
    }    
}
