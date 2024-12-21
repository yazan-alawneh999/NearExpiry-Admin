package com.big0soft.nearexpireadmin.data.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.github.dhaval2404.imagepicker.ImagePicker;

public class ImagePickerUtil {

    public static ActivityResultLauncher<Intent> registerActivityLauncherForImagePicker(Fragment fragment, ImagePickerCallback callback) {
        return fragment.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri uri = result.getData().getData();
                        if (uri != null) {
                            callback.onImagePicked(uri.toString()); // Delegate to child class
                        } else {
                            callback.onImagePickError("Failed to get image");
                        }
                    } else {
                        callback.onImagePickError("Image picking canceled");
                    }
                }
        );
    }


    /**
     * Utility method to launch the image picker.
     * This method uses the ImagePicker library to launch an intent allowing the user to
     * pick an image, which can be cropped and compressed according to the specified settings.
     * It throws an IllegalStateException if the ActivityResultLauncher has not been initialized.
     */
    public static void pickImage(Fragment fragment, ActivityResultLauncher<Intent> imageLauncher) {
        if (imageLauncher == null) {
            throw new IllegalStateException("ActivityResultLauncher is not initialized.");
        }
        ImagePicker.with(fragment)
                .cropSquare()
                .compress(512)
                .maxResultSize(512, 512)
                .createIntent(intent -> {
                    imageLauncher.launch(intent);
                    return null;
                });
    }


    public interface ImagePickerCallback {
        void onImagePicked(String imageUrl);    // Called when image is successfully picked

        void onImagePickError(String errorMessage);
    }
}