package com.big0soft.resource.helper;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

public class MediaHelper {
    public static int getMediaDuration(Context context, String url) {
        return getMediaDuration(context,Uri.parse(url));
    }
    public static int getMediaDuration(Context context, Uri uri) {
        MediaPlayer mp = MediaPlayer.create(context, uri);
        int duration = mp.getDuration();
        mp.release();
        return duration;
    }


    public static Uri getMediaUserId(Context context, int id) {
        return Uri.parse("android.resource://" + context.getPackageName() + "/" + id);
    }
}
