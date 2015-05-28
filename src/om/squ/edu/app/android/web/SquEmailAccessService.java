/**
 * 
 */
package om.squ.edu.app.android.web;


import om.squ.edu.app.android.R;
import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @author Bhabesh
 *
 */
public class SquEmailAccessService extends Activity
{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_squ_email);
			String urlSquEmail = "https://mail.squ.edu.om";
			WebView	webViewEmail	=	(WebView)findViewById(R.id.webview_email);
			webViewEmail.loadUrl(urlSquEmail);
			webViewEmail.setWebViewClient(new MyWebViewClient());
			
	}
	
	
	
	private class MyWebViewClient extends WebViewClient 
	{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			//return super.shouldOverrideUrlLoading(view, url);
			return true;
		}
		
		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler,
				SslError error) {
			// TODO Auto-generated method stub
			super.onReceivedSslError(view, handler, error);
			handler.proceed();
		}
	}
	
	
}
