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
public class TestHU5_ConsultarListadoColeccionistas {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void elementosListadoColeccionistasMayorCero () throws InterruptedException {

        ViewInteraction menuBtn = onView(Matchers.allOf(ViewMatchers.withId(R.id.coleccionistaMenu),isDisplayed()));
        menuBtn.check(matches(withId(R.id.coleccionistaMenu)));
        Thread.sleep(3000);
        menuBtn.perform(click());
        Thread.sleep(3000);

        mActivityTestRule.getScenario().onActivity(activity -> {
            // use 'activity'.
            RecyclerView coleccionistaRv  = activity.findViewById(R.id.coleccionistaRv);
            int cant = coleccionistaRv.getAdapter().getItemCount();
            Assert.assertNotEquals(0,cant);
        });
    }

}
