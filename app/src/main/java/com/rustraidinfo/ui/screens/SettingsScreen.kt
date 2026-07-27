package com.rustraidinfo.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rustraidinfo.ui.components.*
import com.rustraidinfo.ui.theme.*

@Composable
fun SettingsScreen() {
    RustBackground { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // App Info
            Spacer(modifier = Modifier.height(32.dp))
            AnimatedRustLogo(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(32.dp))

            SectionHeader(title = "Settings")

            Spacer(modifier = Modifier.height(8.dp))

            // Settings items
            SettingsItem(
                icon = Icons.Default.DarkMode,
                title = "Dark Mode",
                subtitle = "Always on (Rust style)",
                enabled = true
            )

            SettingsItem(
                icon = Icons.Default.Notifications,
                title = "Notifications",
                subtitle = "Raid alerts and updates",
                enabled = false
            )

            SettingsItem(
                icon = Icons.Default.Info,
                title = "App Version",
                subtitle = "1.0.0",
                showToggle = false
            )

            Divider(
                color = RustCardBg,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            SectionHeader(title = "Raid Calculator Settings")

            Spacer(modifier = Modifier.height(8.dp))

            // Default settings
            SettingsItem(
                icon = Icons.Default.Tune,
                title = "Default Wall Type",
                subtitle = "Stone",
                showToggle = false
            )

            SettingsItem(
                icon = Icons.Default.Tune,
                title = "Default Raid Tool",
                subtitle = "C4 Explosive",
                showToggle = false
            )

            Divider(
                color = RustCardBg,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            SectionHeader(title = "About")

            Spacer(modifier = Modifier.height(8.dp))

            SettingsItem(
                icon = Icons.Default.Code,
                title = "Developer",
                subtitle = "Rust Raid Info Team",
                showToggle = false
            )

            SettingsItem(
                icon = Icons.Default.Description,
                title = "Data Source",
                subtitle = "Rust Game Data (2024)",
                showToggle = false
            )

            SettingsItem(
                icon = Icons.Default.Shield,
                title = "Privacy Policy",
                subtitle = "No data collected",
                showToggle = false
            )

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
private fun SettingsItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String,
    enabled: Boolean = true,
    showToggle: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = RustRed,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = RustTextPrimary,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = RustTextSecondary
            )
        }

        if (showToggle) {
            Switch(
                checked = enabled,
                onCheckedChange = null,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = RustRed,
                    checkedTrackColor = RustDarkRed.copy(alpha = 0.5f),
                    uncheckedThumbColor = RustTextMuted,
                    uncheckedTrackColor = RustCardBg
                )
            )
        } else {
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = RustTextMuted,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
