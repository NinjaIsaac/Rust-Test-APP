package com.rustraidinfo.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rustraidinfo.data.models.RaidDifficulty
import com.rustraidinfo.data.models.RaidInfo
import com.rustraidinfo.ui.components.*
import com.rustraidinfo.ui.theme.*
import com.rustraidinfo.viewmodel.RaidViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RaidsScreen(
    viewModel: RaidViewModel,
    onRaidClick: (RaidInfo) -> Unit
) {
    val raids = viewModel.raids
    var showFilter by remember { mutableStateOf(false) }

    RustBackground { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Header
            SectionHeader(
                title = "Raid Strategies",
                action = {
                    IconButton(onClick = { showFilter = !showFilter }) {
                        Icon(
                            Icons.Default.FilterList,
                            contentDescription = "Filter",
                            tint = if (showFilter) RustRed else RustTextSecondary
                        )
                    }
                }
            )

            // Search bar
            OutlinedTextField(
                value = viewModel.searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(12.dp)),
                placeholder = {
                    Text("Search raids...", color = RustTextMuted)
                },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = null, tint = RustTextSecondary)
                },
                trailingIcon = {
                    if (viewModel.searchQuery.isNotBlank()) {
                        IconButton(onClick = { viewModel.updateSearchQuery("") }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear", tint = RustTextSecondary)
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = RustTextPrimary,
                    unfocusedTextColor = RustTextPrimary,
                    focusedBorderColor = RustRed,
                    unfocusedBorderColor = RustCardBg,
                    cursorColor = RustRed,
                    focusedContainerColor = RustCardBg.copy(alpha = 0.5f),
                    unfocusedContainerColor = RustCardBg.copy(alpha = 0.3f)
                ),
                singleLine = true
            )

            // Difficulty filter chips
            AnimatedVisibility(visible = showFilter) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val difficulties = listOf(null, "Easy", "Medium", "Hard", "Expert")
                    difficulties.forEach { difficulty ->
                        FilterChip(
                            selected = viewModel.selectedDifficulty == difficulty,
                            onClick = { viewModel.updateDifficulty(difficulty) },
                            label = { Text(difficulty ?: "All", fontSize = MaterialTheme.typography.labelSmall.fontSize) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = RustRed.copy(alpha = 0.2f),
                                selectedLabelColor = RustRed
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Raid list
            if (raids.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No raids found",
                        color = RustTextSecondary,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(raids, key = { it.id }) { raid ->
                        RaidListItem(
                            raid = raid,
                            onClick = { onRaidClick(raid) }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(80.dp)) // Bottom nav spacing
                    }
                }
            }
        }
    }
}

@Composable
private fun RaidListItem(
    raid: RaidInfo,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val borderAlpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    RustCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        glowColor = raid.difficulty.color.copy(alpha = borderAlpha)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = raid.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = RustTextPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                DifficultyBadge(
                    difficulty = raid.difficulty.displayName,
                    color = raid.difficulty.color
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = raid.description,
                style = MaterialTheme.typography.bodySmall,
                color = RustTextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Estimated Cost",
                        style = MaterialTheme.typography.labelSmall,
                        color = RustTextMuted
                    )
                    Text(
                        text = raid.estimatedCost,
                        style = MaterialTheme.typography.titleSmall,
                        color = RustGold,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "${raid.requiredItems.size} items needed",
                    style = MaterialTheme.typography.labelSmall,
                    color = RustTextSecondary
                )
            }
        }
    }
}

