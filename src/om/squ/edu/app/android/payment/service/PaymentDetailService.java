/**
 * 
 */
package om.squ.edu.app.android.payment.service;

import om.squ.edu.app.android.R;
import om.squ.edu.app.android.payment.service.rest.PaymentCurrentYearRestService;
import om.squ.edu.app.android.payment.service.rest.PaymentCurrentYearRestService.TaskGetCurrentYear;
import om.squ.edu.app.android.payment.service.rest.PaymentDetailRestService;
import om.squ.edu.app.android.util.ServiceUtil;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayout;
import android.view.Menu;
import android.widget.TextView;

/**
 * @author Bhabesh
 *
 */
public class PaymentDetailService extends ActionBarActivity implements TaskGetCurrentYear<String>
{
	private String strCurrentYear = "na";
	protected Handler asyncTaskHandler;
	private	Resources			resources;
	private	String				mode;
	private	ServiceUtil			serviceUtil;
	private	SharedPreferences	prefUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			
			asyncTaskHandler = new Handler();
			
			setContentView(R.layout.layout_payment_detail);
			
		
			String currYear = getResources().getString(R.string.service_payment_currYear);
			
			TextView tv	=	(TextView)findViewById(R.id.textPaymentYear);
								resources				=	getResources();
								mode 					= 	resources.getString(R.string.mode_deploy);
								serviceUtil				=	new ServiceUtil(resources, mode);
			String				urlPaymentCurrYearRest	=	serviceUtil.getUrlPayment()+"/" +currYear;
			
			new PaymentCurrentYearRestService(PaymentDetailService.this, this, resources.getString(R.string.payment_year), tv).execute(urlPaymentCurrYearRest);
			
			
			
			
			
		}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		return false;
	}
	




	@Override
	public void getCurrentYearRest(String paramYear) {
		
		int PRIVATE_MODE = 0;
		this.strCurrentYear 		=	paramYear;
		String 	PREFER_NAME 		= 	resources.getString(R.string.prefer_name);
		
		prefUser = getApplicationContext().getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
		String userId = prefUser.getString("userId", null);
		
		String	urlPaymentDetail	=	serviceUtil.getUrlPayment()+"/"+userId+"/"+strCurrentYear;
		GridLayout	gridLayout		=	(GridLayout)findViewById(R.id.gridPayment_detail);
		
		
		new PaymentDetailRestService(gridLayout, PaymentDetailService.this,resources).execute(urlPaymentDetail);

	
	}





	
}
