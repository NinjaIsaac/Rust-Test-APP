package com.rustraidinfo.data.models

import androidx.compose.ui.graphics.Color

/**
 * Represents a building material type in Rust
 */
enum class BuildingMaterial(val displayName: String, val color: Color) {
    WOOD("Wood", Color(0xFF8B4513)),
    STONE("Stone", Color(0xFF808080)),
    METAL("Sheet Metal", Color(0xFFC0C0C0)),
    ARMORED("Armored", Color(0xFF2F4F4F)),
    HQM("High Quality Metal", Color(0xFFFFD700))
}

/**
 * Represents an explosive/raid tool in Rust
 */
enum class RaidTool(val displayName: String, val explosionType: ExplosionType) {
    SATCHEL("Satchel Charge", ExplosionType.EXPLOSIVE),
    C4("C4 Explosive", ExplosionType.EXPLOSIVE),
    ROCKET("Rocket", ExplosionType.EXPLOSIVE),
    HV_ROCKET("High Velocity Rocket", ExplosionType.EXPLOSIVE),
    INCENDIARY_ROCKET("Incendiary Rocket", ExplosionType.INCENDIARY),
    EXPLOSIVE_AMMO("Explosive 5.56", ExplosionType.EXPLOSIVE),
    HANDMADE_SHELL("Handmade Shell", ExplosionType.EXPLOSIVE),
    BEANCAN("Beancan Grenade", ExplosionType.EXPLOSIVE),
    TIMED_EXPLOSIVE("Timed Explosive", ExplosionType.EXPLOSIVE),
    MLRS_ROCKET("MLRS Rocket", ExplosionType.EXPLOSIVE)
}

enum class ExplosionType(val displayName: String) {
    EXPLOSIVE("Explosive"),
    INCENDIARY("Incendiary")
}

/**
 * Data class for raid cost calculation results
 */
data class RaidCost(
    val buildingMaterial: BuildingMaterial,
    val wallType: WallType,
    val raidTool: RaidTool,
    val quantityNeeded: Int,
    val totalCostInResources: Map<RaidResource, Int>,
    val totalCostInScrap: Int = 0
) {
    data class RaidResource(
        val name: String,
        val icon: String = ""
    )
}

enum class WallType(val displayName: String, val health: Int) {
    TWIG("Twig", 10),
    WOOD("Wood", 250),
    STONE("Stone", 500),
    METAL("Metal", 1000),
    ARMORED("Armored", 2000),
    HQM_WALL("HQM Wall", 2500)
}

/**
 * Damage values for each tool against different materials
 */
data class DamageInfo(
    val toolName: String,
    val damages: Map<WallType, Int> // damage per unit
)

/**
 * Complete raid info data
 */
data class RaidInfo(
    val id: Int,
    val title: String,
    val description: String,
    val difficulty: RaidDifficulty,
    val requiredItems: List<RaidItem>,
    val estimatedCost: String,
    val tips: List<String>
)

enum class RaidDifficulty(val displayName: String, val color: Color) {
    EASY("Easy", Color(0xFF2ECC71)),
    MEDIUM("Medium", Color(0xFFF39C12)),
    HARD("Hard", Color(0xFFE74C3C)),
    EXPERT("Expert", Color(0xFF8E44AD))
}

data class RaidItem(
    val name: String,
    val quantity: Int,
    val icon: String = ""
)

/**
 * Predefined raid strategies
 */
val raidStrategies = listOf(
    RaidInfo(
        id = 1,
        title = "2x1 Stone Raid",
        description = "Quick raid on a basic 2x1 stone base using satchels",
        difficulty = RaidDifficulty.EASY,
        requiredItems = listOf(
            RaidItem("Satchel Charge", 4),
            RaidItem("Explosives", 240)
        ),
        estimatedCost = "~800 Sulfur",
        tips = listOf(
            "Target door first, then walls",
            "Bring wood for barricades",
            "Watch for counter-raiders"
        )
    ),
    RaidInfo(
        id = 2,
        title = "Metal Base Raid",
        description = "Medium raid on a metal 2x2 base with C4",
        difficulty = RaidDifficulty.MEDIUM,
        requiredItems = listOf(
            RaidItem("C4 Explosive", 4),
            RaidItem("Explosives", 880)
        ),
        estimatedCost = "~2,200 Sulfur",
        tips = listOf(
            "Use rockets for efficiency",
            "Bring meds and armor",
            "Check for tool cupboards"
        )
    ),
    RaidInfo(
        id = 3,
        title = "Armored Core Raid",
        description = "Hard raid on armored base core using rockets",
        difficulty = RaidDifficulty.HARD,
        requiredItems = listOf(
            RaidItem("Rocket", 8),
            RaidItem("Explosives", 1400)
        ),
        estimatedCost = "~4,000 Sulfur",
        tips = listOf(
            "Coordinate with team",
            "Bring multiple launchers",
            "Prepare for long fight"
        )
    ),
    RaidInfo(
        id = 4,
        title = "Massive Compound Raid",
        description = "Expert raid on a large compound with HQM walls",
        difficulty = RaidDifficulty.EXPERT,
        requiredItems = listOf(
            RaidItem("C4 Explosive", 20),
            RaidItem("Rocket", 40),
            RaidItem("Satchel Charge", 30)
        ),
        estimatedCost = "~15,000+ Sulfur",
        tips = listOf(
            "Full team coordination required",
            "Bring supply drops",
            "Establish raid base nearby",
            "Expect heavy resistance"
        )
    ),
    RaidInfo(
        id = 5,
        title = "Door Raid (Soft Side)",
        description = "Quick door raid using explosive ammo on soft side",
        difficulty = RaidDifficulty.EASY,
        requiredItems = listOf(
            RaidItem("Explosive 5.56", 48),
            RaidItem("Gunpowder", 480)
        ),
        estimatedCost = "~600 Sulfur",
        tips = listOf(
            "Aim for door soft side",
            "Use silenced weapons",
            "Quick in and out"
        )
    ),
    RaidInfo(
        id = 6,
        title = "Efficient Rocket Raid",
        description = "Optimized rocket raid using with splash damage",
        difficulty = RaidDifficulty.MEDIUM,
        requiredItems = listOf(
            RaidItem("Rocket", 6),
            RaidItem("Incendiary Rocket", 4)
        ),
        estimatedCost = "~2,800 Sulfur",
        tips = listOf(
            "Rockets splash through walls",
            "Fire from distance",
            "Watch for roof campers"
        )
    )
)

