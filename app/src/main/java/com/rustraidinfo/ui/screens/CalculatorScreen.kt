package com.rustraidinfo.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rustraidinfo.data.models.BuildingMaterial
import com.rustraidinfo.data.models.RaidTool
import com.rustraidinfo.data.models.WallType
import com.rustraidinfo.ui.components.*
import com.rustraidinfo.ui.theme.*
import com.rustraidinfo.viewmodel.RaidViewModel

@Composable
fun CalculatorScreen(
    viewModel: RaidViewModel
) {
    RustBackground { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            SectionHeader(title = "Raid Cost Calculator")

            Spacer(modifier = Modifier.height(8.dp))

            // Building Material Selection
            Text(
                text = "Building Material",
                style = MaterialTheme.typography.titleSmall,
                color = RustTextSecondary,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BuildingMaterial.entries.forEach { material ->
                    MaterialChip(
                        material = material,
                        isSelected = viewModel.selectedMaterial == material,
                        onClick = { viewModel.updateMaterial(material) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Wall Type Selection
            Text(
                text = "Wall Type",
                style = MaterialTheme.typography.titleSmall,
                color = RustTextSecondary,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WallType.entries.take(3).forEach { wallType ->
                    WallTypeChip(
                        wallType = wallType,
                        isSelected = viewModel.selectedWallType == wallType,
                        onClick = { viewModel.updateWallType(wallType) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                WallType.entries.drop(3).forEach { wallType ->
                    WallTypeChip(
                        wallType = wallType,
                        isSelected = viewModel.selectedWallType == wallType,
                        onClick = { viewModel.updateWallType(wallType) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Raid Tool Selection
            Text(
                text = "Raid Tool",
                style = MaterialTheme.typography.titleSmall,
                color = RustTextSecondary,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
            )

            // Tool chips - first row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RaidTool.entries.take(4).forEach { tool ->
                    ToolChip(
                        tool = tool,
                        isSelected = viewModel.selectedRaidTool == tool,
                        onClick = { viewModel.updateRaidTool(tool) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Tool chips - second row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RaidTool.entries.drop(4).take(4).forEach { tool ->
                    ToolChip(
                        tool = tool,
                        isSelected = viewModel.selectedRaidTool == tool,
                        onClick = { viewModel.updateRaidTool(tool) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Tool chips - third row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                RaidTool.entries.drop(8).forEach { tool ->
                    ToolChip(
                        tool = tool,
                        isSelected = viewModel.selectedRaidTool == tool,
                        onClick = { viewModel.updateRaidTool(tool) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Wall Count
            CounterControl(
                value = viewModel.wallCount,
                onIncrement = { viewModel.updateWallCount(viewModel.wallCount + 1) },
                onDecrement = { viewModel.updateWallCount(viewModel.wallCount - 1) },
                label = "Number of Walls",
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Calculate button
            RustButton(
                text = "Calculate Raid Cost",
                onClick = { viewModel.calculateRaidCost() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            // Result
            AnimatedVisibility(
                visible = viewModel.calculatedQuantity != null,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = RustCardBg
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.Calculate,
                            contentDescription = null,
                            tint = RustGold,
                            modifier = Modifier.size(32.dp)
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Raid Cost Result",
                            style = MaterialTheme.typography.titleMedium,
                            color = RustTextPrimary,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Required ${viewModel.selectedRaidTool.displayName}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = RustTextSecondary
                        )

                        Text(
                            text = "${viewModel.calculatedQuantity}",
                            style = MaterialTheme.typography.displayLarge,
                            color = RustRed,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "To destroy ${viewModel.wallCount}x ${viewModel.selectedWallType.displayName} ${viewModel.selectedMaterial.displayName} wall${if (viewModel.wallCount > 1) "s" else ""}",
                            style = MaterialTheme.typography.bodySmall,
                            color = RustTextSecondary,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedButton(
                            onClick = { viewModel.resetCalculation() },
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = RustTextSecondary
                            )
                        ) {
                            Icon(
                                Icons.Default.Refresh,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Reset")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(80.dp)) // Bottom nav spacing
        }
    }
}

@Composable
private fun MaterialChip(
    material: BuildingMaterial,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        modifier = modifier.height(48.dp),
        label = {
            Text(
                text = material.displayName,
                fontSize = MaterialTheme.typography.labelSmall.fontSize,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = material.color.copy(alpha = 0.3f),
            selectedLabelColor = material.color
        ),
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
private fun WallTypeChip(
    wallType: WallType,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        modifier = modifier.height(48.dp),
        label = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = wallType.displayName,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
                Text(
                    text = "${wallType.health} HP",
                    fontSize = 10.sp,
                    color = if (isSelected) RustTextSecondary else RustTextMuted
                )
            }
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = RustRed.copy(alpha = 0.2f),
            selectedLabelColor = RustTextPrimary
        ),
        shape = RoundedCornerShape(10.dp)
    )
}

@Composable
private fun ToolChip(
    tool: RaidTool,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        modifier = modifier.height(40.dp),
        label = {
            Text(
                text = tool.displayName,
                fontSize = 10.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                maxLines = 1
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = RustOrange.copy(alpha = 0.3f),
            selectedLabelColor = RustOrange
        ),
        shape = RoundedCornerShape(8.dp)
    )
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

