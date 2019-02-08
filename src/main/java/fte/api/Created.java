/**
 * 
 */
package fte.api;

/**
 * @author Try-Parser
 *
 */
public class Created extends Success {
    private String link="";
    
    public Created(String link){
        super(true);
        this.setLink(link);
    }

    public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
        this.link=link;
    }
}
