package com.big0soft.resource.helper;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;

import java.time.LocalDateTime;


public class IntentHelper {

    public static void startActivity(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }

    public static void startActivityWithFinish(Activity activity, Class<?> cls) {
        startActivity(activity, cls);
        activity.finish();
    }

    public static void startService(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startService(intent);
    }

    public static void startService(Activity activity, Intent intent) {
        activity.startService(intent);
    }

    public static void stopService(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.stopService(intent);
    }


    public static void refreshActivity(Activity activity) {
        Intent intent = new Intent(activity, activity.getClass());
        activity.startActivity(intent);
        activity.finish();
    }

    public static void startWhatsApp(Activity activity, String phoneNumber) {
        String url = "https://api.whatsapp.com/send?phone=" + phoneNumber;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        activity.startActivity(i);
    }

    public static void startGmailApp(Activity activity) {
        try {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            activity.startActivity(emailIntent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static <C extends Context> String getLauncherActivity(C context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        return intent.getComponent().getClassName();
    }

    public static <T extends Context> void startTelegram(T context, String url) {
        Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(telegram);
    }
    public static  void startTelegram(Context context, String url,String message) {
        Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        telegram.putExtra(Intent.EXTRA_TEXT, message);
        context.startActivity(telegram);
    }


    /**
     * @return image path after take it
     */
    public static void openCamera(Activity context, int requestCode) {
        String name;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            name = LocalDateTime.now().toString();
        } else {
            name = String.valueOf(System.currentTimeMillis());
        }
//        File destination = new File(Environment.getExternalStorageDirectory(), "camera/images/camera_image" + ".jpg");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination));
        context.startActivityForResult(intent, requestCode);

    }

//    private static Uri getTempUri() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
//        String dt = sdf.format(new Date());
//        File imageFile = new File(Environment.getExternalStorageDirectory() + "/MyApp/", "Camera_" + dt + "jpg");
//        if (!imageFile.getParentFile().exists()) {
//            imageFile.getParentFile().mkdir();
//        }
//    }

}