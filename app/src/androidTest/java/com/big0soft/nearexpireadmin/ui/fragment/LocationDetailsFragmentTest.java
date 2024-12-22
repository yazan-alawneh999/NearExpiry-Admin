package com.big0soft.nearexpireadmin.ui.fragment;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.big0soft.nearexpireadmin.util.ViewUtils.hasTextInputLayoutErrorText;
import static org.hamcrest.Matchers.allOf;

import android.app.Activity;
import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.big0soft.nearexpireadmin.R;
import com.big0soft.nearexpireadmin.ui.activities.StoreRegistrationActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LocationDetailsFragmentTest {

    @Rule
    public ActivityScenarioRule<StoreRegistrationActivity> rule = new ActivityScenarioRule<>(StoreRegistrationActivity.class);
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    Context appContext = ApplicationProvider.getApplicationContext();

    @Before
    public void setUp() {
        launchLocationDetailsFragment();

    }

    @Test
    public void emptyCountry_shouldDisplayError() {

        onView(withId(R.id.fragmentLocationDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentLocationDetailsSubmitBtn), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.fragmentLocationCityInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.empty_filed))));

    }

    @Test
    public void emptyCity_shouldDisplayError() {

        onView(withId(R.id.fragmentLocationDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentLocationCountryEditText), isDisplayed()))
                .perform(typeText("Jordan"));
        onView(allOf(withId(R.id.fragmentLocationDetailsSubmitBtn), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.fragmentLocationCityInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.empty_filed))));

    }

    @Test
    public void emptyStreet_shouldDisplayError() {

        onView(withId(R.id.fragmentLocationDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentLocationCountryEditText), isDisplayed()))
                .perform(typeText("Jordan"));
        onView(allOf(withId(R.id.fragmentLocationCityEditText), isDisplayed()))
                .perform(typeText("Irbid"));
        onView(allOf(withId(R.id.fragmentLocationDetailsSubmitBtn), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.fragmentLocationStreetInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.empty_filed))));

    }

    @Test
    public void successSubmition() throws InterruptedException {

        onView(withId(R.id.fragmentLocationDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentLocationCountryEditText), isDisplayed()))
                .perform(typeText("Jordan"));
        onView(allOf(withId(R.id.fragmentLocationCityEditText), isDisplayed()))
                .perform(typeText("Irbid"));
        onView(allOf(withId(R.id.fragmentLocationStreetEditText), isDisplayed()))
                .perform(typeText("al-hazari street"));
        onView(allOf(withId(R.id.fragmentLocationDetailsSubmitBtn), isDisplayed()))
                .perform(click());
        Thread.sleep(1000);
        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
    }

    @Test
    public void testBackUp() throws InterruptedException {

        onView(withId(R.id.fragmentLocationDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentLocationCountryEditText), isDisplayed()))
                .perform(typeText("Jordan"));
        onView(allOf(withId(R.id.fragmentLocationCityEditText), isDisplayed()))
                .perform(typeText("Irbid"));
        onView(allOf(withId(R.id.fragmentLocationStreetEditText), isDisplayed()))
                .perform(typeText("al-hazari street"));
        rule.getScenario().onActivity(Activity::finish);
        Thread.sleep(2000);
        ActivityScenario.launch(StoreRegistrationActivity.class);
        launchLocationDetailsFragment();
        onView(withId(R.id.fragmentLocationDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentLocationCountryEditText), isDisplayed()))
                .check(matches(withText("Jordan")));
        onView(allOf(withId(R.id.fragmentLocationCityEditText), isDisplayed()))
                .check(matches(withText("Irbid")));
        onView(allOf(withId(R.id.fragmentLocationStreetEditText), isDisplayed()))
                .check(matches(withText("al-hazari street")));
    }


    @Test
    public void testOverWriteOnBackUp() throws InterruptedException {

        onView(withId(R.id.fragmentLocationDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentLocationCountryEditText), isDisplayed()))
                .perform(typeText("Jordan"));
        onView(allOf(withId(R.id.fragmentLocationCityEditText), isDisplayed()))
                .perform(typeText("Irbid"));
        onView(allOf(withId(R.id.fragmentLocationStreetEditText), isDisplayed()))
                .perform(typeText("al-hazari street"));
        rule.getScenario().onActivity(Activity::finish);
        Thread.sleep(2000);
        ActivityScenario.launch(StoreRegistrationActivity.class);
        launchLocationDetailsFragment();
        onView(withId(R.id.fragmentLocationDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentLocationCountryEditText), isDisplayed()))
                .perform(replaceText(""));
        onView(allOf(withId(R.id.fragmentLocationCityEditText), isDisplayed()))
                .perform(replaceText(""));
        onView(allOf(withId(R.id.fragmentLocationStreetEditText), isDisplayed()))
                .perform(replaceText(""));
        onView(allOf(withId(R.id.fragmentLocationCountryEditText), isDisplayed()))
                .perform(typeText("Jordan1"));
        onView(allOf(withId(R.id.fragmentLocationCityEditText), isDisplayed()))
                .perform(typeText("Irbid1"));
        onView(allOf(withId(R.id.fragmentLocationStreetEditText), isDisplayed()))
                .perform(typeText("al-hazari street1"));
        rule.getScenario().onActivity(Activity::finish);
        Thread.sleep(2000);
        ActivityScenario.launch(StoreRegistrationActivity.class);
        launchLocationDetailsFragment();
        onView(withId(R.id.fragmentLocationDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentLocationCountryEditText), isDisplayed()))
                .check(matches(withText("Jordan1")));
        onView(allOf(withId(R.id.fragmentLocationCityEditText), isDisplayed()))
                .check(matches(withText("Irbid1")));
        onView(allOf(withId(R.id.fragmentLocationStreetEditText), isDisplayed()))
                .check(matches(withText("al-hazari street1")));
    }

    private void launchLocationDetailsFragment() {
//        onView(withId(R.id.fragmentStoreSetup))
//                .check(matches(isDisplayed()));
//        onView(allOf(withId(R.id.fragmentSetupNextBtn), isDisplayed()))
//                .perform(click());
        rule.getScenario().onActivity(activity -> {
            activity.controller().navigate(R.id.action_storeSetupFragment_to_locationDetailsFragment);
        });
    }
}
