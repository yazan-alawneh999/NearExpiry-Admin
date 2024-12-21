package com.big0soft.resource.helper;


import android.util.Log;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDirections;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

public class NavigationUtils {

    public static void navigate(NavController controller, NavDirections directions) {
        if (controller == null) return;
        try {
            controller.navigate(directions);
        } catch (Exception e) {
            Log.e(TAGs.TAG, "navigate: ", e);
        }
    }

    public static Fragment currentFragment(NavHostFragment navHostFragment) {
        return getStackFragment(navHostFragment, 0);
    }


    public static Fragment getStackFragment(NavHostFragment navHostFragment, int index) {
        return navHostFragment.getChildFragmentManager().getFragments().get(index);
    }

    @Nullable
    public static NavDestination currentNavigationDestination(NavController controller) {
        if (controller == null) {
            throw new RuntimeException("controller is null");
        }
        return controller.getCurrentDestination();
    }

    public static void navigate(NavController controller, @IdRes int action) {
        if (controller == null) return;
        try {
            controller.navigate(action);
        } catch (Exception e) {
            Log.e(TAGs.TAG, "navigate: ", e);
        }
    }

    @Nullable
    public static NavGraph currentNavigationParent(NavController controller) {
        if (controller == null) {
            throw new RuntimeException("controller is null");
        }
        return currentNavigationDestination(controller).getParent();
    }

    public static int currentNavigationParentId(NavController controller) {
        NavDestination navDestination = currentNavigationParent(controller);
        return navDestination == null ? -1 : navDestination.getId();
    }
}
