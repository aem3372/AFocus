package com.aemiot.breeze.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.aemiot.breeze.BreezeSDK;

import java.util.List;

/**
 * Created by fanye on 16/4/24.
 */
public class Navigation {
    public static void jump(Context context, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if(isIntentExisting(context, intent)) {
            context.startActivity(intent);
        } else {
            intent = new Intent(Intent.ACTION_VIEW, BreezeSDK.getInstance().getBrowserUri());
            intent.putExtra("redirect", uri);
            context.startActivity(intent);
        }
    }

    public static boolean isIntentExisting(Context context, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        return isIntentExisting(context, intent);
    }

    private static boolean isIntentExisting(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo.size() > 0) {
            return true;
        }
        return false;
    }
}
