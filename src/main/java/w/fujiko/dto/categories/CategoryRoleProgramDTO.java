package w.fujiko.dto.categories;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import w.fujiko.dto.programs.ProgramUserPermissionDTO;

@Getter
@Setter
public class CategoryRoleProgramDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("category")
    private String categoryName;

    private List<ProgramUserPermissionDTO> programs;

    public String getCategoryName(){
        return this.categoryName;
    }
    public String setCategoryName(String category_name){
        return this.categoryName=category_name;
    }
    public List<ProgramUserPermissionDTO> getPrograms(){
        return this.programs;
    }
    public List<ProgramUserPermissionDTO> setPrograms(List<ProgramUserPermissionDTO> programs){
        return this.programs=programs;
    }
}