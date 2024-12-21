package com.big0soft.nearexpireadmin.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.requests.StoreSetupRequest;
import com.big0soft.nearexpireadmin.data.requests.StoreSetupRequest;
import com.big0soft.nearexpireadmin.data.responses.StoreSetupResponse;
import com.big0soft.nearexpireadmin.data.validation.ValidateResult;
import com.big0soft.nearexpireadmin.data.validation.ValidationFactory;
import com.big0soft.nearexpireadmin.domain.enums.Status;
import com.big0soft.nearexpireadmin.domain.interfaces.DataBackup;
import com.big0soft.nearexpireadmin.domain.repository.BaseRepository;
import com.big0soft.resource.authorization.IAuthorization;


import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class StoreSetupViewModel extends AndroidViewModel {

    private final MutableLiveData<StoreSetupResponse> successStoreSetupLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> errorStoreSetupLiveData = new MutableLiveData<>();
    private final MutableLiveData<Status> statusStoreSetupLiveData = new MutableLiveData<>();


    private final MutableLiveData<ValidateResult> nameValidationResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<ValidateResult> descriptionValidationResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<ValidateResult> imageLogoValidationResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<ValidateResult> imageBackgroundValidationResultLiveData = new MutableLiveData<>();

    private final MutableLiveData<String> imageLogoLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> imageBackgroundLiveData = new MutableLiveData<>();

    private final MutableLiveData<StoreSetupRequest> StoreSetupRequestBackupLiveData = new MutableLiveData<>();



    private final BaseRepository baseRepository;
    private final IAuthorization authorization;

    private final CompositeDisposable disposable;
    private final DataBackup<StoreSetupRequest> storeBackup;

    public StoreSetupViewModel(@NonNull Application application, BaseRepository baseRepository, IAuthorization authorization, DataBackup<StoreSetupRequest> dataBackup) {
        super(application);
        this.baseRepository = baseRepository;
        disposable = new CompositeDisposable();
        this.authorization = authorization;
        this.storeBackup = dataBackup;
    }


    public void submitStoreSetup(StoreSetupRequest request) {
        if (!isRequestValid(request)) {
            return;
        }
    }

    public LiveData<StoreSetupResponse> getSuccessStoreSetupLiveData() {
        return successStoreSetupLiveData;
    }

    public LiveData<Integer> getErrorStoreSetupLiveData() {
        return errorStoreSetupLiveData;
    }

    public LiveData<Status> getStatusStoreSetupLiveData() {
        return statusStoreSetupLiveData;
    }
    public void setSuccessStoreSetupLiveData(StoreSetupResponse storeSetupResponse) {
        successStoreSetupLiveData.setValue(storeSetupResponse);

    }
    public void setErrorStoreSetupLiveData(Integer error) {
        errorStoreSetupLiveData.setValue(error);
    }

    private boolean isRequestValid(StoreSetupRequest request) {
        ValidateResult nameValidateResult = ValidationFactory.validateStringInput(request.getName());
        ValidateResult descriptionValidateResult = ValidationFactory.validateStringInput(request.getDescription());
        ValidateResult imageLogoValidateResult = ValidationFactory.validateStringInput(request.getImageLogo());
        ValidateResult imageBackgroundValidateResult = ValidationFactory.validateStringInput(request.getBackgroundImage());
        if (!nameValidateResult.isSuccess()) {
            nameValidationResultLiveData.setValue(nameValidateResult);
            return false;
        }
        if (!descriptionValidateResult.isSuccess()) {
            descriptionValidationResultLiveData.setValue(descriptionValidateResult);
            return false;
        }
        if (!imageLogoValidateResult.isSuccess()) {
            imageLogoValidationResultLiveData.setValue(new ValidateResult(false,R.string.logo_image_required));
            return false;
        }
        if (!imageBackgroundValidateResult.isSuccess()) {
            imageBackgroundValidationResultLiveData.setValue(new ValidateResult(false,R.string.image_background_required));
            return false;
        }
        return true;
    }


    public void setImageLogoLiveData(String imageLogo) {
        imageLogoLiveData.setValue(imageLogo);
    }

    public void setImageBackgroundLiveData(String imageBackground) {
        imageBackgroundLiveData.setValue(imageBackground);

    }

    public LiveData<String> getImageLogoLiveData() {
        return imageLogoLiveData;
    }

    public LiveData<String> getImageBackgroundLiveData() {
        return imageBackgroundLiveData;
    }

    public void setNameValidationResultLiveData(ValidateResult nameValidateResult) {
        nameValidationResultLiveData.setValue(nameValidateResult);
    }

    public void setDescriptionValidationResultLiveData(ValidateResult descriptionValidateResult) {
        descriptionValidationResultLiveData.setValue(descriptionValidateResult);
    }

    public void setImageLogoValidationResultLiveData(ValidateResult imageLogoValidateResult) {
        imageLogoValidationResultLiveData.setValue(imageLogoValidateResult);
    }

    public void setImageBackgroundValidationResultLiveData(ValidateResult imageBackgroundValidateResult) {
        imageBackgroundValidationResultLiveData.setValue(imageBackgroundValidateResult);
    }

    public LiveData<ValidateResult> getNameValidationResultLiveData() {
        return nameValidationResultLiveData;
    }

    public LiveData<ValidateResult> getDescriptionValidationResultLiveData() {
        return descriptionValidationResultLiveData;
    }

    public LiveData<ValidateResult> getImageLogoValidationResultLiveData() {
        return imageLogoValidationResultLiveData;
    }

    public LiveData<ValidateResult> getImageBackgroundValidationResultLiveData() {
        return imageBackgroundValidationResultLiveData;
    }


    public void backupStoreInfo(StoreSetupRequest StoreSetupRequest) {
        storeBackup.backup(StoreSetupRequest);

    }

    public void restoreStoreInfo() {
        StoreSetupRequestBackupLiveData.setValue(storeBackup.restore());
    }

    public void clearStoreInfo() {
        storeBackup.clean();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    /**
     * @noinspection unchecked
     */
    public static class Factory implements ViewModelProvider.Factory {
        private final Application application;
        private final BaseRepository baseRepository;
        private final IAuthorization authorization;
        private final DataBackup<StoreSetupRequest> dataBackup;

        public Factory(Application application, BaseRepository baseRepository, IAuthorization authorization, DataBackup<StoreSetupRequest> dataBackup) {
            this.application = application;
            this.baseRepository = baseRepository;
            this.authorization = authorization;
            this.dataBackup = dataBackup;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (!modelClass.isAssignableFrom(StoreSetupViewModel.class)) {
                throw new IllegalArgumentException("Unknown ViewModel class");
            }
            return (T) new StoreSetupViewModel(application, baseRepository, authorization, dataBackup);
        }
    }
}
