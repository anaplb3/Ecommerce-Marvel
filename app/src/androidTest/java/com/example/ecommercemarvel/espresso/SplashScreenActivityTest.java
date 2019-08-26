package com.example.ecommercemarvel.espresso;

import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.ecommercemarvel.R;
import com.example.ecommercemarvel.view.ComicsActivity;
import com.example.ecommercemarvel.view.SplashScreenActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class SplashScreenActivityTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity>
            mActivityRule = new ActivityTestRule<>(SplashScreenActivity.class, false, true);

    @Test
    public void whenActivityIsLaunched_shouldDisplaySplashScreen() {

        onView(withId(R.id.imageViewLogo)).check(matches(isDisplayed()));
    }

    @Test
    public void after2Seconds_shouldLaunchComicsActivity() {
        Intents.init();


        //TIRAR GAMBI
        try {
            Thread.sleep(2000);
        } catch (Exception a) {
            System.out.println(a.getMessage());
        }

        Matcher<Intent> matcher = hasComponent(ComicsActivity.class.getName());

        intended(matcher);

        Intents.release();
    }

}
