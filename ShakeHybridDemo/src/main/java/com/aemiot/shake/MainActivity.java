package com.aemiot.shake;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aemiot.afoucs.AFoucsSDK;
import com.aemiot.shake.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AFoucsSDK.getInstance().initialization(this.getApplicationContext());
        binding.webView.loadUrl("file:///android_asset/local.html");

    }


}
