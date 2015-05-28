/**
 * 
 */
package om.squ.edu.app.android.payment.service;


import om.squ.edu.app.android.R;
import om.squ.edu.app.android.payment.bo.Earn;
import om.squ.edu.app.android.payment.bo.PaymentEarn;
import om.squ.edu.app.android.util.HttpUtils;
import om.squ.edu.app.android.util.ServiceUtil;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

/**
 * @author Bhabesh
 *
 */
public class PaymentService extends Activity
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
		return true;
	}
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return true;
	}
	
	
	private class Payment extends AsyncTask<String, Void, PaymentEarn>
	{
		private	String	msgError	=	null;
		@Override
		protected PaymentEarn doInBackground(String... params) {
			Resources			resources	=	getResources();
			PaymentEarn			paymentEarn	=	null;
			String				mode 		= 	resources.getString(R.string.mode_deploy);
			ServiceUtil			serviceUtil	=	new ServiceUtil(resources, mode);
						
			String 				userId 		=	params[0];
			
			
			String urlPayment		=  serviceUtil.getUrlPayment()+"/" + userId;

			
			RestTemplate 	restTemplate	=	new RestTemplate();
			/**
			 * TODO Below line is for bypassing ssl for self generated certificate
			 */
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpUtils.getNewHttpClient()));				/*By pass ssl */

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			try
			{
				paymentEarn = restTemplate.getForObject(urlPayment,PaymentEarn.class);
			}
			catch(Exception ex)
			{
				msgError	=	ex.getMessage();
			}
			
			
			return paymentEarn;
		}
		
		   @Override
		    protected void onPostExecute(PaymentEarn paymentEarn) {
			   GridLayout gridLayout;
			 
			   
			   if(null == msgError)
			   {
			   
					   gridLayout = (GridLayout)findViewById(R.id.gridPayment);
					   
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
			   else
			   {
				   String		strTitle		=	getResources().getString(R.string.ex_error_101_PaymentService_title);
				   String		strErrText		=	getResources().getString(R.string.ex_error_101_PaymentService);
				   String		strBttnClose	=	getResources().getString(R.string.bttn_close);
				   
				   ServiceUtil	serviceUtil	=	new ServiceUtil(PaymentService.this);
				   serviceUtil.alertDialogueError(strTitle, strErrText, strBttnClose);
			   }
			   
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
	
