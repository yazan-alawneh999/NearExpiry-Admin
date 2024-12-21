package com.big0soft.resource.helper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class UserInterfaceHelper {

    public static <C extends Context> String copy(C ctx, String text) {
        ClipboardManager clipboard = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("TextView", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(ctx, "copy: " + text, Toast.LENGTH_SHORT).show();
        return clipboard.getPrimaryClip().getItemAt(0).getText().toString();
    }
    public static  String copy(ClipboardManager clipboard, String text) {
        ClipData clip = ClipData.newPlainText("TextView", text);
        clipboard.setPrimaryClip(clip);
        return clipboard.getPrimaryClip().getItemAt(0).getText().toString();
    }



}
