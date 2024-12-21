package com.big0soft.nearexpireadmin.ui.fragment;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.big0soft.nearexpireadmin.util.ViewUtils.hasTextInputLayoutErrorText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.mock;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.ui.activities.StoreRegistrationActivity;
import com.big0soft.nearexpireadmin.ui.viewmodel.StoreSetupViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

@RunWith(AndroidJUnit4.class)
public class StoreSetupFragmentTest {
    @Rule
    public ActivityScenarioRule<StoreRegistrationActivity> activityScenarioRule = new ActivityScenarioRule<>(StoreRegistrationActivity.class);
    @Rule
   public  InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    Context appContext = ApplicationProvider.getApplicationContext();


    @Test
    public void emptyStoreName_shouldDisplayError()  {
        onView(withId(R.id.fragmentStoreSetup)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentSetupNextBtn), isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.fragmentStoreSetupNameInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.empty_filed))));


    }

    @Test
    public void emptyStoreDesc_shouldDisplayError()  {
        onView(withId(R.id.fragmentStoreSetup)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentStoreSetupNameEditText), isDisplayed()))
                .perform(typeText("test"));
        onView(allOf(withId(R.id.fragmentSetupNextBtn), isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.fragmentStoreSetupDescInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.empty_filed))));


    }

    @Test
    public void emptyStoreImageLogo_shouldDisplayError()  {
        onView(withId(R.id.fragmentStoreSetup)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentStoreSetupNameEditText), isDisplayed()))
                .perform(typeText("test"));
        onView(allOf(withId(R.id.fragmentStoreSetupDescEditText), isDisplayed()))
                .perform(typeText("test"));
        onView(allOf(withId(R.id.fragmentSetupNextBtn), isDisplayed()))
                .perform(click());

        onView(allOf(withText(appContext.getString(R.string.logo_image_required)),isDisplayed()))
                .check(matches(isDisplayed()));

    }
}