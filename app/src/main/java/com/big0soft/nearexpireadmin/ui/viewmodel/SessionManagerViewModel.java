package com.big0soft.nearexpireadmin.ui.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.big0soft.resource.helper.TAGs;
import com.big0soft.resource.session.SessionManager;
import com.big0soft.resource.session.UserSession;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class SessionManagerViewModel extends ViewModel {
    private final MutableLiveData<Boolean> userHasSessionLivedata = new MutableLiveData<>();
    private final SessionManager mSessionManager;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public SessionManagerViewModel(SessionManager mSessionManager) {
        this.mSessionManager = mSessionManager;
    }

    public LiveData<Boolean> userHasSessionLivedata() {
        return userHasSessionLivedata;
    }

    public void checkSession() {
        compositeDisposable.add(Observable.interval(5, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    userHasSessionLivedata.postValue(mSessionManager.hasSession());
                }));
    }


    public UserSession userSession() {
        Log.i(TAGs.TAG(getClass()), "userSession: " + mSessionManager.userSession());
//        if (!mSessionManager.hasSession()) {
//            userHasSessionLivedata.setValue(false);
//            return null;
//        }
        String session = mSessionManager.userSession();
        return new UserSession("exists@big0soft.com", 1, "123456");
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public static class SessionManagerViewModelFactory implements ViewModelProvider.Factory {
        private final SessionManager mSessionManager;

        public SessionManagerViewModelFactory(SessionManager mSessionManager) {
            this.mSessionManager = mSessionManager;

        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new SessionManagerViewModel(mSessionManager);
        }
    }
}