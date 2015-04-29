/**
 * 
 */
package om.squ.edu.app.android.basic;

import om.squ.edu.app.android.basic.bo.User;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.util.Log;

/**
 * @author Bhabesh
 *
 */
public class TestService {
	
	public TestService()
	{
		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();

		// Add the Jackson message converter
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		// Make the HTTP GET request, marshaling the response from JSON to an array of Events
		User user = restTemplate.getForObject("http://172.20.10.58:8080/prjRestBasic/macadd/3423BA4C0127", User.class);
		
		Log.e(" User TestService :",user.toString());
		
	}

}
