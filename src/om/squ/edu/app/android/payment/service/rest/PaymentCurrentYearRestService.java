/**
 * 
 */
package om.squ.edu.app.android.payment.service.rest;

import om.squ.edu.app.android.util.HttpUtils;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * @author Bhabesh
 *
 */
public class PaymentCurrentYearRestService extends AsyncTask<String, Void, String>
{
	private	TextView					textViewCurrentYear;
	private TaskGetCurrentYear<String>	caller;
	private	String						strPaymentYrTxt;
	
	public PaymentCurrentYearRestService(TaskGetCurrentYear<String> caller, String strPaymentYrTxt, TextView	textViewCurrentYear)
	{
		this.caller 				=	caller;
		this.textViewCurrentYear	=	textViewCurrentYear;
		this.strPaymentYrTxt		=	strPaymentYrTxt;
	}
	

		@Override
		protected String doInBackground(String... params) 
		{
			
			String				urlPaymentCurrYearRest				=	params[0];
			
			
			
			RestTemplate 	restTemplate	=	new RestTemplate();
			
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpUtils.getNewHttpClient()));				//By pass self signed certificate 

			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

			String	currYearRestVal = restTemplate.getForObject(urlPaymentCurrYearRest,String.class);
			
			return currYearRestVal;
		}
		
		
		   @Override
	    protected void onPostExecute(String paramCurrYear)
		{
			 textViewCurrentYear.setText(strPaymentYrTxt + paramCurrYear);
			 caller.getCurrentYearRest(paramCurrYear);
		}
		

	
	public interface TaskGetCurrentYear<T>    {
		public void getCurrentYearRest(String paramYear);
	}


	
	
}
