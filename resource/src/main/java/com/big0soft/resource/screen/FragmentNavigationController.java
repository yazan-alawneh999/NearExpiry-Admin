package com.big0soft.resource.screen;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public interface FragmentNavigationController {
    /**
     * @return {@link NavController}
     * @throws IllegalStateException - if called before onCreate
     */
    NavController controller();
    /**
     * @return {@link androidx.navigation.fragment.NavHostFragment}
     * @throws IllegalStateException NavHostFragment id not found
     */
    NavHostFragment navHostFragment();
}
