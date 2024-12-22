package com.big0soft.nearexpireadmin.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.repository.remote.LoginRepository;
import com.big0soft.nearexpireadmin.data.requests.LoginRequest;
import com.big0soft.nearexpireadmin.data.responses.LoginResponse;
import com.big0soft.nearexpireadmin.data.validation.ValidateResult;
import com.big0soft.nearexpireadmin.data.validation.ValidationFactory;
import com.big0soft.nearexpireadmin.domain.enums.AuthProvider;
import com.big0soft.nearexpireadmin.domain.enums.Status;
import com.big0soft.resource.model.ErrorResponse;
import com.big0soft.resource.session.SessionManager;
import com.big0soft.resource.session.UserSession;

import java.io.IOException;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import retrofit2.HttpException;

public class LoginViewModel extends AndroidViewModel {

    private static final String TAG = "yazan";
    private final LoginRepository mLoginRepository;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    // common live data for all view models
    private final MutableLiveData<ValidateResult> providerValidateResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<ValidateResult> passwordValidateResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<ValidateResult> confirmPasswordValidateResultLiveData = new MutableLiveData<>();
    private final MutableLiveData<LoginResponse> loginResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<ErrorResponse> errorResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Status> loginStatusLiveData = new MutableLiveData<>();

    private final SessionManager mSessionManager;


    public LoginViewModel(Application application, SessionManager mSessionManager, LoginRepository mLoginRepository) {
        super(application);
        this.mSessionManager = mSessionManager;
        this.mLoginRepository = mLoginRepository;
    }


    public void login(LoginRequest loginRequest) {
        if (!isRequestValid(loginRequest)) {
            return;
        }
        compositeDisposable.add(mLoginRepository.login(loginRequest)
//                .filter(Objects::isNull)
                .doAfterSuccess(loginResponse -> saveSession(new UserSession(loginResponse.getToken(),
                        loginResponse.getExpireAt(), loginRequest.getPassword()))
                )
                .doOnSubscribe(disposable -> loginStatusLiveData.postValue(Status.LOADING))
                .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                            loginStatusLiveData.postValue(Status.SUCCESS);
                            loginResponseLiveData.postValue(loginResponse);
                        },
                        throwable -> {
                            loginStatusLiveData.postValue(Status.ERROR);
                            handleLoginError(throwable);
                        }));
    }

    // parent request validation with  shared method request valid
    private boolean isRequestValid(LoginRequest loginRequest) {
        ValidateResult validate = ValidationFactory.validate(loginRequest.getAuthProvider(), loginRequest.getProvider());
        if (!validate.isSuccess()) {
            providerValidateResultLiveData.setValue(validate);
            return false;
        }
        validate = ValidationFactory.validatePassword( loginRequest.getPassword());
        if (!validate.isSuccess()) {
            passwordValidateResultLiveData.setValue(validate);
            return false;
        }
        return true;
    }

    public LiveData<Status> getLoginStatusLiveData() {
        return loginStatusLiveData;
    }

    private void saveSession(UserSession userSession) {
        mSessionManager.saveSession(userSession.toJson());
        mSessionManager.sessionExpireAt(userSession.getExpireAt());
    }

    public void loginBySession() {
        if (!mSessionManager.hasSession()) {
            return;
        }
        String session = mSessionManager.userSession();
        UserSession userSession = (UserSession) new UserSession().fromJson(session);
        LoginRequest loginRequest = new LoginRequest(
                userSession.getToken(),
                userSession.getPassword(),
                AuthProvider.EMAIL);
        login(loginRequest);
    }


    public LiveData<LoginResponse> getLoginResponseLiveData() {
        return loginResponseLiveData;
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
                int code = httpException.code();

                if (code == 401) {
                    errorResponse.setError("Wrong email or password");
                } else {
                    String jsonError = Objects.requireNonNull(Objects.requireNonNull(httpException.response()).errorBody()).string();
                    errorResponse.setError(getApplication().getString(R.string.no_user_found));

//                    errorResponse = new Gson().fromJson(jsonError, ErrorResponse.class);
                }
//                errorResponse = new Gson().fromJson(jsonError, ErrorResponse.class); // 6. Use Gson for parsing
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

    /**
     * @noinspection unchecked
     */
    public static class Factory implements ViewModelProvider.Factory {

        private final SessionManager mSessionManager;
        private final Application application;
        private final LoginRepository mLoginRepository;

        public Factory(@NonNull Application application, SessionManager sessionManager, LoginRepository mLoginRepository) {
            this.application = application;
            this.mSessionManager = sessionManager;
            this.mLoginRepository = mLoginRepository;
        }


        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (!modelClass.isAssignableFrom(LoginViewModel.class)) {
                throw new IllegalArgumentException("Unknown ViewModel class");
            }

            return (T) new LoginViewModel(application, mSessionManager, mLoginRepository);
        }
    }
}
