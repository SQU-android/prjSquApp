/**
 * 
 */
package om.squ.edu.app.android.web;

import om.squ.edu.app.android.R;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebView;

/**
 * @author Bhabesh
 *
 */
public class SquWebAccessService extends ActionBarActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_squ_web);
		String urlSql = "http://wwwdev.squ.edu.om";
		WebView	webViewSqu	=	(WebView)findViewById(R.id.webview_squ);
		webViewSqu.loadUrl(urlSql);
	}
}
