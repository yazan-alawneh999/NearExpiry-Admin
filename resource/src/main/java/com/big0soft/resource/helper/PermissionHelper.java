package com.big0soft.resource.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener;

import java.io.File;

public class PermissionHelper {
    public static void installApk(Activity activity, File fileApk) {
        if (!activity.getPackageManager().canRequestPackageInstalls()) {
            activity.startActivity(new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES
                    , Uri.parse("package:" + activity.getPackageName())));
        } else {
            Intent intent = new Intent(Intent.ACTION_INSTALL_PACKAGE);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(Intent.EXTRA_NOT_UNKNOWN_SOURCE, true);
            intent.setData(FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", fileApk));
            activity.startActivity(intent);
        }
    }
    public boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Context context, String permission) {
        requestPermission(context, new BasePermissionListener(), permission);
    }

    public static void requestPermission(Context context, PermissionListener permissionListener, String permission) {
        Dexter.withContext(context).withPermission(permission)
                .withListener(permissionListener)
                .check();
    }

    public static void requestPermission(Context context, PermissionViewBuilder permissionListener, String permission) {
        requestPermission(context, permissionListener.build(context), permission);
    }


    public static void requestPermission(Context context, String... permission) {
        requestPermission(context, new CompositeMultiplePermissionsListener(), permission);
    }

    public static void requestPermission(Context context, MultiplePermissionsListener permissionsListener, String... permission) {
        Dexter.withContext(context).withPermissions(permission)
                .withListener(permissionsListener)
                .check();
    }

    public static void requestPermission(Context context, PermissionsViewBuilder permissionsListener, String... permission) {
//        MultiplePermissionsListener compositePermissionsListener = new CompositeMultiplePermissionsListener(snackbarMultiplePermissionsListener, dialogMultiplePermissionsListener);
        requestPermission(context, permissionsListener.build(context), permission);
    }


    public interface PermissionViewBuilder {
        PermissionListener build(Context context);
    }

    public static class PermissionViewBuilderDialog implements PermissionViewBuilder {
        private final PermissionModel model;

        public PermissionViewBuilderDialog(PermissionModel model) {
            this.model = model;
        }

        @Override
        public PermissionListener build(Context context) {
            return DialogOnDeniedPermissionListener.Builder
                    .withContext(context)
                    .withTitle(model.title)
                    .withMessage(model.message)
                    .withButtonText(model.button)
                    .withIcon(model.icon)
                    .build();
        }
    }

    public static class PermissionViewBuilderSnackbar implements PermissionViewBuilder {
        private final PermissionModel model;
        private final Snackbar.Callback callback;


        public PermissionViewBuilderSnackbar(PermissionModel model, Snackbar.Callback callback) {
            this.model = model;
            this.callback = callback;
        }

        @Override
        public PermissionListener build(Context context) {
            return SnackbarOnDeniedPermissionListener.Builder
                    .with(model.view, model.message)
                    .withOpenSettingsButton(model.button)
                    .withCallback(callback)
                    .build();
        }
    }

    public interface PermissionsViewBuilder {
        MultiplePermissionsListener build(Context context);
    }


    public static class PermissionsViewBuilderDialog implements PermissionsViewBuilder {
        private final PermissionModel model;

        public PermissionsViewBuilderDialog(PermissionModel model) {
            this.model = model;
        }

        @Override
        public MultiplePermissionsListener build(Context context) {
            return DialogOnAnyDeniedMultiplePermissionsListener.Builder
                    .withContext(context)
                    .withTitle(model.title)
                    .withMessage(model.message)
                    .withButtonText(model.button)
                    .withIcon(model.icon)
                    .build();
        }
    }

    public static class PermissionsViewBuilderSnackbar implements PermissionsViewBuilder {
        private final PermissionModel model;
        private final Snackbar.Callback callback;


        public PermissionsViewBuilderSnackbar(PermissionModel model, Snackbar.Callback callback) {
            this.model = model;
            this.callback = callback;
        }

        @Override
        public MultiplePermissionsListener build(Context context) {
            return SnackbarOnAnyDeniedMultiplePermissionsListener.Builder
                    .with(model.view, model.message)
                    .withOpenSettingsButton(model.button)
                    .withCallback(callback)
                    .build();
        }
    }


    public static class PermissionModel {
        public int title;
        public int message;
        public View view;
        public int icon;
        public int button;


        /**
         * <h1>use this for dialog permission</h1>
         */
        public PermissionModel(int title, int message, int icon, int button) {
            this.title = title;
            this.message = message;
            this.icon = icon;
            this.button = button;
        }

        /**
         * <h1>use this for snackbar permission</h1>
         */
        public PermissionModel(int message, View view, int button) {
            this.message = message;
            this.view = view;
            this.button = button;
        }

        /**
         * <h1>for all permission view</h1>
         */
        public PermissionModel(int title, int message, View view, int icon, int button) {
            this.title = title;
            this.message = message;
            this.view = view;
            this.icon = icon;
            this.button = button;
        }
    }
}
