package com.big0soft.nearexpireadmin.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.big0soft.nearexpireadmin.data.requests.ContactDetailsRequest;
import com.big0soft.nearexpireadmin.data.requests.LocationDetailsRequest;
import com.big0soft.nearexpireadmin.data.responses.ContactDetailsResponse;
import com.big0soft.nearexpireadmin.data.responses.LocationDetailsResponse;
import com.big0soft.nearexpireadmin.data.validation.ValidateResult;
import com.big0soft.nearexpireadmin.data.validation.ValidationFactory;
import com.big0soft.nearexpireadmin.domain.enums.AuthProvider;
import com.big0soft.nearexpireadmin.domain.enums.Status;
import com.big0soft.nearexpireadmin.domain.interfaces.DataBackup;
import com.big0soft.nearexpireadmin.domain.repository.BaseRepository;
import com.big0soft.resource.authorization.IAuthorization;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class ContactDetailsViewModel extends AndroidViewModel {

    private final MutableLiveData<ContactDetailsResponse> successContactResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> errorContactResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Status> statusContactResponseLiveData = new MutableLiveData<>();


    private final MutableLiveData<ValidateResult> phoneValidationResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<ValidateResult> whatsappValidationResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<ValidateResult> emailValidationResultLiveData = new MutableLiveData<>();


    private final BaseRepository baseRepository;
    private final IAuthorization authorization;

    private final CompositeDisposable disposable;

    private final DataBackup<ContactDetailsRequest> contactBackup;
    private final MutableLiveData<ContactDetailsRequest> contactDetailsBackupLiveData = new MutableLiveData<>();

    public ContactDetailsViewModel(@NonNull Application application, BaseRepository baseRepository, IAuthorization authorization, DataBackup<ContactDetailsRequest> contactBackup) {
        super(application);
        this.baseRepository = baseRepository;
        disposable = new CompositeDisposable();
        this.authorization = authorization;
        this.contactBackup = contactBackup;
    }

    public void submitContactDetails(ContactDetailsRequest request) {
        if (!isRequestValid(request)) {
            return;
        }
        successContactResponseLiveData.setValue(null);

    }

    private boolean isRequestValid(ContactDetailsRequest request) {
        ValidateResult phoneValidateResult = ValidationFactory.validate(AuthProvider.PHONE_NUMBER, request.getPhone());
        ValidateResult whatsappValidateResult = ValidationFactory.validate(AuthProvider.PHONE_NUMBER, request.getWhatsapp());
        ValidateResult emailValidateResult = ValidationFactory.validate(AuthProvider.EMAIL, request.getEmail());

        if (!phoneValidateResult.isSuccess()) {
            phoneValidationResultLiveData.setValue(phoneValidateResult);
            return false;
        }
        if (!whatsappValidateResult.isSuccess()) {
            whatsappValidationResultLiveData.setValue(whatsappValidateResult);
            return false;
        }

        if (!emailValidateResult.isSuccess()) {
            emailValidationResultLiveData.setValue(emailValidateResult);
            return false;
        }
        return true;
    }


    public void backupContactInfo(ContactDetailsRequest contactDetailsRequest) {
        contactBackup.backup(contactDetailsRequest);

    }

    public void restoreContactBackup() {
        contactDetailsBackupLiveData.setValue(contactBackup.restore());
    }

    public void clearStoreInfo() {
        contactBackup.clean();
    }

    public LiveData<ContactDetailsResponse> getSuccessContactResponseLiveData() {
        return successContactResponseLiveData;
    }

    public LiveData<ValidateResult> getPhoneValidationResultLiveData() {
        return phoneValidationResultLiveData;

    }


    public LiveData<ValidateResult> getWhatsappValidationResultLiveData() {
        return whatsappValidationResultLiveData;

    }

    public LiveData<ValidateResult> getEmailValidationResultLiveData() {
        return emailValidationResultLiveData;

    }

    public LiveData<ContactDetailsRequest> getContactDetailsBackup() {
        return contactDetailsBackupLiveData;
    }

    public void setPhoneValidationResultLiveData(ValidateResult phoneValidationResultLiveData) {
        this.phoneValidationResultLiveData.setValue(phoneValidationResultLiveData);
    }

    public void setWhatsppValidationResultLiveData(ValidateResult whatsappValidationResultLiveData) {
        this.whatsappValidationResultLiveData.setValue(whatsappValidationResultLiveData);
    }

    public void setEmailValidationResultLiveData(ValidateResult emailValidationResultLiveData) {
        this.emailValidationResultLiveData.setValue(emailValidationResultLiveData);
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
        private final DataBackup<ContactDetailsRequest> contactBackup;

        public Factory(Application application, BaseRepository baseRepository, IAuthorization authorization, DataBackup<ContactDetailsRequest> contactBackup) {
            this.application = application;
            this.baseRepository = baseRepository;
            this.authorization = authorization;
            this.contactBackup = contactBackup;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (!modelClass.isAssignableFrom(ContactDetailsViewModel.class)) {
                throw new IllegalArgumentException("Unknown ViewModel class");
            }
            return (T) new ContactDetailsViewModel(application, baseRepository, authorization, contactBackup);
        }
    }
}
