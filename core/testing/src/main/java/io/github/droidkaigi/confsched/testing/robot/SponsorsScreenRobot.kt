package io.github.droidkaigi.confsched.testing.robot

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import com.github.takahirom.roborazzi.captureRoboImage
import io.github.droidkaigi.confsched.designsystem.theme.KaigiTheme
import io.github.droidkaigi.confsched.sponsors.SponsorsScreen
import io.github.droidkaigi.confsched.sponsors.SponsorsScreenTestTag
import io.github.droidkaigi.confsched.testing.RobotTestRule
import io.github.droidkaigi.confsched.testing.coroutines.runTestWithLogging
import kotlinx.coroutines.test.TestDispatcher
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class SponsorsScreenRobot @Inject constructor(
    private val testDispatcher: TestDispatcher,
) {
    @Inject lateinit var robotTestRule: RobotTestRule
    private lateinit var composeTestRule: AndroidComposeTestRule<*, *>
    operator fun invoke(
        block: SponsorsScreenRobot.() -> Unit,
    ) {
        runTestWithLogging(timeout = 30.seconds) {
            this@SponsorsScreenRobot.composeTestRule = robotTestRule.composeTestRule
            block()
        }
    }

    fun setupScreenContent() {
        composeTestRule.setContent {
            KaigiTheme {
                SponsorsScreen(
                    onNavigationIconClick = {},
                    onSponsorClick = {},
                )
            }
        }
        waitUntilIdle()
    }

    fun scrollSponsorScreen() {
        composeTestRule
            .onNode(hasTestTag(SponsorsScreenTestTag))
            .performTouchInput {
                swipeUp(
                    startY = visibleSize.height * 3F / 4,
                    endY = visibleSize.height / 3F,
                )
            }
    }

    fun checkScreenCapture() {
        composeTestRule
            .onNode(isRoot())
            .captureRoboImage()
    }

    fun waitUntilIdle() {
        composeTestRule.waitForIdle()
        testDispatcher.scheduler.advanceUntilIdle()
    }
}
