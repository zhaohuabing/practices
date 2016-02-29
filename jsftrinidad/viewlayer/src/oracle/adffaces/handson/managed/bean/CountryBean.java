package oracle.adffaces.handson.managed.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class CountryBean {

    private String name;
    private Map<String,StateBean> states;
    
    public CountryBean() {
        states = new TreeMap<String,StateBean>();
        createUSStates();
    }

    public void setName(String countryName) {
        this.name = countryName;
    }

    public String getName() {
        return name;
    }

    public List<StateBean> getStates(){
        Collection<StateBean> c = states.values();
        return new ArrayList(c);
    } 

    public Map<String,String> getStatesValueMap(){
        Map<String,String> map = new TreeMap<String,String>();
        for( StateBean state:states.values() ){
            String name = state.getName();
            String[] parts = name.split("\\.");
            map.put( parts[0] + " " + ((parts.length > 1)?parts[1]:""), name );
        }
        return map;
    }

    public Map<String, String> lookupCitiesValueMap( String stateName ){
        Map<String,String> map = new TreeMap<String,String>();
        if( stateName != null && !stateName.equalsIgnoreCase("")){
            StateBean state = states.get( stateName );
            List<String> cities = state.getCities();
            for( String city:cities ){
                map.put( city, city );
            }
        }
        return map;        
    }

    private void createUSStates(){
        ResourceBundle rb = ResourceBundle.getBundle("oracle.adffaces.handson.resources.cities");
        for(Enumeration e = rb.getKeys(); e.hasMoreElements();){
            String name = (String)e.nextElement();
            StateBean state = new StateBean();
            state.setName( name );
            String cities = rb.getString( name );
            state.setCities( parseCities( cities ) );
            states.put( state.getName(), state );
        }
    }
    
    private List parseCities( String citiesList ){
        List<String> listOfCities = new ArrayList<String>();
        String[] cities = citiesList.split(",");
        for( String city:cities ){
            listOfCities.add(city.replaceAll("\"", ""));
        }
        return Collections.unmodifiableList( listOfCities );
    }

    private void printCityAndState(){
        for( StateBean state:states.values() ){
            System.out.println( state.getName() );
            List<String> cities = state.getCities();
            for( String city:cities ){
                System.out.println("\t" + city);
            }
        }
    }

    public static void main(String[] args){
        CountryBean bean = new CountryBean();
        bean.createUSStates();
        bean.printCityAndState();
    }
    
    /* *************************************************************************
     *                              ADD CUSTOM CODE
     * *************************************************************************/
     
     
    
}
