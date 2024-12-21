package com.big0soft.nearexpireadmin.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.data.adapter.HomeCategoryAdapter;
import com.big0soft.nearexpireadmin.data.base.CustomeBaseFragment;
import com.big0soft.nearexpireadmin.data.models.HomeCategoryModel;
import com.big0soft.nearexpireadmin.databinding.FragmentHomeBinding;
import com.big0soft.nearexpireadmin.ui.viewmodel.SessionManagerViewModel;
import com.big0soft.resource.authorization.AuthorizationBasic;
import com.big0soft.resource.authorization.IAuthorization;
import com.big0soft.resource.session.SessionManagerImpl;
import com.big0soft.resource.session.UserSession;
import com.big0soft.resource.utils.ViewModelUtils;


import java.util.ArrayList;

import io.reactivex.rxjava3.annotations.NonNull;

public class HomeFragment extends CustomeBaseFragment<FragmentHomeBinding> {

    HomeCategoryAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new HomeCategoryAdapter();

        SessionManagerImpl mSessionManager = new SessionManagerImpl(sharedPreference());
        ViewModelProvider.Factory factory = new SessionManagerViewModel.SessionManagerViewModelFactory(mSessionManager);
        SessionManagerViewModel   mSessionViewModel = ViewModelUtils.instanceViewModel(this, factory, SessionManagerViewModel.class);
        UserSession userSession = mSessionViewModel.userSession();
        IAuthorization authorization =   new AuthorizationBasic(userSession.getToken(), userSession.getPassword());
        Log.i("TAG", "onCreate: "+authorization.authorization());

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, NavController controller) {
        super.onViewCreated(view, savedInstanceState, controller);
        HomeCategoryModel model = new HomeCategoryModel();
        model.setIcon(R.drawable.e_add);
        model.setTitle(R.string.new_product);
        model.setColor(R.color.md_theme_primary);

        HomeCategoryModel model1 = new HomeCategoryModel();
        model1.setIcon(R.drawable.box_3d_50);
        model1.setTitle(R.string.orders);
        model1.setColor(R.color.home_category_order_bg_color);

        HomeCategoryModel model2 = new HomeCategoryModel();
        model2.setIcon(R.drawable.archive);
        model2.setTitle(R.string.all_products);
        model2.setColor(R.color.home_category_all_products_bg_color);


        ArrayList<HomeCategoryModel> list = new ArrayList<>();

        list.add(model);
        list.add(model1);
        list.add(model2);
        adapter.submitList(list);

        binding.fragmentHomeServicesRv.setHasFixedSize(true);
        binding.fragmentHomeServicesRv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.fragmentHomeServicesRv.setAdapter(adapter);
    }


}