package com.rustraidinfo.ui.screens

import androidx.compose.animation.*
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
import com.rustraidinfo.data.models.RaidInfo
import com.rustraidinfo.ui.components.*
import com.rustraidinfo.ui.theme.*

@Composable
fun RaidDetailScreen(
    raid: RaidInfo,
    onBack: () -> Unit
) {
    RustBackground { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Top bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = RustTextPrimary
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Raid Details",
                    style = MaterialTheme.typography.titleLarge,
                    color = RustTextPrimary,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                // Raid header card
                RustCard(
                    modifier = Modifier.fillMaxWidth(),
                    glowColor = raid.difficulty.color.copy(alpha = 0.4f)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = raid.title,
                                style = MaterialTheme.typography.headlineSmall,
                                color = RustTextPrimary,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f)
                            )
                            DifficultyBadge(
                                difficulty = raid.difficulty.displayName,
                                color = raid.difficulty.color
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = raid.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = RustTextSecondary
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Stats row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StatItem(
                                label = "Difficulty",
                                value = raid.difficulty.displayName
                            )
                            StatItem(
                                label = "Est. Cost",
                                value = raid.estimatedCost
                            )
                            StatItem(
                                label = "Items Needed",
                                value = "${raid.requiredItems.size}"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Required items section
                SectionHeader(title = "Required Items")

                Spacer(modifier = Modifier.height(8.dp))

                raid.requiredItems.forEach { item ->
                    RustCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        glowColor = RustOrange.copy(alpha = 0.2f)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Inventory,
                                    contentDescription = null,
                                    tint = RustGold,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.bodyMedium,
                                color = RustTextPrimary,
                                modifier = Modifier.weight(1f),
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "x${item.quantity}",
                                style = MaterialTheme.typography.titleMedium,
                                color = RustGold,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Tips section
                SectionHeader(title = "Raid Tips")

                Spacer(modifier = Modifier.height(8.dp))

                raid.tips.forEachIndexed { index, tip ->
                    RustCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        glowColor = RustAccentBlue.copy(alpha = 0.2f)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .padding(end = 4.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.Lightbulb,
                                    contentDescription = null,
                                    tint = RustGold,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = tip,
                                style = MaterialTheme.typography.bodyMedium,
                                color = RustTextPrimary
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Start raid button
                RustButton(
                    text = "Start Planning This Raid",
                    onClick = { onBack() },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}
Scaffold(
    topBar = {

    },
    bottomBar = {

    }
) { padding ->

    Box(
        modifier = Modifier.padding(padding)
    ) {

    }
}

