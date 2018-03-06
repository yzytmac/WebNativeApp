package example.yzy.com.webengine.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by yzy on 2018/3/5.
 */

public class JsonUtil {
    private Gson mGson;
    private static final JsonUtil ourInstance = new JsonUtil();

    public static JsonUtil getInstance() {
        return ourInstance;
    }

    private JsonUtil() {
        mGson = new Gson();
    }


    public<T> T paseJson(String pJson, TypeToken<T> pToken) {
        T t = mGson.fromJson(pJson.toString(), pToken.getType());
        return t;
    }
}
