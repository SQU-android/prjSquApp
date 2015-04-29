/**
 * 
 */
package om.squ.edu.app.android.payment.bo;

/**
 * @author Bhabesh
 *
 */
public class Earn 
{
	public Earn(){}
	public Earn (String earnDescription, float earnRate)
	{
		this.earnDescription = earnDescription;
		this.earnRate = earnRate;
	}
	
	private String 	earnDescription;
	private	float	earnRate;
	/**
	 * @return the earnDescription
	 */
	public String getEarnDescription() {
		return earnDescription;
	}
	/**
	 * @param earnDescription the earnDescription to set
	 */
	public void setEarnDescription(String earnDescription) {
		this.earnDescription = earnDescription;
	}
	/**
	 * @return the earnRate
	 */
	public float getEarnRate() {
		return earnRate;
	}
	/**
	 * @param earnRate the earnRate to set
	 */
	public void setEarnRate(float earnRate) {
		this.earnRate = earnRate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Earn [earnDescription=" + earnDescription + ", earnRate="
				+ earnRate + "]";
	}
	
	
}
