/**
 * 
 */
package w.fujiko.dto.customers.departments.users;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import w.fujiko.dto.users.UserDefaultDTO;

/**
 * 
 * @author yagami
 *
 */
public class CustomerDepartmentUserDefaultDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;
    
    @JsonProperty("id")
    private int id;
    
    @JsonProperty("user")
	private UserDefaultDTO user;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
    }

    public UserDefaultDTO getUser() {
		return user;
	}

	public void setUser(UserDefaultDTO user) {
		this.user = user;
    }

}