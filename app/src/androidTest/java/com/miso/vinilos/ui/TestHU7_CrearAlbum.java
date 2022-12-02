package com.miso.vinilos.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.EditText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.miso.vinilos.R;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestHU7_CrearAlbum {
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void creaAlbumVisible () throws InterruptedException {

        String album = getRandomString(10);
        String descripcion = getRandomString(50);
        ViewInteraction menuBtn = onView(Matchers.allOf(ViewMatchers.withId(R.id.albumMenu),isDisplayed()));
        menuBtn.check(matches(withId(R.id.albumMenu)));
        Thread.sleep(3000);
        menuBtn.perform(click());
        Thread.sleep(3000);

        ViewInteraction crearAlbumbtn = onView(Matchers.allOf(ViewMatchers.withId(R.id.botonCrearAlbum),isDisplayed()));
        crearAlbumbtn.perform(click());
        Thread.sleep(3000);

        ViewInteraction nombreAlbum = onView(Matchers.allOf(ViewMatchers.withId(R.id.nombreAlbum),isDisplayed()));
        nombreAlbum.perform(ViewActions.typeText(album));

        ViewInteraction urlAlbum = onView(Matchers.allOf(ViewMatchers.withId(R.id.urlAlbum),isDisplayed()));
        urlAlbum.perform(ViewActions.click());
        urlAlbum.perform(ViewActions.replaceText("https://upload.wikimedia.org/wikipedia/en/thumb/1/1d/Adioslepido1.jpg/220px-Adioslepido1.jpg"));

        Espresso.closeSoftKeyboard();

        ViewInteraction fechaAlbum = onView(Matchers.allOf(ViewMatchers.withId(R.id.fechaAlbum),isDisplayed()));
        fechaAlbum.perform(ViewActions.click());
        onView(withId(android.R.id.button1)).perform(click());

        Espresso.closeSoftKeyboard();

        onView(withId(R.id.descripcionAlbum)).perform(ViewActions.typeText(descripcion));
        Thread.sleep(3000);
        Espresso.closeSoftKeyboard();
        Thread.sleep(3000);
        ViewInteraction guardarAlbumBtn = onView(Matchers.allOf(ViewMatchers.withId(R.id.botonGuardarAlbum),isDisplayed()));
        guardarAlbumBtn.perform(click());
        Thread.sleep(3000);

    }



    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }

}
