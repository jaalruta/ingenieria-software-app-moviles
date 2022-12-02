package com.miso.vinilos.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
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
public class TestHU8_Comentar_album {
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void comentarAlbum () throws InterruptedException {

        String album = getRandomString(10);
        String descripcion = getRandomString(50);
        ViewInteraction menuBtn = onView(Matchers.allOf(ViewMatchers.withId(R.id.albumMenu),isDisplayed()));
        menuBtn.check(matches(withId(R.id.albumMenu)));
        Thread.sleep(3000);
        menuBtn.perform(click());
        Thread.sleep(3000);

        onView(withId(R.id.albumsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        Thread.sleep(3000);

        ViewInteraction botonComentar = onView(Matchers.allOf(ViewMatchers.withId(R.id.botonComentar),isDisplayed()));
        botonComentar.perform(ViewActions.click());

        ViewInteraction comentario = onView(Matchers.allOf(ViewMatchers.withId(R.id.albumComentario),isDisplayed()));
        comentario.perform(ViewActions.click());

        comentario.perform(ViewActions.replaceText("Comentario de pruebas"));

        Espresso.closeSoftKeyboard();

        Thread.sleep(3000);

        ViewInteraction botonGuardar = onView(Matchers.allOf(ViewMatchers.withId(R.id.botonGuardarComentario),isDisplayed()));
        botonGuardar.perform(ViewActions.click());
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
