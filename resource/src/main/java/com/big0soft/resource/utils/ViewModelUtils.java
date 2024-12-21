package com.big0soft.resource.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public final class ViewModelUtils {
    public static <T extends ViewModel> T getViewModel(ViewModelProvider provider, Class<T> clazz) {
        return provider.get(clazz);
    }

    public static <T extends ViewModel> ViewModelProvider getProvider(@NonNull ViewModelStoreOwner owner, @Nullable ViewModelProvider.Factory factory) {
        if (factory == null) {
            return new ViewModelProvider(owner);

        }
        return new ViewModelProvider(owner, factory);
    }

    // This method abstracts the creation of ViewModel for a specific NavGraph


    public static <T extends ViewModel> T instanceViewModel(@NonNull ViewModelStoreOwner owner, @Nullable ViewModelProvider.Factory factory, @NonNull Class<T> clazz) {
        return getViewModel(getProvider(owner, factory), clazz);
    }

    public static <T extends ViewModel> T instanceViewModel(@NonNull ViewModelStoreOwner owner, @NonNull Class<T> clazz) {
        return getViewModel(getProvider(owner, null), clazz);
    }


}
