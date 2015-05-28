/**
 * 
 */
package om.squ.edu.app.android.basic;

import om.squ.edu.app.android.R;
import om.squ.edu.app.android.basic.bo.User;
import om.squ.edu.app.android.util.HttpUtils;
import om.squ.edu.app.android.util.ServiceUtil;

import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.webkit.WebView.FindListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Bhabesh
 *
 */
public class MacService 
{
	User userServ = null; 
	private 	String 		urlWifiUser;
	private		TextView 	textViewName;
	private		TextView	textViewId;
	private 	Editor 		editor;
	protected	TextView	textLoadingMac;
	private		Resources	resources;
	private		Context		context;
	
	
	public MacService(Context context, String macAddress, TextView txtViewName, TextView txtViewId, TextView txtLoadingMac, SharedPreferences	prefUser, Resources resources)
	{
		this.resources			=	resources;
		this.context			=	context;
		String		mode 		= 	resources.getString(R.string.mode_deploy);
		ServiceUtil	serviceUtil	=	new ServiceUtil(resources, mode);
		
		editor	=	prefUser.edit();
		
		String	 urlWifi 	=	serviceUtil.getUrlMac();
		this.urlWifiUser = urlWifi + "/"+macAddress;
		this.textViewName	=	txtViewName;
		this.textViewId		=	txtViewId;
		this.textLoadingMac	=	txtLoadingMac;
		
		UserService userService	= new UserService();
		userService.execute(urlWifiUser);
		

	}

	private class UserService extends AsyncTask<String, Integer, User>
	{
		private User	user	=	null;
		private String	msgErr	=	null;		
		
		/**
		 * @return the user
		 */
		public User getUser() {
			return user;
		}

		@Override
		protected User doInBackground(String... params) {
			String 			urlRest 		=	params[0];
			User 			user			=	null;  
			
			
			RestTemplate 	restTemplate	=	new RestTemplate();
			/**
			 * TODO Below line is for bypassing ssl for self generated certificate
			 */
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpUtils.getNewHttpClient()));				/*By pass ssl */
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			try
			{
				user	=	restTemplate.getForObject(urlRest, User.class);
			}
			catch(Exception ex)
			{
				
				msgErr	=	ex.getMessage();
			}
			
			return user;

		}
		
		   @Override
		    protected void onPostExecute(User user) {
		    	if (msgErr != null)
		    	{
		    		ServiceUtil	serviceUtil		=	new ServiceUtil(context);
		    		String		strTitle		=	resources.getString(R.string.ex_error_100_mac_service_title);
		    		String		strErrMsg		=	resources.getString(R.string.ex_error_100_mac_service);
		    		String		strBttnClose	=	resources.getString(R.string.bttn_close);
		    		
		    		serviceUtil.alertDialogueError(strTitle, strErrMsg, strBttnClose);
		    	}
		    	else
		    	{
				   	textViewName.setText(user.getUserName());
			    	textViewId.setText(user.getUserId());
			    	textLoadingMac.setText("");
			    	editor.putString("userId", user.getUserId());
			    	editor.commit();
		    	}

		    }
		
		   @Override
	        protected void onProgressUpdate(Integer... values) {
			   //super.onProgressUpdate(values);  
			   textLoadingMac.setText(" .. Loading .. "+values[0] + "%");
			   
		   }
		
	}
}
