package com.android.sunshine.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Nazila on 12/15/2017.
 */

public class NetworkStatus {
    private Context context;

    public NetworkStatus(Context context) {
        this.context = context;
    }

    public boolean isNetworkConnected() {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo!=null && activeNetworkInfo.isConnectedOrConnecting());
    }


}
