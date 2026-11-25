package com.example.retrofit.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.R
import com.example.retrofit.ui.components.text.PrimaryText
import com.example.retrofit.ui.theme.RetrofitTheme

@Composable
fun HeaderBar(
    title: String,
    modifier: Modifier = Modifier,
    leftIconRes: Int? = null,
    onLeftClick: () -> Unit = {},
    rightIconRes: Int? = null,
    onRightClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(Color.White)
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (leftIconRes != null) {
            Icon(
                painter = painterResource(id = leftIconRes),
                contentDescription = "Left action",
                tint = Color(0xFF242424),
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onLeftClick
                    )
            )
        } else {
            Spacer(modifier = Modifier.size(20.dp))
        }

        PrimaryText(
            text = title,
            color = Color(0xFF303030),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp,
            fontFamily = FontFamily(Font(R.font.merriweather)),
            )

        if (rightIconRes != null) {
            Icon(
                painter = painterResource(id = rightIconRes),
                contentDescription = "Right action",
                tint = Color(0xFF242424),
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onRightClick
                    )
            )
        } else {
            Spacer(modifier = Modifier.size(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderBarPreview() {
    RetrofitTheme {
        HeaderBar(
            title = "Profile",
            leftIconRes = R.drawable.search_icon,
            rightIconRes = R.drawable.logout_icon
        )
    }
}
