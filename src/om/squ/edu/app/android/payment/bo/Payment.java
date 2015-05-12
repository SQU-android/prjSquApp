/**
 * 
 */
package om.squ.edu.app.android.payment.bo;

import java.util.List;

/**
 * @author Bhabesh
 *
 */
public class Payment 
{
	List<PaymentSummary>		lstPaymentSummary;
	List<Integer>				salYears;
	/**
	 * @return the lstPaymentSummary
	 */
	public List<PaymentSummary> getLstPaymentSummary() {
		return lstPaymentSummary;
	}
	/**
	 * @param lstPaymentSummary the lstPaymentSummary to set
	 */
	public void setLstPaymentSummary(List<PaymentSummary> lstPaymentSummary) {
		this.lstPaymentSummary = lstPaymentSummary;
	}
	/**
	 * @return the salYears
	 */
	public List<Integer> getSalYears() {
		return salYears;
	}
	/**
	 * @param salYears the salYears to set
	 */
	public void setSalYears(List<Integer> salYears) {
		this.salYears = salYears;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Payment [lstPaymentSummary=" + lstPaymentSummary
				+ ", salYears=" + salYears + "]";
	}
	
	
}
