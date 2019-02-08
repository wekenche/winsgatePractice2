/**
 * 
 */
package w.fujiko.model.transactions.sales;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.persistence.Embeddable;


/**
 * @author yagami
 *
 */
@Embeddable
public class SalesDetailPk implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

	@Column(name="sales_slip_id",columnDefinition="int",nullable=false)
	@NotNull
	private Integer salesHeaderId;

	@Column(name="task_id",columnDefinition="smallint",nullable=false)
	@NotNull
	private short taskId;

	public Integer getSalesHeaderId() {
		return salesHeaderId;
	}

	public void setSalesHeaderId(Integer salesHeaderId) {
		this.salesHeaderId = salesHeaderId;
	}

	public short getTaskId() {
		return taskId;
	}

	public void setTaskId(short taskId) {
		this.taskId = taskId;
	}
}