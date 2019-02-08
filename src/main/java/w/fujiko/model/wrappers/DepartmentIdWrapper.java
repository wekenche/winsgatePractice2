package w.fujiko.model.wrappers;

import java.util.List;

public class DepartmentIdWrapper{

    private List<Integer> departmentIds;

    public DepartmentIdWrapper(){

    }
    
    public List<Integer> getDepartmentIds(){
        return this.departmentIds;
    }
    public void setDepartmentIds(List<Integer> departmentIds){
        this.departmentIds = departmentIds;
    }
}
