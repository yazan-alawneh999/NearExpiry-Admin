package com.big0soft.nearexpireadmin.ui.fragment;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.big0soft.nearexpireadmin.util.ViewUtils.hasTextInputLayoutErrorText;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Mockito.mock;

import android.app.Activity;
import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.MutableLiveData;
import androidx.test.core.app.ActivityScenario;
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
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    Context appContext = ApplicationProvider.getApplicationContext();


    @Test
    public void emptyStoreName_shouldDisplayError() {
        onView(withId(R.id.fragmentStoreSetup)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentSetupNextBtn), isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.fragmentStoreSetupNameInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.empty_filed))));


    }

    @Test
    public void emptyStoreDesc_shouldDisplayError() {
        onView(withId(R.id.fragmentStoreSetup)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentStoreSetupNameEditText), isDisplayed()))
                .perform(typeText("test"));

        onView(allOf(withId(R.id.fragmentSetupNextBtn), isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.fragmentStoreSetupDescInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.empty_filed))));


    }

    @Test
    public void emptyStoreImageLogo_shouldDisplayError() {
        onView(withId(R.id.fragmentStoreSetup)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentStoreSetupNameEditText), isDisplayed()))
                .perform(typeText("test"));
        onView(allOf(withId(R.id.fragmentStoreSetupDescEditText), isDisplayed()))
                .perform(typeText("test"));
        onView(allOf(withId(R.id.fragmentSetupNextBtn), isDisplayed()))
                .perform(click());

        onView(allOf(withText(appContext.getString(R.string.logo_image_required)), isDisplayed()))
                .check(matches(isDisplayed()));

    }

    @Test
    public void testStoreSetupBackupRestore() throws InterruptedException {
        onView(withId(R.id.fragmentStoreSetup))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.fragmentStoreSetupNameEditText), isDisplayed())).perform(typeText("test2"));
        pressBack();
        Thread.sleep(2000);
        ActivityScenario.launch(StoreRegistrationActivity.class);
        onView(withId(R.id.fragmentStoreSetup))
                .check(matches(isDisplayed()));
        onView(withId(R.id.fragmentStoreSetupNameEditText))
                .check(matches(withText("test2")));
    }

    @Test
    public void testStoreSetupBackupRestoreWithUnExpectedFinish() throws InterruptedException {
        onView(withId(R.id.fragmentStoreSetup))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.fragmentStoreSetupNameEditText), isDisplayed())).perform(typeText("test2"));
        activityScenarioRule.getScenario().onActivity(Activity::finish);
        Thread.sleep(2000);
        ActivityScenario.launch(StoreRegistrationActivity.class);
        onView(withId(R.id.fragmentStoreSetup))
                .check(matches(isDisplayed()));
        onView(withId(R.id.fragmentStoreSetupNameEditText))
                .check(matches(withText("test2")));
    }

    @Test
    public void testOverwriteStoreSetupBackupRestore() throws InterruptedException {
        onView(withId(R.id.fragmentStoreSetup))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.fragmentStoreSetupNameEditText), isDisplayed())).perform(typeText("test2"));
        pressBack();
        Thread.sleep(2000);
        ActivityScenario.launch(StoreRegistrationActivity.class);
        onView(withId(R.id.fragmentStoreSetup))
                .check(matches(isDisplayed()));
        onView(withId(R.id.fragmentStoreSetupNameEditText)) // Target the EditText
                .perform(replaceText(""));
        onView(withId(R.id.fragmentStoreSetupNameEditText))
                .perform(typeText("test3"));
        pressBack();
        Thread.sleep(2000);
        ActivityScenario.launch(StoreRegistrationActivity.class);
        onView(withId(R.id.fragmentStoreSetup))
                .check(matches(isDisplayed()));
        onView(withId(R.id.fragmentStoreSetupNameEditText))
                .check(matches(withText("test3")));

    }
}