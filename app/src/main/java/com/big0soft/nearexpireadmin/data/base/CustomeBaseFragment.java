package com.big0soft.nearexpireadmin.data.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.big0soft.resource.screen.BaseFragment;

public abstract class CustomeBaseFragment<B extends ViewDataBinding> extends BaseFragment {
    protected B binding;

    public B getBinding() {
        return binding;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return binding.getRoot();
    }

    protected abstract int getLayoutId();

    protected void setClickListener(View view, View.OnClickListener listener) {
        if (view == null) {
            throw new IllegalArgumentException("View cannot be null");
        }

        if (listener == null) {
            throw new IllegalArgumentException("OnClickListener cannot be null");
        }

        view.setOnClickListener(listener);
    }

    /**
     * Retrieves the input text from a given EditText view.
     * This method casts the provided view to an EditText and retrieves the text entered by the user.
     *
     * @param view the EditText view from which to retrieve the input text.
     * @return the text entered by the user as a String.
     */
    protected String getInputText(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View cannot be null");
        }

        if (!(view instanceof android.widget.EditText)) {
            throw new IllegalArgumentException("View must be an instance of EditText");
        }

        android.widget.EditText editText = (android.widget.EditText) view;
        String inputText = editText.getText().toString().trim(); // Trim whitespace

        if (inputText.isEmpty()) {
            // Optionally, log or handle empty input here.
            return "";
        }

        return inputText;
    }
}