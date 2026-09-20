/*
 * Smart Island (2026)
 * © Animesh Gupta — github.com/agupta07505
 * Licensed under the GNU GPL v3 License
 * Do not remove or alter this notice. - Per GPL-3.0 Section 4 & Section 5
 */

package com.agupta07505.smartisland

import com.agupta07505.smartisland.model.IslandNotification
import com.agupta07505.smartisland.model.SwipeAction
import com.agupta07505.smartisland.ui.executeSwipeAction
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PillGestureActionTest {

    @Test
    fun nextOrPreviousNotificationDoesNotDismissOnSingleNotification() {
        var dismissed = false
        var pageSelected = -1

        executeSwipeAction(
            action = SwipeAction.NextNotification,
            currentNotification = null,
            context = null,
            onDismiss = { dismissed = true },
            onDismissAll = {},
            onToggle = {},
            onOpenNotification = {},
            onOpenFloatingWindow = {},
            onOpenNotificationShade = {},
            onPageSelected = { pageSelected = it },
            notificationsSize = 1,
            currentIndex = 0
        )

        assertFalse("Swipe NextNotification on single notification must not dismiss", dismissed)
        assertEquals(-1, pageSelected)

        executeSwipeAction(
            action = SwipeAction.PreviousNotification,
            currentNotification = null,
            context = null,
            onDismiss = { dismissed = true },
            onDismissAll = {},
            onToggle = {},
            onOpenNotification = {},
            onOpenFloatingWindow = {},
            onOpenNotificationShade = {},
            onPageSelected = { pageSelected = it },
            notificationsSize = 1,
            currentIndex = 0
        )

        assertFalse("Swipe PreviousNotification on single notification must not dismiss", dismissed)
        assertEquals(-1, pageSelected)
    }

    @Test
    fun dismissCurrentCallsOnDismiss() {
        var dismissed = false

        executeSwipeAction(
            action = SwipeAction.DismissCurrent,
            currentNotification = null,
            context = null,
            onDismiss = { dismissed = true },
            onDismissAll = {},
            onToggle = {},
            onOpenNotification = {},
            onOpenFloatingWindow = {},
            onOpenNotificationShade = {},
            onPageSelected = {},
            notificationsSize = 1,
            currentIndex = 0
        )

        assertTrue("Swipe DismissCurrent must trigger onDismiss", dismissed)
    }

    @Test
    fun nextNotificationCyclesIndexWhenMultipleNotificationsExist() {
        var selectedPage = -1

        executeSwipeAction(
            action = SwipeAction.NextNotification,
            currentNotification = null,
            context = null,
            onDismiss = {},
            onDismissAll = {},
            onToggle = {},
            onOpenNotification = {},
            onOpenFloatingWindow = {},
            onOpenNotificationShade = {},
            onPageSelected = { selectedPage = it },
            notificationsSize = 3,
            currentIndex = 0
        )

        assertEquals(1, selectedPage)

        executeSwipeAction(
            action = SwipeAction.PreviousNotification,
            currentNotification = null,
            context = null,
            onDismiss = {},
            onDismissAll = {},
            onToggle = {},
            onOpenNotification = {},
            onOpenFloatingWindow = {},
            onOpenNotificationShade = {},
            onPageSelected = { selectedPage = it },
            notificationsSize = 3,
            currentIndex = 0
        )

        assertEquals(2, selectedPage)
    }

    @Test
    fun expandActionTriggersToggle() {
        var toggled = false

        executeSwipeAction(
            action = SwipeAction.Expand,
            currentNotification = null,
            context = null,
            onDismiss = {},
            onDismissAll = {},
            onToggle = { toggled = true },
            onOpenNotification = {},
            onOpenFloatingWindow = {},
            onOpenNotificationShade = {},
            onPageSelected = {},
            notificationsSize = 1,
            currentIndex = 0
        )

        assertTrue("Swipe Expand must trigger onToggle", toggled)
    }
}
