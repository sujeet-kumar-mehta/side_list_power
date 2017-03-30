package in.sample.saltlistpower.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class DataUtils {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

}
