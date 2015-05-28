/**
 * 
 */
package om.squ.edu.app.android.payment.service.rest;

import om.squ.edu.app.android.util.HttpUtils;
import om.squ.edu.app.android.util.ServiceUtil;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Bhabesh
 *
 */
public class PaymentCurrentYearRestService extends AsyncTask<String, Void, String>
{
	private	TextView					textViewCurrentYear;
	private TaskGetCurrentYear<String>	caller;
	private	String						strPaymentYrTxt;
	private	Context						context;
	private	String						msgErr	=	null;
	
	public PaymentCurrentYearRestService(Context context, TaskGetCurrentYear<String> caller, String strPaymentYrTxt, TextView	textViewCurrentYear)
	{
		this.context				=	context;
		this.caller 				=	caller;
		this.textViewCurrentYear	=	textViewCurrentYear;
		this.strPaymentYrTxt		=	strPaymentYrTxt;
		
	}
	

		@Override
		protected String doInBackground(String... params) 
		{
			String				currYearRestVal					=	null;
			String				urlPaymentCurrYearRest			=	params[0];
			
			
			
			RestTemplate 	restTemplate	=	new RestTemplate();
			
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpUtils.getNewHttpClient()));				//By pass self signed certificate 

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			try
			{
				currYearRestVal = restTemplate.getForObject(urlPaymentCurrYearRest,String.class);
			}
			catch (Exception ex)
			{
				msgErr	=	ex.getMessage();
				//Toast.makeText(context, "Error - Fetching year : "+ex.getMessage(), Toast.LENGTH_LONG).show();
			}
			
			return currYearRestVal;
		}
		
		
		   @Override
	    protected void onPostExecute(String paramCurrYear)
		{
			 if(null==msgErr)
			 {
				 textViewCurrentYear.setText(strPaymentYrTxt + paramCurrYear);
				 caller.getCurrentYearRest(paramCurrYear);
			 }
			 
			 
		}
		

	
	public interface TaskGetCurrentYear<T>    {
		public void getCurrentYearRest(String paramYear);
	}

	
}
