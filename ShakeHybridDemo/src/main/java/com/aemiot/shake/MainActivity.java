package com.aemiot.shake;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aemiot.breeze.BreezeSDK;
import com.aemiot.shake.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BreezeSDK.getInstance().initialization();

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.webView.loadUrl("file:///android_asset/local.html");

    }


}
