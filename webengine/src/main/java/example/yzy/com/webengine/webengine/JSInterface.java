package example.yzy.com.webengine.webengine;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Method;

import example.yzy.com.webengine.Utils.JsonUtil;
import example.yzy.com.webengine.Utils.Scheduler;
import example.yzy.com.webengine.webengine.media.ImageObject;
import example.yzy.com.webengine.webengine.media.ImageUtil;
import example.yzy.com.webengine.webengine.net.NetObject;
import example.yzy.com.webengine.webengine.net.NetUtil;

/**
 * Created by yzy on 2018/3/6.
 */

public class JSInterface {

    /**
     * @author YangZhenYu
     * created at 2018/3/6 16:38
     * 功能：选择图片
     */
    @JavascriptInterface
    public void chooseImage(String json) {
        TypeToken<ImageObject> vTypeToken = new TypeToken<ImageObject>() {
        };
        withParameNoReturnFunction(json, vTypeToken, ImageUtil.class, "chooseImage");
    }

    /**
     * @author YangZhenYu
     * created at 2018/3/6 16:38
     * 功能：获取网络状态
     */
    @JavascriptInterface
    public void getNetworkType(String json) {
        TypeToken<NetObject> vTypeToken = new TypeToken<NetObject>() {
        };
        withParameNoReturnFunction(json, vTypeToken, NetUtil.class, "getNetworkType");
    }

    /**
     * 抽取出来的 有参无返回值 公共函数
     *
     * @param json           json字符串
     * @param pJsonTypeToken json解析的typetoken
     * @param utilClazz      要使用的工具类class
     * @param functionName   要使用的工具类中的方法名
     * @param <T>            解析json的实体类
     */
    private <T> void withParameNoReturnFunction(String json, TypeToken<T> pJsonTypeToken, final Class utilClazz, final String functionName) {
        Log.e("yzy", "getNetworkType: " + json);
        final T t = JsonUtil.getInstance().paseJson(json, pJsonTypeToken);
        //webview第一次加载页面是在主线程中，所以后面的操作要保持线程一致
        //选择图片的逻辑就交给ImageUtil来做
        Scheduler.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object vInstance = utilClazz.newInstance();
                    Method vMethod = utilClazz.getDeclaredMethod(functionName, t.getClass());
                    vMethod.setAccessible(true);
                    vMethod.invoke(vInstance, t);
                } catch (Exception pE) {
                    pE.printStackTrace();
                }
            }
        });
    }


}
