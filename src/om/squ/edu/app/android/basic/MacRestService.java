/**
 * 
 */
package om.squ.edu.app.android.basic;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import om.squ.edu.app.android.basic.bo.User;
import android.os.AsyncTask;
import android.util.Log;

/**
 * @author Bhabesh
 *
 */
public class MacRestService 
{
	private User squUser;
	
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.squUser = user;
	}
    
	public void getUser(String macAdd)
	{
		String strurl = "http://@172.20.10.58:8080/prjRestBasic/macadd";
		strurl = strurl+"/"+macAdd;
		
		//getRest(strurl);
		
		
		UserRetriver userRetriver	=	new UserRetriver(strurl);
		userRetriver.execute();
		//Log.e(" User 3 :",userRetriver.getUser().toString());
		
		
		//return squUser;
		
		/*
		RestTemplate restTemplate	=	new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		User user	=	restTemplate.getForObject(strMacRESTurl, User.class);
		Log.e(" User 3 :",user.toString());
		*/
	}
	

	private class UserRetriver extends AsyncTask<Void, Void, User>
	{
		String strMacRESTurl = null;
		User	user = null;

		/**
		 * @return the user
		 */
		public User getUser() {
			return user;
		}


		public UserRetriver(String strUrl)
		{
			strMacRESTurl = strUrl;
		}
		
		
		@Override
		protected User doInBackground(Void... params) {
			try
			{
				
				


				final String authUser = "squ\\bhabesh";
				final String authPassword = "";
				Authenticator.setDefault(
				   new Authenticator() {
				      public PasswordAuthentication getPasswordAuthentication() {
				         return new PasswordAuthentication(
				               authUser, authPassword.toCharArray()); 
				      }
				   }
				);

				System.setProperty("http.proxyHost", "192.168.10.11");
				System.setProperty("http.proxyPort", "80");
				System.setProperty("http.proxyUser", authUser);
				System.setProperty("http.proxyPassword", authPassword);
	
				
				
				
				HttpAuthentication authHeader = new HttpBasicAuthentication("", "");
				HttpHeaders requestHeaders = new HttpHeaders();
				requestHeaders.setAuthorization(authHeader);
				
				
				
				
				RestTemplate restTemplate	=	new RestTemplate();
				
				/*
				HttpComponentsClientHttpRequestFactory factory =   new HttpComponentsClientHttpRequestFactory(); //((HttpComponentsClientHttpRequestFactory) restTemplate.getRequestFactory());
		        DefaultHttpClient defaultHttpClient = (DefaultHttpClient) factory.getHttpClient();
		        
		        defaultHttpClient.getAuthSchemes().register("basic",new BasicSchemeFactory());
		        HttpHost proxy = new HttpHost("192.168.10.11", 80);
		        defaultHttpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		        defaultHttpClient.getCredentialsProvider().setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(authUser, authPassword));
		        
		        
		        //defaultHttpClient.getCredentialsProvider().setCredentials(new AuthScope("192.168.10.11", 80), new UsernamePasswordCredentials(authUser,authPassword ));
		        
		        factory.setHttpClient(defaultHttpClient);
		        restTemplate.setRequestFactory(factory);
		        List<String> authPrefs = new ArrayList<String>(2);
		        authPrefs.add(AuthPolicy.DIGEST);
		        authPrefs.add(AuthPolicy.BASIC);
		        defaultHttpClient.getParams().setParameter(AuthPNames.CREDENTIAL_CHARSET, authPrefs);
		        */
		        
		    /*    
		         HttpComponentsClientHttpRequestFactory factory =   new HttpComponentsClientHttpRequestFactory();
		        HttpHost proxy = new HttpHost("192.168.10.11", 80);
		        
		        DefaultHttpClient defaultHttpClient = (DefaultHttpClient) factory
		                .getHttpClient();
		        
		        
		        
		        defaultHttpClient.getParams().setParameter(
		                ConnRoutePNames.DEFAULT_PROXY, proxy);
		       
		        factory.setHttpClient(defaultHttpClient);
		        restTemplate.setRequestFactory(factory);
				*/

				
				restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
				
				User user	=	restTemplate.getForObject(strMacRESTurl, User.class);
				Log.e("User 1 : ",user.toString());
				
				return user;
			}
			catch(Exception ex)
			{
				Log.e("Exception in REST for MacAddress : ",ex.getMessage(),ex);
			}
			
			return null;
		}
		
	    @Override
	    protected void onPostExecute(User user) {
	    	Log.e(" User 2 :",user.toString());
	    	this.user = user;
	    	
	      //squUser = user;
	    	
	    }



	}
	
	public void getRest(String strUrl)
	{
		RestTemplate restTemplate	=	new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		
		User user	=	restTemplate.getForObject(strUrl, User.class);
		Log.e(" User 4 :",user.toString());
	}
	
}
