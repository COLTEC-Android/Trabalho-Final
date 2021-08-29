package br.ufmg.coltec.trabalhofinal.activities;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.ufmg.coltec.trabalhofinal.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

    @Rule
    public ActivityScenarioRule<ListActivity> mActivityTestRule = new ActivityScenarioRule<>(ListActivity.class);

    @Test
    public void listActivityTest() {

        ViewInteraction textView = onView(
                allOf(withText("Books list"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView.check(matches(withText("Books list")));

        ViewInteraction listView = onView(
                allOf(withId(android.R.id.list),
                        withParent(withParent(withId(R.id.frag_books_list))),
                        isDisplayed()));
        listView.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withText("Favorites"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        textView3.check(matches(withText("Favorites")));

        ViewInteraction listView2 = onView(
                allOf(withId(android.R.id.list),
                        withParent(withParent(withId(R.id.frag_favorite_books_list))),
                        isDisplayed()));
        listView2.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
