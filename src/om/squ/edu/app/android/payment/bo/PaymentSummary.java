/**
 * 
 */
package om.squ.edu.app.android.payment.bo;

import java.sql.Date;

/**
 * @author Bhabesh
 *
 */
public class PaymentSummary 
{
	private		Date	paymentSqlDt;
	private		String	paymentDt;
	private		float	payAmount;
	private		String	paymentType;
	private		String	paymentDesc;
	private		String	remark;
	
	
	
	/**
	 * @return the paymentSqlDt
	 */
	public Date getPaymentSqlDt() {
		return paymentSqlDt;
	}



	/**
	 * @param paymentSqlDt the paymentSqlDt to set
	 */
	public void setPaymentSqlDt(Date paymentSqlDt) {
		this.paymentSqlDt = paymentSqlDt;
	}



	/**
	 * @return the paymentDt
	 */
	public String getPaymentDt() {
		return paymentDt;
	}



	/**
	 * @param paymentDt the paymentDt to set
	 */
	public void setPaymentDt(String paymentDt) {
		this.paymentDt = paymentDt;
	}



	/**
	 * @return the payAmount
	 */
	public float getPayAmount() {
		return payAmount;
	}



	/**
	 * @param payAmount the payAmount to set
	 */
	public void setPayAmount(float payAmount) {
		this.payAmount = payAmount;
	}



	/**
	 * @return the paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}



	/**
	 * @param paymentType the paymentType to set
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}



	/**
	 * @return the paymentDesc
	 */
	public String getPaymentDesc() {
		return paymentDesc;
	}



	/**
	 * @param paymentDesc the paymentDesc to set
	 */
	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}



	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}



	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentSummary [paymentSqlDt=" + paymentSqlDt + ", paymentDt="
				+ paymentDt + ", payAmount=" + payAmount + ", paymentType="
				+ paymentType + ", paymentDesc=" + paymentDesc + ", remark="
				+ remark + "]";
	}
	
	
}
