package com.example.ecommercemarvel.espresso;

//import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.ecommercemarvel.R;
import com.example.ecommercemarvel.view.ComicAdapter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class ComicAdapterTest {



    @Test
    public void shouldDisplayAdapter() {

        onView(withId(R.id.comicTitle)).check(matches(isDisplayed()));
        onView(withId(R.id.comicImage)).check(matches(isDisplayed()));
    }

}
