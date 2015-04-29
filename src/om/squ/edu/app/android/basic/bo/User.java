/**
 * 
 */
package om.squ.edu.app.android.basic.bo;

/**
 * @author Bhabesh
 *
 */
public class User 
{
	private String userId;
	private String userName;
	private String userType;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", userType=" + userType + "]";
	}
	
	
}
