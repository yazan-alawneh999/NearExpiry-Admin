package com.big0soft.resource.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

public class KeyboardUtils {
    public static boolean performSearch(TextView v) {
        v.clearFocus();
        InputMethodManager in = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return in.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
    public static void hideKeyboard(Activity activity, View view){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public static boolean changeInputKeyboard(EditText editText, MotionEvent event){
        int inType = editText.getInputType(); // backup the input type
        editText.setInputType(InputType.TYPE_NULL); // disable soft input
        editText.onTouchEvent(event); // call native handler
        editText.setInputType(inType); // restore input type
        return true; // consume touch even
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }
    @SuppressLint("RestrictedApi")
    public static void hideKeyboard(View view) {
//        ViewUtils.hideKeyboard(view);
    }
}
