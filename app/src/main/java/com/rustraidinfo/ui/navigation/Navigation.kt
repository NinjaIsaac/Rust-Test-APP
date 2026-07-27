package com.rustraidinfo.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val icon: ImageVector? = null
) {
    data object Raids : Screen(
        route = "raids",
        title = "Raids",
        icon = Icons.Default.Dashboard
    )

    data object Calculator : Screen(
        route = "calculator",
        title = "Calculator",
        icon = Icons.Default.Calculate
    )

    data object Materials : Screen(
        route = "materials",
        title = "Materials",
        icon = Icons.Default.Inventory
    )

    data object Settings : Screen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )

    data object RaidDetail : Screen(
        route = "raid_detail/{raidId}",
        title = "Raid Details"
    ) {
        fun createRoute(raidId: Int) = "raid_detail/$raidId"
    }
}

val bottomNavItems = listOf(
    Screen.Raids,
    Screen.Calculator,
    Screen.Materials,
    Screen.Settings
)

