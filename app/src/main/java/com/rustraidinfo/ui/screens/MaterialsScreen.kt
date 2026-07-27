package com.rustraidinfo.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rustraidinfo.ui.components.*
import com.rustraidinfo.ui.theme.*

data class MaterialInfo(
    val name: String,
    val description: String,
    val icon: String,
    val category: String,
    val stackSize: Int,
    val color: Color
)

private val materialsList = listOf(
    MaterialInfo(
        name = "Sulfur",
        description = "Primary resource for creating gunpowder and explosives. Mined from sulfur nodes.",
        icon = "🟡",
        category = "Resources",
        stackSize = 1000,
        color = Color(0xFFF1C40F)
    ),
    MaterialInfo(
        name = "Gunpowder",
        description = "Crafted from sulfur and charcoal. Used in ammunition and explosives.",
        icon = "💥",
        category = "Crafting",
        stackSize = 1000,
        color = Color(0xFF8E8E8E)
    ),
    MaterialInfo(
        name = "Explosives",
        description = "Crafted from gunpowder, sulfur, and metal fragments. Required for C4 and rockets.",
        icon = "🧨",
        category = "Explosives",
        stackSize = 10,
        color = Color(0xFFCD412D)
    ),
    MaterialInfo(
        name = "Metal Fragments",
        description = "Smelted from metal ore. Used in crafting tools, weapons, and explosives.",
        icon = "⚙️",
        category = "Resources",
        stackSize = 1000,
        color = Color(0xFFC0C0C0)
    ),
    MaterialInfo(
        name = "High Quality Metal",
        description = "Rare resource smelted from HQM ore. Used in high-tier crafting.",
        icon = "💎",
        category = "Resources",
        stackSize = 100,
        color = Color(0xFFFFD700)
    ),
    MaterialInfo(
        name = "Charcoal",
        description = "Byproduct from wood burning in campfires/furnaces. Used in gunpowder.",
        icon = "⬛",
        category = "Resources",
        stackSize = 1000,
        color = Color(0xFF2F4F4F)
    ),
    MaterialInfo(
        name = "Wood",
        description = "Gathered from trees. Used for building, barricades, and campfires.",
        icon = "🪵",
        category = "Resources",
        stackSize = 1000,
        color = Color(0xFF8B4513)
    ),
    MaterialInfo(
        name = "Stone",
        description = "Mined from stone nodes. Used for building stone walls and foundations.",
        icon = "🪨",
        category = "Resources",
        stackSize = 1000,
        color = Color(0xFF808080)
    ),
    MaterialInfo(
        name = "Low Grade Fuel",
        description = "Crafted from animal fat. Used for furnaces, lanterns, and flamethrowers.",
        icon = "🛢️",
        category = "Resources",
        stackSize = 500,
        color = Color(0xFFE67E22)
    ),
    MaterialInfo(
        name = "Scrap",
        description = "Found across the map. Used for researching, crafting, and trade.",
        icon = "🔩",
        category = "Resources",
        stackSize = 1000,
        color = Color(0xFF3498DB)
    ),
    MaterialInfo(
        name = "Satchel Charge",
        description = "Timer-based explosive. Cheap but unreliable. 4 for a stone wall.",
        icon = "🎒",
        category = "Explosives",
        stackSize = 10,
        color = Color(0xFFE74C3C)
    ),
    MaterialInfo(
        name = "C4 Explosive",
        description = "Remote detonated explosive. Most reliable raiding tool. 2 for metal wall.",
        icon = "💣",
        category = "Explosives",
        stackSize = 10,
        color = Color(0xFFCD412D)
    ),
    MaterialInfo(
        name = "Rocket",
        description = "Fired from rocket launcher. High damage with splash. 4 for armored wall.",
        icon = "🚀",
        category = "Explosives",
        stackSize = 10,
        color = Color(0xFFE67E22)
    ),
    MaterialInfo(
        name = "Explosive 5.56 Rifle Ammo",
        description = "High-caliber explosive ammo. Good for soft-side raiding.",
        icon = "🔫",
        category = "Ammunition",
        stackSize = 128,
        color = Color(0xFFF39C12)
    )
)

@Composable
fun MaterialsScreen() {
    RustBackground { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            SectionHeader(title = "Materials & Resources")

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Categories
                val categories = materialsList.groupBy { it.category }
                
                categories.forEach { (category, materials) ->
                    item {
                        Text(
                            text = category,
                            style = MaterialTheme.typography.titleSmall,
                            color = RustGold,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    items(materials) { material ->
                        MaterialListItem(material = material)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
private fun MaterialListItem(
    material: MaterialInfo
) {
    RustCard(
        modifier = Modifier.fillMaxWidth(),
        glowColor = material.color.copy(alpha = 0.3f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        material.color.copy(alpha = 0.15f),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = material.icon,
                    fontSize = 24.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = material.name,
                        style = MaterialTheme.typography.titleSmall,
                        color = RustTextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = RustDarker
                    ) {
                        Text(
                            text = "x${material.stackSize}",
                            style = MaterialTheme.typography.labelSmall,
                            color = RustTextSecondary,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = material.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = RustTextSecondary
                )
            }
        }
    }
}

