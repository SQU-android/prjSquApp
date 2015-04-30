/**
 * 
 */
package om.squ.edu.app.android.payment;


import om.squ.edu.app.android.R;
import om.squ.edu.app.android.payment.bo.Earn;
import om.squ.edu.app.android.payment.bo.PaymentEarn;
import om.squ.edu.app.android.util.ServiceUtil;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayout;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * @author Bhabesh
 *
 */
public class PaymentService extends ActionBarActivity
{
	int PRIVATE_MODE = 0;
	private	SharedPreferences	prefUser;
	private static final String PREFER_NAME 	= "squAppEmpPrefer";
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_payment);
			
			
			
			prefUser = getApplicationContext().getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
			String userId = prefUser.getString("userId", null);
			
			//Log.e("User PaymentService url ", urlPayment);
			
			new Payment().execute(userId);
			
		}
		
	
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_payment, menu);
		return true;
	}
		
		
	
	
	private class Payment extends AsyncTask<String, Void, PaymentEarn>
	{
		@Override
		protected PaymentEarn doInBackground(String... params) {
			Resources			resources	=	getResources();
			String				mode 		= 	resources.getString(R.string.mode_deploy);
			ServiceUtil			serviceUtil	=	new ServiceUtil(resources, mode);
						
			String 			userId 		=	params[0];
			
			//String urlPayment01 	= "http://172.20.10.58:8080/prjPaymentPortlet/payment";
			String urlPayment		=  serviceUtil.getUrlPayment()+"/" + userId;

			
			RestTemplate 	restTemplate	=	new RestTemplate();
			
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			PaymentEarn	paymentEarn = restTemplate.getForObject(urlPayment,PaymentEarn.class);
			
			
			return paymentEarn;
		}
		
		   @Override
		    protected void onPostExecute(PaymentEarn paymentEarn) {
			   GridLayout gridLayout;
			   gridLayout = (GridLayout)findViewById(R.id.gridPayment);
			   
			   
			   //gridLayout = new GridLayout(getApplicationContext());
			   //gridLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			   //gridLayout.setOrientation(0);
			   gridLayout.setColumnCount(2);
			   gridLayout.setRowCount(paymentEarn.getEarns().size());
			   
			   for(Earn earn : paymentEarn.getEarns())
			   {
				   
				   
				   TextView tvEarnDesc 				= new TextView(getApplicationContext());
				   TextView tvEarnRate 				= new TextView(getApplicationContext());
				   
				   tvEarnDesc.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				   tvEarnRate.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				   
				  
				   tvEarnDesc.setPadding(5, 5, 5, 5);
				   tvEarnRate.setPadding(5, 5, 5, 5);
				   
				   tvEarnDesc.setTextColor(Color.parseColor("#3333FF"));
				   tvEarnRate.setTextColor(Color.parseColor("#FF3333"));


					   tvEarnDesc.setText(earn.getEarnDescription());
					   tvEarnRate.setText(String.valueOf(earn.getEarnRate()));				   

					   gridLayout.addView(tvEarnDesc);
					   gridLayout.addView(tvEarnRate);
			
				   
				   
			   }
			   
			   gridLayout.addView(createTextView("________________________","#000000"));
			   gridLayout.addView(createTextView("_______","#000000"));
			   
			   gridLayout.addView(createTextView("Gross Salary","#000000"));
			   gridLayout.addView(createTextView(String.valueOf(paymentEarn.getGrossSal()),"#3333FF"));
			   
			   gridLayout.addView(createTextView("Total Deduction","#000000"));
			   gridLayout.addView(createTextView(String.valueOf(paymentEarn.getTotalDeductSal()),"#3333FF"));
			   
			   gridLayout.addView(createTextView("Net Salary","#00CC00"));
			   gridLayout.addView(createTextView(String.valueOf(paymentEarn.getNetSal()),"#00CC00"));
			   
			   gridLayout.addView(createTextView("Last Promotion","#000000"));
			   gridLayout.addView(createTextView(paymentEarn.getLastPromotionDt(),"#FF3333"));
			   
			   
			   
		   }
		
		   
		   private TextView createTextView(String strText, String strColor)
		   {
			   TextView textView				= 	new TextView(getApplicationContext());
			   textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			   textView.setPadding(5, 5, 5, 5);
			   textView.setTextColor(Color.parseColor(strColor));
			   textView.setText(strText);
			   return textView;
		   }
		   
	}
	
}
	
