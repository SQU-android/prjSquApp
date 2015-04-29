/**
 * 
 */
package om.squ.edu.app.android.payment.bo;

import java.util.List;

/**
 * @author Bhabesh
 *
 */
public class PaymentEarn 
{
	private List<Earn> 	earns;
	private float 		grossSal;
	private float 		totalDeductSal;
	private	float		netSal;
	private String		lastPromotionDt;
	/**
	 * @return the earns
	 */
	public List<Earn> getEarns() {
		return earns;
	}
	/**
	 * @param earns the earns to set
	 */
	public void setEarns(List<Earn> earns) {
		this.earns = earns;
	}
	/**
	 * @return the grossSal
	 */
	public float getGrossSal() {
		return grossSal;
	}
	/**
	 * @param grossSal the grossSal to set
	 */
	public void setGrossSal(float grossSal) {
		this.grossSal = grossSal;
	}
	/**
	 * @return the totalDeductSal
	 */
	public float getTotalDeductSal() {
		return totalDeductSal;
	}
	/**
	 * @param totalDeductSal the totalDeductSal to set
	 */
	public void setTotalDeductSal(float totalDeductSal) {
		this.totalDeductSal = totalDeductSal;
	}
	/**
	 * @return the netSal
	 */
	public float getNetSal() {
		return netSal;
	}
	/**
	 * @param netSal the netSal to set
	 */
	public void setNetSal(float netSal) {
		this.netSal = netSal;
	}
	/**
	 * @return the lastPromotionDt
	 */
	public String getLastPromotionDt() {
		return lastPromotionDt;
	}
	/**
	 * @param lastPromotionDt the lastPromotionDt to set
	 */
	public void setLastPromotionDt(String lastPromotionDt) {
		this.lastPromotionDt = lastPromotionDt;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentEarn [earns=" + earns + ", grossSal=" + grossSal
				+ ", totalDeductSal=" + totalDeductSal + ", netSal=" + netSal
				+ ", lastPromotionDt=" + lastPromotionDt + "]";
	}
	
	
	
	
	
}
