/**
 * 
 */
package om.squ.edu.app.android.leave.leavebal;



import om.squ.edu.app.android.R;
import om.squ.edu.app.android.leave.leavebal.rest.LeaveBalanceRestService;
import om.squ.edu.app.android.util.ServiceUtil;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Bhabesh
 *
 */
public class LeaveBalanceService extends Activity
{
	private	Resources			resources;
	private	String				mode;
	private	ServiceUtil			serviceUtil;
	private	SharedPreferences	prefUser;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_leavebal);
		TextView tvLeaveBal	=	(TextView)findViewById(R.id.txtLeaveBal);
		
		resources				=	getResources();
		mode 					= 	resources.getString(R.string.mode_deploy);
		serviceUtil				=	new ServiceUtil(resources, mode);

		int PRIVATE_MODE 		= 	0;
		
		String 	PREFER_NAME 	= 	resources.getString(R.string.prefer_name);
		
		prefUser = getApplicationContext().getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
		String userId = prefUser.getString("userId", null);
		
		String urlLeaveBal 		= 	serviceUtil.getUrlLeaveBal()+"/"+userId;
		
		new LeaveBalanceRestService(tvLeaveBal).execute(urlLeaveBal);
		
		
	}
}
