package oracle.adffaces.handson.model;


import java.util.ArrayList;
import java.util.Collection;

public class GenericTreeNode {
    private String description;
    private String longDescription;
    private Collection children;
    private String nodeType;
    private boolean nodeSelected;


    public GenericTreeNode() {
        this.description = "root";
        this.nodeType = "root";
        this.children = new ArrayList();
    }

    public GenericTreeNode(String description,  String nodeType) {
        this.description = description;
        this.nodeType = nodeType;
        this.children = new ArrayList();
    }

    public GenericTreeNode(String description, String longDescription, 
                           Collection children) {
        this.description = description;
        this.longDescription = longDescription;
        this.children = children;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setChildren(Collection children) {
        this.children = children;
    }

    public Collection getChildren() {
        return children;
    }

    public int getChildCount() {
        if (children == null)
            return 0;
        else
            return children.size();
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeSelected(boolean nodeSelected) {
        this.nodeSelected = nodeSelected;
    }

    public boolean isNodeSelected() {
        return nodeSelected;
    }
}
