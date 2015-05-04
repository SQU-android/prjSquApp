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

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.TextView;

/**
 * @author Bhabesh
 *
 */
public class MacService 
{
	User userServ = null; 
	private String 		urlWifiUser 	=	null;
	private	TextView 	textViewName	=	null;
	private	TextView	textViewId		=	null;
	private Editor 		editor;
	
	
	public MacService(String macAddress, TextView txtViewName, TextView txtViewId, SharedPreferences	prefUser, Resources resources)
	{
		String	mode = resources.getString(R.string.mode_deploy);
		ServiceUtil	serviceUtil	=	new ServiceUtil(resources, mode);
		
		editor	=	prefUser.edit();
		
		String	 urlWifi 	=	serviceUtil.getUrlMac();
		this.urlWifiUser = urlWifi + "/"+macAddress;
		this.textViewName	=	txtViewName;
		this.textViewId		=	txtViewId;
		
		UserService userService	= new UserService();
		userService.execute(urlWifiUser);
		

	}

	private class UserService extends AsyncTask<String, Void, User>
	{
		private User	user	=	null;
		
		
		/**
		 * @return the user
		 */
		public User getUser() {
			return user;
		}

		@Override
		protected User doInBackground(String... params) {
			String 			urlRest 		=	params[0];
			
			
			
			RestTemplate 	restTemplate	=	new RestTemplate();
			/**
			 * TODO Below line is for bypassing ssl for self generated certificate
			 */
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(HttpUtils.getNewHttpClient()));				/*By pass ssl */
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			
			User user	=	restTemplate.getForObject(urlRest, User.class);
			
			return user;

		}
		
		   @Override
		    protected void onPostExecute(User user) {
		    	textViewName.setText(user.getUserName());
		    	textViewId.setText(user.getUserId());
		    	editor.putString("userId", user.getUserId());
		    	editor.commit();
		    }
		
		
		
	}
}
