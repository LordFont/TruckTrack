package hr.foi.air.trucktrack;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DriverListTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void driverListTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.loginButton), withText("Prijavi se"),
                        withParent(allOf(withId(R.id.coordinatorHolderLogin),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.driverListContainer), isDisplayed()));
        frameLayout.perform(click());

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.drivers_toolbar),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.viewIcon), withContentDescription("Promjena načina prikaza"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drivers_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.rvDrivers),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main_container),
                                        0),
                                0),
                        isDisplayed()));
        recyclerView.check(matches(isDisplayed()));

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.viewIcon), withContentDescription("Promjena načina prikaza"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction viewGroup2 = onView(
                allOf(withId(R.id.drivers_toolbar),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class),
                                        0),
                                0),
                        isDisplayed()));
        viewGroup2.check(matches(isDisplayed()));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.viewIcon), withContentDescription("Promjena načina prikaza"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drivers_toolbar),
                                        2),
                                0),
                        isDisplayed()));
        textView2.check(matches(isDisplayed()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.rvDrivers),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.main_container),
                                        0),
                                0),
                        isDisplayed()));
        recyclerView2.check(matches(isDisplayed()));

        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.driverHolder),
                                childAtPosition(
                                        withId(R.id.rvDrivers),
                                        0)),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        pressBack();

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
