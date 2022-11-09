package com.example.myapplication;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebviewInterface {
    Context context ;
    WebviewInterface(Context context){
        this.context=context;
    }

    @JavascriptInterface
    public void showToast(String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
