package android_tests


import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.to_do_list.MainActivity
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class TodoScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun todoScreen_titleIsDisplayed() {
        composeTestRule
            .onNodeWithText("Todo Screen")
            .assertIsDisplayed()
    }
}
