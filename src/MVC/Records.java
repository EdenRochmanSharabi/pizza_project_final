package MVC;

import java.util.HashMap;

public class Records {
    public HashMap<Integer, Object> record;
    public int size;

    public Records(){
        record = new HashMap<>();
        size = 0;
    }
    public void setRecord(Integer column, Object value){
        record.put(column, value);
        size++;
    }
    public Object getRecord(Integer column){
        return record.get(column);
    }
    public void deleteRecord(Integer column){
        record.remove(column);
        size--;
    }

    @Override
    public String toString() {
        return "Records{" +
                "record=" + record +
                ", size=" + size +
                '}';
    }
}
