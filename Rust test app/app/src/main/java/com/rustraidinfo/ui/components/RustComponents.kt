package com.rustraidinfo.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rustraidinfo.ui.theme.*

@Composable
fun RustBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(RustDarker, RustDark, RustDarker)
                )
            )
    ) {
        // Animated background particles
        val infiniteTransition = rememberInfiniteTransition()
        val alpha by infiniteTransition.animateFloat(
            initialValue = 0.02f,
            targetValue = 0.08f,
            animationSpec = infiniteRepeatable(
                animation = tween(3000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        // Decorative grid lines
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(alpha = alpha)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(RustAccentRed.copy(alpha = 0.1f), Color.Transparent)
                    )
                )
        )
        content()
    }
}

@Composable
fun RustCard(
    modifier: Modifier = Modifier,
    glowColor: Color = RustRed.copy(alpha = 0.3f),
    content: @Composable () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Card(
        modifier = modifier
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .border(
                1.dp,
                glowColor.copy(alpha = glowAlpha),
                RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = RustCardBg.copy(alpha = 0.9f)
        )
    ) {
        content()
    }
}

@Composable
fun RustButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(52.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = RustRed,
            contentColor = Color.White,
            disabledContainerColor = RustDarkRed.copy(alpha = 0.5f),
            disabledContentColor = Color.Gray
        ),
        enabled = enabled
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.White,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DifficultyBadge(
    difficulty: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        targetValue = color.copy(alpha = 0.2f),
        animationSpec = tween(300)
    )

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = backgroundColor
    ) {
        Text(
            text = difficulty,
            color = color,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun StatItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = RustGold,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = RustTextSecondary
        )
    }
}

@Composable
fun CounterControl(
    value: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    modifier: Modifier = Modifier,
    label: String = ""
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (label.isNotBlank()) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyMedium,
                color = RustTextPrimary,
                modifier = Modifier.weight(1f)
            )
        }

        FilledIconButton(
            onClick = onDecrement,
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(10.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = RustDarkRed,
                contentColor = Color.White
            )
        ) {
            Text("-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Text(
            text = "$value",
            style = MaterialTheme.typography.titleLarge,
            color = RustGold,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(40.dp),
            textAlign = TextAlign.Center
        )

        FilledIconButton(
            onClick = onIncrement,
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(10.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = RustRed,
                contentColor = Color.White
            )
        ) {
            Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun AnimatedRustLogo(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .graphicsLayer(scaleX = scale, scaleY = scale)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(RustRed, RustDarkRed)
                    )
                )
                .shadow(
                    elevation = 16.dp,
                    shape = CircleShape,
                    ambientColor = RustRed.copy(alpha = glowAlpha),
                    spotColor = RustRed.copy(alpha = glowAlpha)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "RUST RAID INFO",
            style = MaterialTheme.typography.headlineMedium,
            color = RustRed,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Raid Calculator & Guide",
            style = MaterialTheme.typography.bodySmall,
            color = RustTextSecondary
        )
    }
}

@Composable
fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    action: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = RustTextPrimary,
            fontWeight = FontWeight.Bold
        )
        action?.invoke()
    }
}

