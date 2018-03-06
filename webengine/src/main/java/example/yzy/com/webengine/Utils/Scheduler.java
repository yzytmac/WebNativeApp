package example.yzy.com.webengine.Utils;


import example.yzy.com.webengine.webengine.WebEngine;

/**
 * Created by yzy on 2018/3/6.
 */

public class Scheduler {
    public static void runOnUiThread(Runnable pRunnable) {
        WebEngine.getInstance().getActivity().runOnUiThread(pRunnable);
    }

    public static void runOnCurrentThread(Runnable pRunnable) {
        pRunnable.run();
    }

    public static void runOnNewThread(Runnable pRunnable) {
        new Thread(pRunnable).start();
    }
}
