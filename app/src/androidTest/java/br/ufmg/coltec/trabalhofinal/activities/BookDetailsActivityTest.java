package br.ufmg.coltec.trabalhofinal.activities;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
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
public class BookDetailsActivityTest {

    @Rule
    public ActivityScenarioRule<BookDetailsActivity> mActivityTestRule = new ActivityScenarioRule<>(BookDetailsActivity.class);

    @Test
    public void bookDetailsActivityTest() {

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.action_bar),
                        withParent(allOf(withId(R.id.action_bar_container),
                                withParent(withId(R.id.decor_content_parent)))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withText("Final work: FULL"),
                        withParent(allOf(withId(R.id.action_bar),
                                withParent(withId(R.id.action_bar_container)))),
                        isDisplayed()));
        textView2.check(matches(withText("Final work: FULL")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.image), withContentDescription("Share with"),
                        withParent(allOf(withId(R.id.expand_activities_button),
                                withParent(withId(R.id.activity_chooser_view_content)))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction relativeLayout = onView(
                allOf(withParent(allOf(withId(android.R.id.content),
                        withParent(withId(R.id.decor_content_parent)))),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.img_book_details_photo), withContentDescription("Book picture"),
                        withParent(withParent(withId(android.R.id.content))),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.txt_book_details_title_description), withText("Title"),
                        withParent(allOf(withId(R.id.linear_title),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView3.check(matches(withText("Title")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.txt_book_details_title), withText("Morte e Vida Severina"),
                        withParent(allOf(withId(R.id.linear_title),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView4.check(matches(withText("Morte e Vida Severina")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.txt_book_details_author_description), withText("Author"),
                        withParent(allOf(withId(R.id.linear_author),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView5.check(matches(withText("Author")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.txt_book_details_author), withText("Jo�o Cabral de Melo Neto"),
                        withParent(allOf(withId(R.id.linear_author),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView6.check(matches(withText("Jo�o Cabral de Melo Neto")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.txt_book_details_genre_description), withText("Genre"),
                        withParent(allOf(withId(R.id.linear_genre),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView7.check(matches(withText("Genre")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.txt_book_details_genre), withText("Poema"),
                        withParent(allOf(withId(R.id.linear_genre),
                                withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class)))),
                        isDisplayed()));
        textView8.check(matches(withText("Poema")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.txt_book_details_publisher_description), withText("Publisher"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        textView9.check(matches(withText("Publisher")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.txt_book_details_publisher), withText("TUCA"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        textView10.check(matches(withText("TUCA")));
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
