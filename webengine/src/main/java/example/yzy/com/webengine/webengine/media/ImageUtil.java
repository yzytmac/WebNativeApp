package example.yzy.com.webengine.webengine.media;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import example.yzy.com.webengine.Actions;
import example.yzy.com.webengine.webengine.WebEngine;

import static example.yzy.com.webengine.RequestCodes.CHOOSE_IMG_CODE;
import static example.yzy.com.webengine.webengine.ExtraKey.CHOOSE_IMG_KEY;

/**
 * Created by yzy on 2018/3/5.
 */

public class ImageUtil {

    private Activity mActivity;

    public void chooseImage(ImageObject pImageObject) {
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT); //"android.intent.action.GET_CONTENT"
        innerIntent.setType("image/*"); //查看类型 String IMAGE_UNSPECIFIED = "image/*";
        Intent wrapperIntent = Intent.createChooser(innerIntent, null);
        mActivity = WebEngine.getInstance().getActivity();
        mActivity.startActivityForResult(wrapperIntent, CHOOSE_IMG_CODE);
        LocalBroadcastManager.getInstance(mActivity).registerReceiver(new ImageChooseBroadcastReceiver(pImageObject), new IntentFilter(Actions.CHOOSE_IMG_ACTION));
    }

    public class ImageChooseBroadcastReceiver extends BroadcastReceiver {
        private ImageObject mImageObject;
        public ImageChooseBroadcastReceiver() {
        }

        public ImageChooseBroadcastReceiver(ImageObject pImageObject) {
            mImageObject = pImageObject;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("yzy", "onReceive: -----------------");
            String imgUri = intent.getStringExtra(CHOOSE_IMG_KEY);
            WebEngine.getInstance().callJsMethord(mImageObject.getSuccess(),imgUri);
            LocalBroadcastManager.getInstance(mActivity).unregisterReceiver(this);
        }
    }


}
