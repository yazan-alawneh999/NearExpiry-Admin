package com.big0soft.resource.helper;


public class CameraUtils {
    public final static String[] gallery = {"image/png",
            "image/jpg",
            "image/jpeg"};
    public final static String[] files = {"application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .ppt & .pptx
            "application/pdf"};

//    public static AttachmentManager camera(AppCompatActivity appCompatActivity) {
//        return camera(appCompatActivity, null);
//    }
//
//    public static AttachmentManager camera(AppCompatActivity appCompatActivity, Fragment fragment) {
//        return new AttachmentManager.AttachmentBuilder(appCompatActivity) // must pass Context
//                .fragment(fragment) // pass fragment reference if you are in fragment
//                .setUiTitle("Choose File") // title of dialog or bottom sheet
//                .allowMultiple(false) // set true if you want make multiple selection, default is false
//                .asBottomSheet(true) // set true if you need to show selection as bottom sheet, default is as Dialog
//                .setOptionsTextColor(android.R.color.holo_green_light) // change text color
//                .setImagesColor(R.color.cardview_shadow_end_color) // change icon color
//                .hide(HideOption.DOCUMENT) // You can hide any option do you want
//                .setMaxPhotoSize(200000) // Set max  photo size in bytes
//                .galleryMimeTypes(gallery) // mime types for gallery
//                .filesMimeTypes(files) // mime types for files
//                .build(); // Hide any of the three options
//    }
//
//
//    public static ActivityResultLauncher<Intent> registerCamera(AppCompatActivity appCompatActivity, OnCameraRequestResult onCameraRequestResult) {
//        return appCompatActivity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), onCameraRequestResult::onResult);
//    }
//
//    public  interface OnCameraRequestResult {
//        void onResult(ActivityResult result);
//    }
}
