package com.progtechuc.moviedb.helper;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

import com.progtechuc.moviedb.R;

public class Loading {
    private Activity activity;
    private Dialog dialog;

    public Loading(Activity activity){this.activity=activity;}

    public void startProgress(){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_bar);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void stopProgress(){
        dialog.dismiss();
    }
}
