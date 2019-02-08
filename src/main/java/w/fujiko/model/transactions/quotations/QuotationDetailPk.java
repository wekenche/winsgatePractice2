/**
 * 
 */
package w.fujiko.model.transactions.quotations;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.persistence.Embeddable;


/**
 * @author yagami
 *
 */
@Embeddable
public class QuotationDetailPk implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257921460512063270L;

	@Column(name="quotation_slip_id",columnDefinition="int",nullable=false)
	@NotNull
	private Integer quotationHeaderId;

	@Column(name="task_id",columnDefinition="smallint",nullable=false)
	@NotNull
	private short taskId;

	public Integer getQuotationHeaderId() {
		return quotationHeaderId;
	}

	public void setQuotationHeaderId(Integer quotationHeaderId) {
		this.quotationHeaderId = quotationHeaderId;
	}

	public short getTaskId() {
		return taskId;
	}

	public void setTaskId(short taskId) {
		this.taskId = taskId;
	}
}