package com.big0soft.nearexpireadmin.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.base.CustomeBaseFragment;
import com.big0soft.nearexpireadmin.data.interfacesimpl.SharedPreferenceStoreContactDataBackup;
import com.big0soft.nearexpireadmin.data.requests.ContactDetailsRequest;
import com.big0soft.nearexpireadmin.databinding.FragmentContactDetailsBinding;
import com.big0soft.nearexpireadmin.domain.repository.BaseRepository;
import com.big0soft.nearexpireadmin.ui.viewmodel.ContactDetailsViewModel;
import com.big0soft.nearexpireadmin.ui.viewmodel.SessionManagerViewModel;
import com.big0soft.resource.authorization.AuthorizationBasic;
import com.big0soft.resource.authorization.IAuthorization;
import com.big0soft.resource.data.BaseSharedPreferenceDao;
import com.big0soft.resource.data.SharedPreferenceDao;
import com.big0soft.resource.session.SessionManagerImpl;
import com.big0soft.resource.session.UserSession;
import com.big0soft.resource.utils.ViewModelUtils;

import io.reactivex.rxjava3.annotations.NonNull;

public class ContactDetailsFragment extends CustomeBaseFragment<FragmentContactDetailsBinding> {

    private ContactDetailsViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact_details;
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
        ContactDetailsViewModel.Factory factory1 = new ContactDetailsViewModel.Factory(
                requireActivity().getApplication()
                , new BaseRepository()
                , authorization
                , new SharedPreferenceStoreContactDataBackup(dao));

        viewModel = ViewModelUtils.instanceViewModel(this, factory1, ContactDetailsViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, NavController controller) {
        super.onViewCreated(view, savedInstanceState, controller);
        getBinding().setViewModel(viewModel);
        getBinding().setLifecycleOwner(getViewLifecycleOwner());
        getBinding().fragmentContactDetailsSubmitBtn.setOnClickListener(v -> viewModel.submitContactDetails(contactRequest()));
        viewModel.getContactDetailsBackup().observe(getViewLifecycleOwner(),
                contactDetailsRequest -> getBinding().setContactInfo(contactDetailsRequest));
        viewModel.getSuccessContactResponseLiveData().observe(getViewLifecycleOwner(),
                contactDetailsResponse -> controller.navigate(R.id.action_contactDetailsFragment_to_verificationFragment));
    }

    private ContactDetailsRequest contactRequest() {
        ContactDetailsRequest request = new ContactDetailsRequest();
        request.setPhone(getBinding().fragmentContactDetailsPhoneEditText.getText().toString());
        request.setWhatsapp(getBinding().fragmentContactDetailsWhatsAppEditText.getText().toString());
        request.setEmail(getBinding().fragmentContactDetailsEmailEditText.getText().toString());
        return request;
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.backupContactInfo(contactRequest());
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.restoreContactBackup();
    }
}