package com.wasambo.news

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class NewsDetailActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun newsDetailActivityTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.newsListRecyclerView),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val imageView = onView(
            allOf(
                withId(R.id.coverImageView),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withId(R.id.titleTextView),
                withText("Michael K. Williams, Omar From ‘The Wire,’ Is Dead at 54"),
                withParent(withParent(withId(R.id.scrollView))),
                isDisplayed()
            )
        )
        textView.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.authorTextView),
                withText("By Julia Jacobs, Annie Correal, Matthew Haag and Jeremy Egner"),
                withParent(withParent(withId(R.id.scrollView))),
                isDisplayed()
            )
        )
        textView2.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withId(R.id.contentTextView),
                withText("Mr. Williams, who also starred in “Boardwalk Empire” and “Lovecraft Country,” was best known for his role as Omar Little in the David Simon HBO series."),
                withParent(withParent(withId(R.id.scrollView))),
                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))

        val textView4 = onView(
            allOf(
                withId(R.id.dateTextView), withText("2021-09-06"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView4.check(matches(isDisplayed()))

        val textView5 = onView(
            allOf(
                withId(R.id.urlTextView),
                withText("https://www.nytimes.com/2021/09/06/arts/michael-k-williams-dead.html"),
                withParent(withParent(withId(R.id.scrollView))),
                isDisplayed()
            )
        )
        textView5.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>,
        position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent) &&
                    view == parent.getChildAt(position)
            }
        }
    }
}
