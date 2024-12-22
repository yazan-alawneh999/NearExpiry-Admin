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
public class ContactDetailsFragmentTest {
    @Rule
    public ActivityScenarioRule<StoreRegistrationActivity> activityScenario  = new ActivityScenarioRule<>(StoreRegistrationActivity.class);
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    Context appContext = ApplicationProvider.getApplicationContext();
    @Before
    public void setUp() {
        launchContactDetailsFragment();
    }


    @Test
    public void emptyPhone_shouldDisplayError() throws InterruptedException {

        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsSubmitBtn), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.empty_filed))));


    }


    @Test
    public void wrongPhone_shouldDisplayError() {

        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .perform(typeText("7777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsSubmitBtn), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.invalid_phone))));

    }
    @Test
    public void emptyWhatsapp_shouldDisplayError() {

        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .perform(typeText("+9627777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsSubmitBtn), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.fragmentContactDetailsWhatsAppInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.empty_filed))));

    }
    @Test
    public void wrongWhatsapp_shouldDisplayError() {

        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .perform(typeText("7777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsSubmitBtn), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.fragmentContactDetailsWhatsAppInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.invalid_phone))));

    }

    @Test
    public void emptyEmail_shouldDisplayError() {

        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .perform(typeText("+9627777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsWhatsAppEditText), isDisplayed()))
                .perform(typeText("+9627777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsSubmitBtn), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.fragmentContactDetailsEmailInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(R.string.empty_filed))));

    }

    @Test
    public void wrongEmail_shouldDisplayError()  {

        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .perform(typeText("+9627777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsWhatsAppEditText), isDisplayed()))
                .perform(typeText("+9627777777777"));

        onView(allOf(withId(R.id.fragmentContactDetailsEmailEditText), isDisplayed()))
                .perform(typeText("testexample.com"));
        onView(allOf(withId(R.id.fragmentContactDetailsSubmitBtn), isDisplayed()))
                .perform(click());
        onView(allOf(withId(R.id.fragmentContactDetailsEmailInputLayout), isDisplayed()))
                .check(matches(hasTextInputLayoutErrorText(appContext.getString(com.big0soft.resource.R.string.invalid_email))));


    }


    @Test
    public void successSubmition() throws InterruptedException {

        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .perform(typeText("+9627777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsWhatsAppEditText), isDisplayed()))
                .perform(typeText("+9627777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsEmailEditText), isDisplayed()))
                .perform(typeText("test@example.com"));
        onView(allOf(withId(R.id.fragmentContactDetailsSubmitBtn), isDisplayed()))
                .perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.fragmentVerification)).check(matches(isDisplayed()));
    }

    @Test
    public void testBackUp() throws InterruptedException {

        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .perform(typeText("+9627777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsWhatsAppEditText), isDisplayed()))
                .perform(typeText("+9627777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsEmailEditText), isDisplayed()))
                .perform(typeText("test@example.com"));
        activityScenario.getScenario().onActivity(Activity::finish);
        Thread.sleep(2000);
        ActivityScenario.launch(StoreRegistrationActivity.class);
        launchContactDetailsFragment();
        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .check(matches(withText("+9627777777777")));
        onView(allOf(withId(R.id.fragmentContactDetailsWhatsAppEditText), isDisplayed()))
                .check(matches(withText("+9627777777777")));
        onView(allOf(withId(R.id.fragmentContactDetailsEmailEditText), isDisplayed()))
                .check(matches(withText("test@example.com")));
    }


    @Test
    public void testOverWriteOnBackUp() throws InterruptedException {

        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .perform(typeText("+9627777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsWhatsAppEditText), isDisplayed()))
                .perform(typeText("+9627777777777"));
        onView(allOf(withId(R.id.fragmentContactDetailsEmailEditText), isDisplayed()))
                .perform(typeText("test@example.com"));
        activityScenario.getScenario().onActivity(Activity::finish);
        Thread.sleep(2000);
        ActivityScenario.launch(StoreRegistrationActivity.class);
        launchContactDetailsFragment();
        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .perform(replaceText(""));
        onView(allOf(withId(R.id.fragmentContactDetailsWhatsAppEditText), isDisplayed()))
                .perform(replaceText(""));
        onView(allOf(withId(R.id.fragmentContactDetailsEmailEditText), isDisplayed()))
                .perform(replaceText(""));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .perform(typeText("+9627777777778"));
        onView(allOf(withId(R.id.fragmentContactDetailsWhatsAppEditText), isDisplayed()))
                .perform(typeText("+9627777777778"));
        onView(allOf(withId(R.id.fragmentContactDetailsEmailEditText), isDisplayed()))
                .perform(typeText("test1@example.com"));
        activityScenario.getScenario().onActivity(Activity::finish);
        Thread.sleep(2000);
        ActivityScenario.launch(StoreRegistrationActivity.class);
        launchContactDetailsFragment();
        onView(withId(R.id.fragmentContactDetails)).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.fragmentContactDetailsPhoneEditText), isDisplayed()))
                .check(matches(withText("+9627777777778")));
        onView(allOf(withId(R.id.fragmentContactDetailsWhatsAppEditText), isDisplayed()))
                .check(matches(withText("+9627777777778")));
        onView(allOf(withId(R.id.fragmentContactDetailsEmailEditText), isDisplayed()))
                .check(matches(withText("test1@example.com")));
    }

    private void launchContactDetailsFragment() {
        activityScenario.getScenario().onActivity(activity -> {
            activity.controller().navigate(R.id.fragmentContactDetails2);
        });
    }
}
