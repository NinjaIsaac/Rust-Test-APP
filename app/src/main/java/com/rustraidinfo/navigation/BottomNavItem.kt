package com.rustraidinfo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val route: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Raids", "raids", Icons.Default.Home),
    BottomNavItem("Calculator", "calculator", Icons.Default.Calculate),
    BottomNavItem("Materials", "materials", Icons.Default.Build),
    BottomNavItem("Settings", "settings", Icons.Default.Settings)
)