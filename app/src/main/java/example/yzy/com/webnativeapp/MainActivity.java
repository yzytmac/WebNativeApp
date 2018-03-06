package example.yzy.com.webnativeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import example.yzy.com.webengine.webengine.WebEngine;

public class MainActivity extends AppCompatActivity {
    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebview = (WebView) findViewById(R.id.webview);
        // 设置WebView属性，能够执行Javascript脚本
        mWebview.getSettings().setJavaScriptEnabled(true);
        // 加载需要显示的网页 ，这里要调用一次，webviewclient里面也要调用一次

        mWebview.loadUrl("file:///android_asset/index.html");
//        mWebview.loadUrl("file:///android_asset/frozenui-master/demo/index.html");
//        webview.loadUrl("http://frozenui.github.io/frozenui/demo/index.html");
        // 设置Web视图
        //如果不为webview设置webviewClient将会跳转到浏览器打开页面
        //最简单的就是直接new 一个webviewclient设给他，就会是webview来加载网页了，并不需要重写里面的shouldoverrideurlloading方法
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.e("yzy", "onConsoleMessage: " + consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Log.e("yzy", "onJsAlert: " + message);
                return super.onJsAlert(view, url, message, result);
            }
        });


        //初始化引擎
        WebEngine.getInstance().init(this, mWebview);
    }

    @Override
    public void onBackPressed() {
        if (mWebview.canGoBack()) {
            mWebview.goBack();
        } else {
            finish();
        }
    }
}
