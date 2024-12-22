package com.big0soft.nearexpireadmin.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.big0soft.nearexpireadmin.data.repository.remote.RegisterRepository;
import com.big0soft.nearexpireadmin.data.requests.RegistrationRequest;
import com.big0soft.nearexpireadmin.data.responses.RegistrationResponse;
import com.big0soft.nearexpireadmin.data.validation.ValidateResult;
import com.big0soft.nearexpireadmin.data.validation.ValidationFactory;
import com.big0soft.nearexpireadmin.domain.enums.Status;
import com.big0soft.resource.model.ErrorResponse;
import com.big0soft.resource.session.SessionManager;
import com.big0soft.resource.session.UserSession;
import com.google.gson.Gson;

import java.io.IOException;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.HttpException;
import retrofit2.Response;

public class RegisterViewModel extends AndroidViewModel {
    RegisterRepository mRegisterRepository;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    // common live data for validation
    private final MutableLiveData<ValidateResult> providerValidateResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<ValidateResult> passwordValidateResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<ValidateResult> confirmPasswordValidateResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<RegistrationResponse> registerResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<ErrorResponse> errorResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Status> registerStatusLiveData = new MutableLiveData<>();

    private final SessionManager mSessionManager;


    public RegisterViewModel(@NonNull Application application, RegisterRepository mRegisterRepository, SessionManager mSessionManager) {
        super(application);
        this.mSessionManager = mSessionManager;
        this.mRegisterRepository = mRegisterRepository;
    }

    public void register(RegistrationRequest registrationRequest, String confirmPassword) {
        if (!isRequestValid(registrationRequest, confirmPassword)) {
            return;
        }
        compositeDisposable.add(mRegisterRepository.register(registrationRequest)
//                .filter(Objects::isNull)
                .doAfterSuccess(registrationResponse -> saveSession(new UserSession(registrationResponse.getToken(),
                        registrationResponse.getExpireAt(), registrationRequest.password())))
                .doOnSubscribe(disposable -> registerStatusLiveData.postValue(Status.LOADING))
                .subscribe(registrationResponse -> {
                            registerStatusLiveData.postValue(Status.SUCCESS);
                            registerResponseLiveData.postValue(registrationResponse);
                        },
                        throwable -> {
                            registerStatusLiveData.postValue(Status.ERROR);
                            ErrorResponse errorResponse = new ErrorResponse();
                            errorResponse.setError(throwable.getMessage());
                            errorResponseLiveData.postValue(errorResponse);
                        }));


    }

    public LiveData<Status> getRegisterStatusLiveData() {
        return registerStatusLiveData;
    }

    public void setRegisterStatusLiveData(Status status) {
        registerStatusLiveData.postValue(status);
    }

    private boolean isRequestValid(RegistrationRequest registrationRequest, String confirmPassword) {
        ValidateResult validate = ValidationFactory.validate(registrationRequest.authProvider(), registrationRequest.provider());
        if (!validate.isSuccess()) {
            providerValidateResultLiveData.postValue(validate);
            return false;
        }
        validate = ValidationFactory.validatePassword( registrationRequest.password());
        if (!validate.isSuccess()) {
            passwordValidateResultLiveData.postValue(validate);
            return false;
        }



        validate = ValidationFactory.validateConfirmPassword(confirmPassword, registrationRequest.password());
        if (!validate.isSuccess()) {
            confirmPasswordValidateResultLiveData.postValue(validate);
            return false;
        }

        return true;
    }

    private void saveSession(UserSession userSession) {
        mSessionManager.saveSession(userSession.toJson());
        mSessionManager.sessionExpireAt(userSession.getExpireAt());
    }

    public LiveData<RegistrationResponse> getRegisterResponseLiveData() {
        return registerResponseLiveData;
    }

    public LiveData<ErrorResponse> getErrorResponseLiveData() {
        return errorResponseLiveData;
    }

    public LiveData<ValidateResult> getProviderValidateResultLiveData() {
        return providerValidateResultLiveData;
    }

    public void setProviderError(ValidateResult validateResult) {
        providerValidateResultLiveData.postValue(validateResult);
    }


    public void setPasswordError(ValidateResult validateResult) {
        passwordValidateResultLiveData.postValue(validateResult);
    }

    public void setConfirmPasswordError(ValidateResult validateResult) {
        confirmPasswordValidateResultLiveData.postValue(validateResult);
    }

    public LiveData<ValidateResult> getPasswordValidateResultLiveData() {
        return passwordValidateResultLiveData;
    }

    public LiveData<ValidateResult> getConfirmPasswordValidateResultLiveData() {
        return confirmPasswordValidateResultLiveData;
    }


    private void handleLoginError(Throwable throwable) {
        ErrorResponse errorResponse = new ErrorResponse();
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            try { // 5. Handle potential parsing errors
                String jsonError = Response.error(httpException.code(), httpException.response().errorBody()).errorBody().string();
                errorResponse = new Gson().fromJson(jsonError, ErrorResponse.class); // 6. Use Gson for parsing
            } catch (IOException e) {
                // Log the parsing error
                errorResponse.setMessage("Error parsing error response");
            }
        } else if (throwable instanceof IOException) {
            errorResponse.setMessage("Connection error");
        } else {
            errorResponse.setMessage("Unknown error occurred"); // 7. Provide a generic message
        }
        errorResponseLiveData.postValue(errorResponse);
    }

    public static class Factory implements ViewModelProvider.Factory {

        private final SessionManager mSessionManager;
        private final RegisterRepository registerRepository;
        private final Application application;

        public Factory(@NonNull Application application, RegisterRepository registerRepository, SessionManager sessionManager) {
            this.application = application;
            this.registerRepository = registerRepository;
            this.mSessionManager = sessionManager;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (!modelClass.isAssignableFrom(RegisterViewModel.class)) {
                throw new IllegalArgumentException("Unknown ViewModel class");
            }
            return (T) new RegisterViewModel(application, registerRepository, mSessionManager);
        }
    }
}
