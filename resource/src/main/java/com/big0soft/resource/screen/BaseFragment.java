package com.big0soft.resource.screen;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.big0soft.resource.data.SharedPreferenceDao;
import com.big0soft.resource.helper.FragmentsHelpers;
import com.big0soft.resource.helper.NavigationUtils;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * <h1>custom fragment</h1>
 * in this fragment you have many Option
 *
 * @see #activityNavigationCompat()
 * @see #checkPermission(String)
 * @see #checkPermission(List)
 * @see #application()
 * @see #snackbar(Object)
 * @see #navHostFragment()
 * @see #onClick(View)
 * @see #onClick(int)
 * @see #onViewCreated(View, Bundle, NavController)
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener, FragmentNavigationController {
    private NavController controller;

    @Override
    @CallSuper
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        controller = Navigation.findNavController(view);
        onViewCreated(view, savedInstanceState, controller);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, NavController controller) {

    }


    @Override
    public void onClick(View v) {
        onClick(v.getId());
    }


    public boolean checkPermission(String permission) {
        return checkPermission(List.of(permission)).equals("null");
//        return ContextCompat.checkSelfPermission(requireContext(), permission) == PackageManager.PERMISSION_GRANTED;
    }

    public String checkPermission(List<String> permission) {
        for (String s : permission) {
            int checkResult = ContextCompat.checkSelfPermission(requireContext(), s);
            if (checkResult == PackageManager.PERMISSION_DENIED) {
                return s;
            }
        }
        return "null";
    }


    /**
     * @param id view id
     */
    public void onClick(int id) {

    }

    /**
     * @return default application class
     * if you not have class application in you project
     */
    public final Application getApplication() {
        return requireActivity().getApplication();
    }

    /**
     * @return singleton object from shared preference
     * @see FragmentsHelpers
     */
    public SharedPreferenceDao sharedPreference() {
        return FragmentsHelpers.sharedPreference(requireContext());
    }

    /**
     * @return controller instance
     * @throws NullPointerException if use it before {@link #onViewCreated(View, Bundle)}
     * @throws NullPointerException if dose not have nav host
     */
    @Override
    public NavController controller() {
        return controller;
    }

    public void controller(int action) {
        NavigationUtils.navigate(controller, action);
    }

    public void controller(NavDirections action) {
        NavigationUtils.navigate(controller, action);
    }


    /**
     * @param <T> type of activity should extend from {@link ActivityNavigationCompat} and implements {@link FragmentNavigationController}
     * @return activity instance
     * @throws ClassCastException if activity not extend from {@link ActivityNavigationCompat} or implements {@link FragmentNavigationController}
     */
    public <T extends ActivityNavigationCompat & FragmentNavigationController> T activityNavigationCompat() throws ClassCastException {
        if (!(requireActivity() instanceof ActivityNavigationCompat)) {
            throw new ClassCastException(requireActivity().getIntent().getComponent().getClassName() + " activity it not extend from ActivityNavigationCompat");
        }
        if (!(requireActivity() instanceof FragmentNavigationController)) {
            throw new ClassCastException(requireActivity().getIntent().getComponent().getClassName() + " activity it not extend from FragmentNavigationController");
        }
        return (T) (ActivityNavigationCompat) requireActivity();
    }

    @Override
    public NavHostFragment navHostFragment() {
        return activityNavigationCompat().navHostFragment();
    }

    /**
     * @param <T> if you have custom application class
     * @return custom application class
     */
    public <T extends Application> T application() {
        return ((T) requireActivity().getApplication());
    }

    /**
     * @param message snackbar message
     * @return snackbar object
     * @see #snackbar(Object)
     */
    public Snackbar snackbar(Object message) {
        return activityNavigationCompat().snackbar(message);
    }


}
