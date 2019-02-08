/**
 * 
 */
package fte.api;

import java.util.List;

/**
 * @author Try-Parser
 *
 */
public class Page <T> {
	private Integer first;
	private Integer Max;
	private Long total;
	private List<T> results;
	private Boolean success;
	
	public Integer getFirst() {
		return first;
	}
	public void setFirst(Integer first) {
		this.first = first;
	}
	public Integer getMax() {
		return Max;
	}
	public void setMax(Integer max) {
		Max = max;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getResults() {
		return results;
	}
	public void setResults(List<T> list) {
		this.results = list;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
