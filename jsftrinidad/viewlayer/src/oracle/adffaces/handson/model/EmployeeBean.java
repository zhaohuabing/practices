package oracle.adffaces.handson.model;

/**
 * Customer.java represents a customer object 
 */
public class EmployeeBean {

    private String ename;
    private String hiredate;
    private String email;
    private String salary;
    private String phone;

    public EmployeeBean() {
    }

    public void setEname(String fullName) {
        this.ename = fullName;
    }

    public String getEname() {
        return ename;
    }

    public void setHiredate(String address) {
        this.hiredate = address;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSalary(String company) {
        this.salary = company;
    }

    public String getSalary() {
        return salary;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
