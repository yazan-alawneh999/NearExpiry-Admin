package com.big0soft.nearexpireadmin.util;

import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.google.android.material.textfield.TextInputLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import in.aabhasjindal.otptextview.ItemView;

public final class ViewUtils {
    public static Matcher<View> hasTextOtpError(final String error){
        return new TypeSafeMatcher<>() {
            @Override
            public void describeTo(Description description) {
            }

            @Override
            protected boolean matchesSafely(View item) {
                if (!(item instanceof TextInputLayout)) {
                    return false;
                }


                CharSequence error = ((TextInputLayout) item).getError();

                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return error.equals(hint);
            }
        };
    }

    public static ViewAction typeOtpText(String stringToBeTyped) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(ItemView.class);
            }

            @Override
            public String getDescription() {
                return "otp text type in view";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ItemView yourCustomView = (ItemView) view;
                yourCustomView.setText(stringToBeTyped);
            }
        };
    }
    public static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<>() {
            @Override
            public void describeTo(Description description) {
            }

            @Override
            protected boolean matchesSafely(View item) {
                if (!(item instanceof TextInputLayout)) {
                    return false;
                }


                CharSequence error = ((TextInputLayout) item).getError();

                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return expectedErrorText.equals(hint);
            }
        };
    }

    public static Matcher<View> nthChildOf(final Matcher<View> parentMatcher, final int childPosition) {
        return new TypeSafeMatcher<>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("position " + childPosition + " of parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (view.getParent() instanceof ViewGroup) {
                    ViewGroup parent = (ViewGroup) view.getParent();
                    return parentMatcher.matches(parent) && parent.getChildAt(childPosition).equals(view);
                }
                return false;
            }
        };
    }

    public static Matcher<View> withOtpText(final String expectedText) {
        return new TypeSafeMatcher<>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("with OTP text: " + expectedText);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    return false;
                }
                String actualText = ((EditText) view).getText().toString();
                return actualText.equals(expectedText);
            }
        };
    }

//    public static Matcher<SnackbarContentLayout> snackBarHasErrorText(final String expectedErrorText) {
//        return new TypeSafeMatcher<>() {
//            @Override
//            public void describeTo(Description description) {
//            }
//
//            @Override
//            protected boolean matchesSafely(SnackbarContentLayout item) {
//
//                SnackbarContentLayout view = (SnackbarContentLayout) item.getView();
//                CharSequence error = view.getMessageView().getText();
//                if (error == null) {
//                    return false;
//                }
//
//                String hint = error.toString();
//
//                return expectedErrorText.equals(hint);
//            }
//        };
//    }

}