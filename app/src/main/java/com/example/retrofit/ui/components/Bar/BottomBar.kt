package com.example.retrofit.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.retrofit.R
import com.example.retrofit.ui.theme.RetrofitTheme

@Composable
fun BottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val activeIcons = listOf(
        R.drawable.home_active,
        R.drawable.marker_active,
        R.drawable.bell_active,
        R.drawable.profile_active
    )

    val inactiveIcons = listOf(
        R.drawable.home_inactive,
        R.drawable.marker_inactive,
        R.drawable.bell_inactive,
        R.drawable.profile_inactive
    )

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(75.dp),
        color = Color.White,
        tonalElevation = 0.dp,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (index in 0..3) {
                val iconRes = if (selectedTab == index) activeIcons[index] else inactiveIcons[index]
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onTabSelected(index) }
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomBarPreview() {
    RetrofitTheme {
        BottomBar(
            selectedTab = 0,
            onTabSelected = {}
        )
    }
}
