package com.rustraidinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rustraidinfo.data.models.raidStrategies
import com.rustraidinfo.ui.components.RustBackground
import com.rustraidinfo.ui.navigation.Screen
import com.rustraidinfo.ui.screens.*
import com.rustraidinfo.ui.theme.*
import com.rustraidinfo.viewmodel.RaidViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RustRaidInfoTheme {
                RustRaidInfoApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RustRaidInfoApp() {
    val navController = rememberNavController()
    val viewModel: RaidViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Determine if we should show bottom bar
    val showBottomBar = Screen.bottomNavItems.any { screen ->
        currentDestination?.hierarchy?.any { it.route == screen.route } == true
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                RustBottomNavigationBar(
                    currentDestination = currentDestination,
                    onNavigate = { screen ->
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Raids.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Raids.route) {
                RaidsScreen(
                    viewModel = viewModel,
                    onRaidClick = { raid ->
                        viewModel.selectRaid(raid)
                        navController.navigate(Screen.RaidDetail.createRoute(raid.id))
                    }
                )
            }

            composable(Screen.Calculator.route) {
                CalculatorScreen(viewModel = viewModel)
            }

            composable(Screen.Materials.route) {
                MaterialsScreen()
            }

            composable(Screen.Settings.route) {
                SettingsScreen()
            }

            composable(
                route = Screen.RaidDetail.route,
                arguments = listOf(
                    navArgument("raidId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val raidId = backStackEntry.arguments?.getInt("raidId") ?: return@composable
                val raid = raidStrategies.find { it.id == raidId }

                if (raid != null) {
                    RaidDetailScreen(
                        raid = raid,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

@Composable
fun RustBottomNavigationBar(
    currentDestination: androidx.navigation.NavDestination?,
    onNavigate: (Screen) -> Unit
) {
    NavigationBar(
        containerColor = RustDark,
        contentColor = RustTextPrimary,
        tonalElevation = 0.dp,
        modifier = Modifier.navigationBarsPadding()
    ) {
        Screen.bottomNavItems.forEach { screen ->
            val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

            NavigationBarItem(
                selected = selected,
                onClick = { onNavigate(screen) },
                icon = {
                    Icon(
                        imageVector = screen.icon ?: Icons.Default.Warning,
                        contentDescription = screen.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = screen.title,
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = RustRed,
                    selectedTextColor = RustRed,
                    unselectedIconColor = RustTextMuted,
                    unselectedTextColor = RustTextMuted,
                    indicatorColor = RustRed.copy(alpha = 0.15f)
                )
            )
        }
    }
}

