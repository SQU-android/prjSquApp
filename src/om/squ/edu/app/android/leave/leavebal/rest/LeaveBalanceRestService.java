/**
 * 
 */
package om.squ.edu.app.android.leave.leavebal.rest;

import om.squ.edu.app.android.util.HttpUtils;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * @author Bhabesh
 *
 */
public class LeaveBalanceRestService extends AsyncTask<String, Void, String> 
{
	
	private	TextView 	tvLeaveBal;
	private	String		msgErr	=	null;
	
	public LeaveBalanceRestService(TextView tvLeaveBal)
	{
		this.tvLeaveBal	=	tvLeaveBal;
	}

	@Override
	protected String doInBackground(String... params) {
		String 	leaveDays	=	null;
		String 	urlRest		=	params[0];
		
		Log.e("url : ", urlRest);
		
		RestTemplate 	restTemplate	=	new RestTemplate();
		
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpUtils.getNewHttpClient()));				//By pass self signed certificate 
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		try
		{
			leaveDays	=	restTemplate.getForObject(urlRest,String.class);
		}
		catch(Exception ex)
		{
			msgErr		=	ex.getMessage();
		}
		
		return leaveDays;
	}
	
	 @Override
	   protected void onPostExecute(String leaveDays)
	   {
		 	if(null == msgErr)
		 	{
		 			tvLeaveBal.setText(leaveDays);
		 	}
		 	
	   }

}
