package oracle.adffaces.handson.managed.bean;
/**
 * Bean representing US states
 */
import java.util.List;

public class StateBean {

    private String name;
    private List<String> cities;

    public StateBean() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<String> getCities() {
        return cities;
    }

    public String toString(){
        return name;
    }
}
