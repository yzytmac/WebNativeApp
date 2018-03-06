package example.yzy.com.webengine.webengine.media;


import example.yzy.com.webengine.webengine.WebEngine;

/**
 * Created by yzy on 2018/3/5.
 */

public class ImageUtil {

    public void chooseImage(ImageObject pImageObject) {
        WebEngine.getInstance().callJsMethord(pImageObject.getSuccess());
    }
}
