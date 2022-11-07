package com.miso.vinilos.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.miso.vinilos.R;
import com.miso.vinilos.ui.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestHU2_ConsultarInformacionDetalladaAlbumes {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainTestActivity () throws InterruptedException {

        ViewInteraction menuBtn = onView(Matchers.allOf(ViewMatchers.withId(R.id.albumMenu),isDisplayed()));
        menuBtn.check(matches(withId(R.id.albumMenu)));
        Thread.sleep(3000);
        menuBtn.perform(click());
        Thread.sleep(3000);

        ViewInteraction albumBtn = onView(allOf(withId(R.id.textView6), withText("A Night at the Opera"),isDisplayed()));
        albumBtn.check(matches(withId(R.id.textView6)));
        Thread.sleep(3000);
        albumBtn.perform(click());
        Thread.sleep(3000);


    }

}
