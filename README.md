# Breeze

## gradle依赖
`compile 'com.aemiot:breeze:0.0.1'`

## 初始化
`BreezeSDK.getInstance().initialization();`

## Layout方式使用

```
<com.aemiot.breeze.webview.BRWebView
            android:layout_width="match_parent"
            android:layout_height="match_parent" />
```

载入一个页面

`webView.loadUrl(url);`

## 自定义JSBridge插件

### 定制插件
```
public class CustemPlugin extends HybridPlugin
{
    @Override
    public void execute(String method, String params, CallMethodContext jsContext) {
        if("custemMethod".equals(method)) {
            if(params != null) {
                try {
                    JSONObject jsonObject = new JSONObject(params);
                    if (jsonObject.has("value")) {
                        String value = jsonObject.getString("value");
                        custemMethod(value);
                        jsContext.success();
                    } else {
                        custemMethod(null);
                    }
                } catch (JSONException e) {
                    jsContext.fail();
                }
            }
        }
    }

    private void custemMethod(@Nullable String value) {

    }
}
```

### 注册插件
`webView.registerPlugin("custem", new CustemPlugin());`

### 发送事件
`getBridge().fire(new BREvent("BR_CUSTEM"));`

### 生命周期
- Attach 被WebView绑定
- Active 切换到前台
- Inactive 切换到后台
- Detach 与WebView解绑


## 工具

### 跳转工具-Navigation
BreezeSDK.setBrowserUri(browserUri); 配置默认浏览器Activity
Navigation.jump(context, nextUri); 跳转（优先本地intent响应，否则使用默认浏览器Activity）


