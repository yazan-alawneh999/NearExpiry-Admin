package com.big0soft.nearexpireadmin.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.big0soft.nearexpireadmin.data.requests.LocationDetailsRequest;
import com.big0soft.nearexpireadmin.data.responses.LocationDetailsResponse;
import com.big0soft.nearexpireadmin.data.validation.ValidateResult;
import com.big0soft.nearexpireadmin.data.validation.ValidationFactory;
import com.big0soft.nearexpireadmin.domain.enums.Status;
import com.big0soft.nearexpireadmin.domain.interfaces.DataBackup;
import com.big0soft.nearexpireadmin.domain.repository.BaseRepository;
import com.big0soft.resource.authorization.IAuthorization;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class LocationDetailsViewModel extends AndroidViewModel {

    private final MutableLiveData<LocationDetailsResponse> successLocationResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> errorLocationResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Status> statusLocationResponseLiveData = new MutableLiveData<>();


    private final MutableLiveData<ValidateResult> countryNameValidationResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<ValidateResult> cityNameValidationResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<ValidateResult> streetNameValidationResultLiveData = new MutableLiveData<>();


    private final MutableLiveData<LocationDetailsRequest> locationDetailsBackupLiveData = new MutableLiveData<>();
    private final BaseRepository baseRepository;
    private final IAuthorization authorization;

    private final CompositeDisposable disposable;
    private final DataBackup<LocationDetailsRequest> dataBackup;

    public LocationDetailsViewModel(@NonNull Application application, BaseRepository baseRepository, IAuthorization authorization, DataBackup<LocationDetailsRequest> dataBackup) {
        super(application);
        this.baseRepository = baseRepository;
        disposable = new CompositeDisposable();
        this.authorization = authorization;
        this.dataBackup = dataBackup;
    }

    public void submitLocationDetails(LocationDetailsRequest request) {
        if (!isRequestValid(request)) {
            return;
        }
        successLocationResponseLiveData.setValue(null);

    }

    private boolean isRequestValid(LocationDetailsRequest request) {
        ValidateResult CityNameValidateResult = ValidationFactory.validateStringInput(request.getCityName());
        ValidateResult CountryNameValidateResult = ValidationFactory.validateStringInput(request.getCountryName());
        ValidateResult streetNameValidateResult = ValidationFactory.validateStringInput(request.getStreetName());
        if (!CityNameValidateResult.isSuccess()) {
            cityNameValidationResultLiveData.setValue(CityNameValidateResult);
            return false;
        }
        if (!CountryNameValidateResult.isSuccess()) {
            countryNameValidationResultLiveData.setValue(CountryNameValidateResult);
            return false;
        }
        if (!streetNameValidateResult.isSuccess()) {
            streetNameValidationResultLiveData.setValue(streetNameValidateResult);
            return false;
        }
        return true;
    }


    public LiveData<LocationDetailsResponse> getSuccessLocationResponseLiveData() {
        return successLocationResponseLiveData;
    }

    public LiveData<ValidateResult> getCountryNameValidationResultLiveData() {
        return countryNameValidationResultLiveData;

    }


    public LiveData<ValidateResult> getCityNameValidationResultLiveData() {
        return cityNameValidationResultLiveData;

    }

    public LiveData<ValidateResult> getStreetNameValidationResultLiveData() {
        return streetNameValidationResultLiveData;

    }

    public void setCountryNameValidationResultLiveData(ValidateResult countryNameValidationResultLiveData) {
        this.countryNameValidationResultLiveData.setValue(countryNameValidationResultLiveData);
    }

    public void setCityNameValidationResultLiveData(ValidateResult cityNameValidationResultLiveData) {
        this.cityNameValidationResultLiveData.setValue(cityNameValidationResultLiveData);
    }

    public void setStreetNameValidationResultLiveData(ValidateResult streetNameValidationResultLiveData) {
        this.streetNameValidationResultLiveData.setValue(streetNameValidationResultLiveData);
    }


    public void backupStoreInfo(LocationDetailsRequest locationDetailsRequest) {
        dataBackup.backup(locationDetailsRequest);

    }

    public void restoreStoreInfo() {
        locationDetailsBackupLiveData.setValue(dataBackup.restore());
    }

    public void clearStoreInfo() {
        dataBackup.clean();
    }

    public LiveData<LocationDetailsRequest> getLocationDetailsBackupLiveData() {
        return locationDetailsBackupLiveData;
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

        private final DataBackup<LocationDetailsRequest> dataBackup;

        public Factory(Application application, BaseRepository baseRepository, IAuthorization authorization, DataBackup<LocationDetailsRequest> dataBackup) {
            this.application = application;
            this.baseRepository = baseRepository;
            this.authorization = authorization;
            this.dataBackup = dataBackup;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (!modelClass.isAssignableFrom(LocationDetailsViewModel.class)) {
                throw new IllegalArgumentException("Unknown ViewModel class");
            }
            return (T) new LocationDetailsViewModel(application, baseRepository, authorization, dataBackup);
        }
    }
}
