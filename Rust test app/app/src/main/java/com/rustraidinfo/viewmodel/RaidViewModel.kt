package com.rustraidinfo.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.rustraidinfo.data.models.BuildingMaterial
import com.rustraidinfo.data.models.RaidInfo
import com.rustraidinfo.data.models.RaidTool
import com.rustraidinfo.data.models.WallType
import com.rustraidinfo.data.models.raidStrategies

class RaidViewModel : ViewModel() {

    // UI State
    var selectedTab by mutableStateOf(0)
        private set

    var selectedRaid by mutableStateOf<RaidInfo?>(null)
        private set

    // Calculator state
    var selectedMaterial by mutableStateOf(BuildingMaterial.STONE)
        private set

    var selectedWallType by mutableStateOf(WallType.STONE)
        private set

    var selectedRaidTool by mutableStateOf(RaidTool.C4)
        private set

    var wallCount by mutableStateOf(1)
        private set

    var calculatedQuantity by mutableStateOf<Int?>(null)
        private set

    // Search/filter state
    var searchQuery by mutableStateOf("")
        private set

    var selectedDifficulty by mutableStateOf<String?>(null)
        private set

    val raids: List<RaidInfo>
        get() {
            var filtered = raidStrategies

            if (searchQuery.isNotBlank()) {
                filtered = filtered.filter {
                    it.title.contains(searchQuery, ignoreCase = true) ||
                    it.description.contains(searchQuery, ignoreCase = true)
                }
            }

            if (selectedDifficulty != null) {
                filtered = filtered.filter {
                    it.difficulty.displayName == selectedDifficulty
                }
            }

            return filtered
        }

    fun selectTab(index: Int) {
        selectedTab = index
    }

    fun selectRaid(raid: RaidInfo) {
        selectedRaid = raid
    }

    fun clearSelection() {
        selectedRaid = null
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
    }

    fun updateDifficulty(difficulty: String?) {
        selectedDifficulty = difficulty
    }

    fun updateMaterial(material: BuildingMaterial) {
        selectedMaterial = material
    }

    fun updateWallType(wallType: WallType) {
        selectedWallType = wallType
    }

    fun updateRaidTool(tool: RaidTool) {
        selectedRaidTool = tool
    }

    fun updateWallCount(count: Int) {
        wallCount = count.coerceIn(1, 100)
    }

    fun calculateRaidCost() {
        // Simplified calculation based on known game values
        val damagePerUnit = getDamagePerUnit(selectedRaidTool, selectedWallType)
        if (damagePerUnit > 0) {
            val totalHealth = selectedWallType.health * wallCount
            calculatedQuantity = (totalHealth + damagePerUnit - 1) / damagePerUnit
        } else {
            calculatedQuantity = 0
        }
    }

    fun resetCalculation() {
        calculatedQuantity = null
        wallCount = 1
    }

    private fun getDamagePerUnit(tool: RaidTool, wall: WallType): Int {
        return when (tool) {
            RaidTool.SATCHEL -> when (wall) {
                WallType.TWIG -> 250
                WallType.WOOD -> 160
                WallType.STONE -> 75
                WallType.METAL -> 50
                WallType.ARMORED -> 25
                WallType.HQM_WALL -> 20
            }
            RaidTool.C4 -> when (wall) {
                WallType.TWIG -> 500
                WallType.WOOD -> 300
                WallType.STONE -> 200
                WallType.METAL -> 100
                WallType.ARMORED -> 50
                WallType.HQM_WALL -> 40
            }
            RaidTool.ROCKET -> when (wall) {
                WallType.TWIG -> 500
                WallType.WOOD -> 300
                WallType.STONE -> 200
                WallType.METAL -> 100
                WallType.ARMORED -> 50
                WallType.HQM_WALL -> 40
            }
            RaidTool.HV_ROCKET -> when (wall) {
                WallType.TWIG -> 200
                WallType.WOOD -> 150
                WallType.STONE -> 100
                WallType.METAL -> 50
                WallType.ARMORED -> 25
                WallType.HQM_WALL -> 20
            }
            RaidTool.INCENDIARY_ROCKET -> when (wall) {
                WallType.TWIG -> 250
                WallType.WOOD -> 180
                WallType.STONE -> 75
                WallType.METAL -> 40
                WallType.ARMORED -> 15
                WallType.HQM_WALL -> 10
            }
            RaidTool.EXPLOSIVE_AMMO -> when (wall) {
                WallType.TWIG -> 10
                WallType.WOOD -> 8
                WallType.STONE -> 4
                WallType.METAL -> 2
                WallType.ARMORED -> 1
                WallType.HQM_WALL -> 1
            }
            RaidTool.HANDMADE_SHELL -> when (wall) {
                WallType.TWIG -> 30
                WallType.WOOD -> 20
                WallType.STONE -> 10
                WallType.METAL -> 5
                WallType.ARMORED -> 3
                WallType.HQM_WALL -> 2
            }
            RaidTool.BEANCAN -> when (wall) {
                WallType.TWIG -> 100
                WallType.WOOD -> 60
                WallType.STONE -> 30
                WallType.METAL -> 15
                WallType.ARMORED -> 8
                WallType.HQM_WALL -> 5
            }
            RaidTool.TIMED_EXPLOSIVE -> when (wall) {
                WallType.TWIG -> 500
                WallType.WOOD -> 300
                WallType.STONE -> 200
                WallType.METAL -> 100
                WallType.ARMORED -> 50
                WallType.HQM_WALL -> 40
            }
            RaidTool.MLRS_ROCKET -> when (wall) {
                WallType.TWIG -> 800
                WallType.WOOD -> 500
                WallType.STONE -> 300
                WallType.METAL -> 150
                WallType.ARMORED -> 80
                WallType.HQM_WALL -> 60
            }
        }
    }
}

