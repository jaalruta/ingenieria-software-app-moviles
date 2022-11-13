package com.miso.vinilos.ui;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.recyclerview.widget.RecyclerView;

import androidx.test.espresso.ViewInteraction;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.miso.vinilos.R;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestHU3_ConsultarListadoArtistas {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void elementosListadoArtistasMayorCero () throws InterruptedException {

        ViewInteraction menuBtn = onView(Matchers.allOf(ViewMatchers.withId(R.id.artistaMenu),isDisplayed()));
        menuBtn.check(matches(withId(R.id.artistaMenu)));
        Thread.sleep(3000);
        menuBtn.perform(click());
        Thread.sleep(3000);

        mActivityTestRule.getScenario().onActivity(activity -> {
            // use 'activity'.
            RecyclerView artistaRv  = activity.findViewById(R.id.artistaRv);
            int cant = artistaRv.getAdapter().getItemCount();
            Assert.assertNotEquals(0,cant);
        });
    }




}



