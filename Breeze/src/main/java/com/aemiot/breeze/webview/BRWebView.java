package com.aemiot.breeze.webview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.aemiot.breeze.BreezeSDK;
import com.aemiot.breeze.client.BRWebChromeClient;
import com.aemiot.breeze.client.BRWebClient;
import com.aemiot.breeze.jsbridge.HybridPlugin;
import com.aemiot.breeze.jsbridge.JSBridge;
import com.aemiot.breeze.jsbridge.PluginManager;
import com.aemiot.hybrid.R;

import java.util.Iterator;
import java.util.Map;

public class BRWebView extends WebView implements IWebView{

    public static final String TAG = "BRWebView";

    FrameLayout mFloatRoot;
    ProgressBar mProgressBar;

    private JSBridge mJSBridge;
    private PluginManager mPluginManager;

    public BRWebView(Context context) {
        super(context);
        init();
    }

    public BRWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BRWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        Iterator iterator = mPluginManager.getPluginMap().values().iterator();
        while (iterator.hasNext()) {
            HybridPlugin plugin = (HybridPlugin) iterator.next();
            plugin.active();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        Iterator iterator = mPluginManager.getPluginMap().values().iterator();
        while (iterator.hasNext()) {
            HybridPlugin plugin = (HybridPlugin) iterator.next();
            plugin.inactive();
        }
    }

    @Override
    public boolean performLongClick() {
        return true;
    }

    public void init() {
        // UI
        mProgressBar = new ProgressBar(getContext());
        mProgressBar.setIndeterminate(false);
        mProgressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.loading_color));
        mProgressBar.setVisibility(GONE);

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(100, 100);
        lp.gravity = Gravity.CENTER;
        mFloatRoot = new FrameLayout(getContext());
        mFloatRoot.addView(mProgressBar, lp);

        this.addView(mFloatRoot, new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        // cache
        setWebViewClient(new BRWebClient());
        setWebChromeClient(new BRWebChromeClient());
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getContext().getCacheDir().getAbsolutePath());
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);

        // UA
        String ua = settings.getUserAgentString();
        if(ua == null) {
            ua = "";
        } else {
            ua += " ";
        }
        settings.setUserAgentString(ua + "Breeze/1.0");
        Log.i(TAG, "UA:" + settings.getUserAgentString());

        // base
        mJSBridge = new JSBridge(this);
        mPluginManager = new PluginManager();

        Iterator iterator =
                BreezeSDK.getInstance().getDefaultPluginConfig().getDefaultPlugin().entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Class<? extends HybridPlugin>> entry =
                    (Map.Entry<String, Class<? extends HybridPlugin>> ) iterator.next();
            try {
                mPluginManager.registerPlugin(entry.getKey(), entry.getValue().newInstance());
                mPluginManager.getPlugin(entry.getKey()).attach(getContext(), mJSBridge);
            } catch (InstantiationException e) {

            } catch (IllegalAccessException e) {

            }
        }
    }

    @Override
    // 设置回退
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.canGoBack()) {
            this.goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    @Override
    public Context getWebContext() {
        return getContext();
    }

    @Override
    public JSBridge getJSBridge() {
        return mJSBridge;
    }

    @Override
    public void registerPlugin(String name, HybridPlugin plugin) {
        mPluginManager.registerPlugin(name, plugin);
        plugin.attach(getContext(), mJSBridge);
    }

    @Override
    public void unregisterPlugin(String name) {
        mPluginManager.getPlugin(name).detach();
        mPluginManager.unregisterPlugin(name);
    }

    @Override
    public HybridPlugin getPlugin(String name) {
        return mPluginManager.getPlugin(name);
    }

    @Override
    public void evaluateJavascript(final String script) {
        new Handler(getContext().getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                loadUrl("javascript:" + script);
            }
        });
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(GONE);
    }
}
