package com.big0soft.nearexpireadmin.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.base.CustomeBaseFragment;
import com.big0soft.nearexpireadmin.data.interfacesimpl.SharedPreferenceStoreLocationDataBackup;
import com.big0soft.nearexpireadmin.data.requests.LocationDetailsRequest;
import com.big0soft.nearexpireadmin.databinding.FragmentLocationDetailsBinding;
import com.big0soft.nearexpireadmin.domain.repository.BaseRepository;
import com.big0soft.nearexpireadmin.ui.viewmodel.LocationDetailsViewModel;
import com.big0soft.nearexpireadmin.ui.viewmodel.SessionManagerViewModel;
import com.big0soft.resource.authorization.AuthorizationBasic;
import com.big0soft.resource.authorization.IAuthorization;
import com.big0soft.resource.data.BaseSharedPreferenceDao;
import com.big0soft.resource.data.SharedPreferenceDao;
import com.big0soft.resource.session.SessionManagerImpl;
import com.big0soft.resource.session.UserSession;
import com.big0soft.resource.utils.ViewModelUtils;

import io.reactivex.rxjava3.annotations.NonNull;

public class LocationDetailsFragment extends CustomeBaseFragment<FragmentLocationDetailsBinding> {
    private LocationDetailsViewModel viewModel;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_location_details;
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

        LocationDetailsViewModel.Factory factory1 = new LocationDetailsViewModel.Factory(
                requireActivity().getApplication(),
                new BaseRepository(),
                authorization,
                new SharedPreferenceStoreLocationDataBackup(dao));

        viewModel = ViewModelUtils.instanceViewModel(this, factory1, LocationDetailsViewModel.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, NavController controller) {
        super.onViewCreated(view, savedInstanceState, controller);
        getBinding().setViewModel(viewModel);
        getBinding().setLifecycleOwner(getViewLifecycleOwner());
        viewModel.getLocationDetailsBackupLiveData().observe(getViewLifecycleOwner(), locationDetailsRequest -> {
            getBinding().fragmentLocationCityEditText.setText(locationDetailsRequest.getCityName());
            getBinding().fragmentLocationCountryEditText.setText(locationDetailsRequest.getCountryName());
            getBinding().fragmentLocationStreetEditText.setText(locationDetailsRequest.getStreetName());
        });
        getBinding().fragmentLocationDetailsSubmitBtn.setOnClickListener(v -> {
//            requireActivity().finish();
            viewModel.submitLocationDetails(locationRequest());
        });
        viewModel.getSuccessLocationResponseLiveData().observe(getViewLifecycleOwner(), successLocationResponse -> {
            controller().navigate(R.id.action_locationDetailsFragment_to_contactDetailsFragment);
        });


    }

    private LocationDetailsRequest locationRequest() {
        LocationDetailsRequest locationDetailsRequest = new LocationDetailsRequest();
        locationDetailsRequest.setCityName(getBinding().fragmentLocationCityEditText.getText().toString());
        locationDetailsRequest.setCountryName(getBinding().fragmentLocationCountryEditText.getText().toString());
        locationDetailsRequest.setStreetName(getBinding().fragmentLocationStreetEditText.getText().toString());
        return locationDetailsRequest;
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.backupStoreInfo(locationRequest());
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.restoreStoreInfo();
    }


}