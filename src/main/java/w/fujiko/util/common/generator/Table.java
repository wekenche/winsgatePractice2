package w.fujiko.util.common.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is meant to be used as a data
 * or content when generating a CSV or PDF.
 * 
 * @author festadillo
 *
 * @param <E>
 */
public class Table<E> {

	/**
	 * REQUIRED
	 * 
	 * Name of the headers to be shown to table
	 */
	private String[] headers;
	
	
	/**
	 * REQUIRED
	 * 
	 * Name of the fields to be extracted from the dataSrouce.
	 * Fields that are specified in the array must be present in the model.
	 * 
	 * Example:
	 * 
	 * This declaration of fields:
	 * String[] fieldList = {"username", "password"};
	 * 
	 * Must have corresponding fields in the model.
	 * 
	 * Example Model:
	 * 
	 * 	public class Person {
	 * 		private Integer id;
	 * 		private String name;
	 * 		private String username;
	 * 		private String password;
	 * 		// getters and setters here
	 *  }
	 * 
	 * 
	 */
	private String[] fields;
	
	
	/**
	 *  OPTIONAL
	 *  
	 *  This property defines the default values of the specified fields.
	 *  
	 *  Example: 
	 *  
	 *  Table<Person> table = new Table<>();
	 *  String[] headerList = {"Type", "ID", "Name", "Username", "Salary"};
	 *	String[] fieldList = {"type", "id", "name", "username", "salary"};
	 *  
	 *  // key = target field in the fieldList
	 *  // value = default value assigned to target field
	 *  
	 *  table.addFieldDefaultValue("type", "Employee");
	 *  table.addFieldDefaultValue("salary", "Secret");
	 *  
	 *  In the fieldList array, you may include field names that is not present
	 *  in the model as long as you assign a default value like in the example above.
	 *  The field salary and type does not exist in the Person model.
	 *  
	 */
	private Map<String, String> defaultFieldValues;
	
	
	/**
	 *  OPTIONAL 
	 * 
	 *  Data source is the list of models to be extracted to table.
	 */
	private List<E> dataSource;
	
	
	/**
	 * klazz is the class type of the model.
	 */
	private Class<E> klazz;
	
	
	public Table() {
		defaultFieldValues = new HashMap<String, String>();
	}
	
	
	public void addFieldDefaultValue(String field, String defaultValue) {
		defaultFieldValues.put(field, defaultValue);
	}


	public String[] getHeaders() {
		return headers;
	}


	public void setHeaders(String[] headers) {
		this.headers = headers;
	}


	public String[] getFields() {
		return fields;
	}


	public void setFields(String[] fields) {
		this.fields = fields;
	}


	public Map<String, String> getDefaultFieldValues() {
		return defaultFieldValues;
	}


	public void setDefaultFieldValues(Map<String, String> defaultFieldValues) {
		this.defaultFieldValues = defaultFieldValues;
	}


	public List<E> getDataSource() {
		return dataSource;
	}


	public void setDataSource(List<E> dataSource) {
		this.dataSource = dataSource;
	}


	public Class<E> getKlazz() {
		return klazz;
	}


	public void setKlazz(Class<E> klazz) {
		this.klazz = klazz;
	}
	

}