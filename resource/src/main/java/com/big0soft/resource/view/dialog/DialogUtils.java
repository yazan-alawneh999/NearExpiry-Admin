package com.big0soft.resource.view.dialog;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.big0soft.resource.functional.VoidProcedure;

public final class DialogUtils {


    public static AlertDialog setupValidateActionDialog(Context context, VoidProcedure procedure, int message) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setNegativeButton(android.R.string.no, (dialog, which) -> dialog.dismiss())
                .setPositiveButton(android.R.string.yes, (dialog, which) -> procedure.perform())
                .create();
    }

    public static AlertDialog setupValidateActionDialog(Context context, VoidProcedure procedure, String message) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setNegativeButton(android.R.string.no, (dialog, which) -> dialog.dismiss())
                .setPositiveButton(android.R.string.yes, (dialog, which) -> procedure.perform())
                .create();
    }

    public static void multipleChoiceDialog(Context context, String title, String[] items, int checkedItem, VoidProcedure procedure) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setSingleChoiceItems(items, checkedItem, (dialog, which) -> {
                    procedure.perform();
                    dialog.dismiss();
                })
                .create()
                .show();
    }

    public static void singleChoiceDialog(Context context, String title, String[] items, int checkedItem
            , DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setSingleChoiceItems(items, checkedItem, onClickListener)
                .create()
                .show();
    }

    public static void dialogVisibility(AlertDialog alertDialog, boolean visible) {
        if (visible && !alertDialog.isShowing()) {
            alertDialog.show();
        } else if (alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

}
