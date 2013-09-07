/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package event;

import java.util.Hashtable;

/**
 *
 * @author TRITUEVIET
 */
/*
 * lớp truyền dữ liệu 
 *
 */

public class Event {
    
    
     public Hashtable data;
    
    public Event() {
        data = new Hashtable();
    }
    
    public void setData(String key, Object value) {
        data.put(key, value);
    }
    
    public Object getData(String key) {
        if(data.containsKey(key)) {
            return data.get(key);
        }
        return null;
    }
    
}
