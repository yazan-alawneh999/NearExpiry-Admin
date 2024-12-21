package com.big0soft.resource.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

public class ImageUtils {
    public static String saveImageToFile(Context context, Bitmap bitmap) throws IOException {
        return saveImageToFile(context, bitmap, Bitmap.CompressFormat.PNG);
    }

    public static String saveImageToFile(Context context, Bitmap bitmap, Bitmap.CompressFormat imageType) throws IOException {
        String filename = GenerateRandom.generateRandomName();
        String path = context.getApplicationContext().getFilesDir().getPath();
        OutputStream fOut = null;
        File file = new File(path, filename);
        if (!file.exists()) {
            file.mkdirs();
        }
        String fullPath = filename + "." + imageType.name().toLowerCase(Locale.ROOT);
        File file2 = new File(file, fullPath);
        fOut = new FileOutputStream(file2);
        bitmap.compress(imageType, 100, fOut);
        fOut.flush();
        fOut.close();
        return fullPath;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static String saveImageToFile(Uri imageUri, Context context, String name, int mode) throws IOException {
        InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
        FileUtils.copy(inputStream, context.openFileOutput(name, mode));
        return name;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static String saveImageToFile(File file, File path) throws IOException {
        org.apache.commons.io.FileUtils.copyFile(file, path);
        return "name";
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static String saveImageToFile(Uri imageUri, Context context, int mode) throws IOException {
        return saveImageToFile(imageUri, context, GenerateRandom.generateRandomName(), mode);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static String saveImageToFile(Uri imageUri, Context context) throws IOException {
        String name = GenerateRandom.generateRandomName();
        Log.d(TAGs.TAG, "saveImageToFile: " + name);
        return saveImageToFile(imageUri, context, "name.png", Context.MODE_APPEND);
    }

    public static String saveImageToFile(byte[] data) throws IOException, CreateImageFileException {
        OutputStream out;
        String root = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        File createDir = new File(root + "Folder Name" + File.separator);
        if (!createDir.exists()) {
            createDir.mkdir();
        }
        String fullPath = root + "images/" + GenerateRandom.generateRandomName() + ".png";
        File file = new File(fullPath);
        if (!file.createNewFile()) {
            throw new CreateImageFileException(fullPath);
        }
        out = new FileOutputStream(file);
        out.write(data);
        out.close();
        return fullPath;
    }

}
