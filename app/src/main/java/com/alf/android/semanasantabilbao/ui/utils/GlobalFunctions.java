package com.alf.android.semanasantabilbao.ui.utils;

import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Alberto Laría Fernández on 15/11/2016.
 */

public class GlobalFunctions {

    public boolean checkNetworkStatus(ConnectivityManager connectivityManager) {
        //ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(getApplication().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected() == true) {
            //Toast.makeText(this, "Network Available", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            //Toast.makeText(this, "Network Not Available", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public String getScreenOrientation(int orientation) {
        String screenOrientation = "";
        switch (orientation) {
            case Configuration.ORIENTATION_UNDEFINED: screenOrientation = "Undefined"; break;
            case Configuration.ORIENTATION_LANDSCAPE: screenOrientation = "Landscrape"; break;
            case Configuration.ORIENTATION_PORTRAIT:  screenOrientation = "Portrait"; break;
        }

        return screenOrientation;
    }
}
