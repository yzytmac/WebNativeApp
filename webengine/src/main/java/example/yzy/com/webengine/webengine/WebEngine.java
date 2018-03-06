package example.yzy.com.webengine.webengine;

import android.app.Activity;
import android.provider.Settings;
import android.telecom.Call;
import android.util.Log;
import android.webkit.WebView;

/**
 * Created by yzy on 2018/3/5.
 */

public class WebEngine {
    private static final String JM = "jm";
    private static WebEngine instance = new WebEngine();
    private Activity mActivity;
    private WebView mWebView;

    private WebEngine() {
    }

    public static WebEngine getInstance() {
        if (instance == null) {
            instance = new WebEngine();
        }
        return instance;
    }

    public void init(Activity pActivity,WebView pWebView) {
        mActivity = pActivity;
        mWebView = pWebView;
        pWebView.addJavascriptInterface(new JSInterface(), JM);
    }

    public void callJsMethord(String functionString,String... params){
        StringBuffer sb = new StringBuffer();
        for (String vParam : params) {
            sb.append(vParam+",");
        }
        if(sb.length()>0) {
            sb.deleteCharAt(sb.length()-1);
        }
        mWebView.loadUrl("javascript:"+functionString+"('"+sb.toString()+"')");
    }

    public Activity getActivity(){return mActivity;}
    public WebView getWebView(){return mWebView;}


}
