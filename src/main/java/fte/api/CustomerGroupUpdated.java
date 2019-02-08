/**
 * 
 */
package fte.api;

import java.util.Date;
/**
 * @author Yagami
 *
 */
public class CustomerGroupUpdated extends Success {
    private Date score1Phase;
    private Date score2Phase;
    private Date score3Phase;

    public CustomerGroupUpdated(Date score1Phase,Date score2Phase,Date score3Phase){
        super(true);
        this.setScore1Phase(score1Phase);
        this.setScore2Phase(score2Phase);
        this.setScore3Phase(score3Phase);
    }

    public Date getScore1Phase() {
		return this.score1Phase;
	}

	public void setScore1Phase(Date score1Phase) {
        this.score1Phase=score1Phase;
    }

    public Date getScore2Phase() {
		return this.score2Phase;
	}

	public void setScore2Phase(Date score2Phase) {
        this.score2Phase=score2Phase;
    }

    public Date getScore3Phase() {
		return this.score3Phase;
	}

	public void setScore3Phase(Date score3Phase) {
        this.score3Phase=score3Phase;
    }
}
