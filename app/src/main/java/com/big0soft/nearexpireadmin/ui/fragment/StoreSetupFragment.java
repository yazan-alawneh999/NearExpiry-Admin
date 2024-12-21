package com.big0soft.nearexpireadmin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.base.CustomeBaseFragment;
import com.big0soft.nearexpireadmin.data.interfacesimpl.SharedPreferenceStoreSetupDataBackup;
import com.big0soft.nearexpireadmin.data.requests.StoreSetupRequest;
import com.big0soft.nearexpireadmin.data.util.ImagePickerUtil;
import com.big0soft.nearexpireadmin.databinding.FragmentSotreSetupBinding;
import com.big0soft.nearexpireadmin.domain.repository.BaseRepository;
import com.big0soft.nearexpireadmin.ui.viewmodel.SessionManagerViewModel;
import com.big0soft.nearexpireadmin.ui.viewmodel.StoreSetupViewModel;
import com.big0soft.resource.authorization.AuthorizationBasic;
import com.big0soft.resource.authorization.IAuthorization;
import com.big0soft.resource.data.BaseSharedPreferenceDao;
import com.big0soft.resource.data.SharedPreferenceDao;
import com.big0soft.resource.session.SessionManagerImpl;
import com.big0soft.resource.session.UserSession;
import com.big0soft.resource.utils.ViewModelUtils;

import io.reactivex.rxjava3.annotations.NonNull;

public class StoreSetupFragment extends CustomeBaseFragment<FragmentSotreSetupBinding> implements ImagePickerUtil.ImagePickerCallback {
    private StoreSetupViewModel viewModel;
    private String imageLogo;
    private String imageBackground;
    private ActivityResultLauncher<Intent> imageLogoPickerLauncher;
    private ActivityResultLauncher<Intent> imageBackgroundPickerLauncher;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sotre_setup;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SessionManagerImpl mSessionManager = new SessionManagerImpl(sharedPreference());
        ViewModelProvider.Factory factory = new SessionManagerViewModel.SessionManagerViewModelFactory(mSessionManager);
        SessionManagerViewModel mSessionViewModel = ViewModelUtils.instanceViewModel(this, factory, SessionManagerViewModel.class);
        UserSession userSession = mSessionViewModel.userSession();
        IAuthorization authorization = new AuthorizationBasic(userSession.getToken(), userSession.getPassword());
        Log.i("TAG", "onCreate: " + authorization.authorization());

        SharedPreferenceDao dao = new BaseSharedPreferenceDao(requireContext());
        StoreSetupViewModel.Factory factory1 = new StoreSetupViewModel.Factory(requireActivity().getApplication(), new BaseRepository(), authorization, new SharedPreferenceStoreSetupDataBackup(dao));
        viewModel = ViewModelUtils.instanceViewModel(this, factory1, StoreSetupViewModel.class);

        imageLogoPickerLauncher = ImagePickerUtil.registerActivityLauncherForImagePicker(this, this);
        imageBackgroundPickerLauncher = ImagePickerUtil.registerActivityLauncherForImagePicker(this, new ImagePickerUtil.ImagePickerCallback() {
            @Override
            public void onImagePicked(String imageUrl) {
                if (imageUrl != null) {
                    imageBackground = imageUrl;
                    viewModel.setImageBackgroundLiveData(imageBackground);
                }
            }

            @Override
            public void onImagePickError(String errorMessage) {
                snackbar(errorMessage).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, NavController controller) {
        super.onViewCreated(view, savedInstanceState, controller);
        getBinding().setViewModel(viewModel);
        getBinding().setLifecycleOwner(getViewLifecycleOwner());
        getBinding().fragmentSetupNextBtn.setOnClickListener(v -> viewModel.submitStoreSetup(storeSetupRequest()));
        viewModel.getSuccessStoreSetupLiveData().observe(getViewLifecycleOwner(), storeSetupResponse -> {
            controller.navigate(R.id.action_storeSetupFragment_to_locationDetailsFragment);
        });

        viewModel.getImageLogoValidationResultLiveData().observe(getViewLifecycleOwner(), result -> {
            snackbar(result.getMessage()).show();
        });
        viewModel.getImageBackgroundValidationResultLiveData().observe(getViewLifecycleOwner(), result -> {
            snackbar(result.getMessage()).show();
        });

        getBinding().fragmentStoreSetupLogoImageView.setOnClickListener(v -> ImagePickerUtil.pickImage(this, imageLogoPickerLauncher));
        getBinding().fragmentStoreSetupBackgroundImageView.setOnClickListener(v -> ImagePickerUtil.pickImage(this, imageBackgroundPickerLauncher));

    }

    private StoreSetupRequest storeSetupRequest() {

        StoreSetupRequest request = new StoreSetupRequest();
        request.setName(getBinding().fragmentStoreSetupNameEditText.getText().toString());
        request.setDescription(getBinding().fragmentStoreSetupDescEditText.getText().toString());
        request.setImageLogo(imageLogo);
        request.setBackgroundImage(imageBackground);
        return request;
    }


    public void setViewModel(StoreSetupViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.backupStoreInfo(storeSetupRequest());

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.restoreStoreInfo();
    }

    @Override
    public void onImagePicked(String imageUrl) {

        if (imageUrl != null) {
            imageLogo = imageUrl;
            viewModel.setImageLogoLiveData(imageUrl);

        }
    }

    @Override
    public void onImagePickError(String errorMessage) {
        snackbar(errorMessage).show();

    }
}

