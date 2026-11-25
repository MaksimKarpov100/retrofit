package com.example.retrofit.ui.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.R

@Composable
fun InputFieldText(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 66.dp)
            .shadow(16.dp, RoundedCornerShape(4.dp), ambientColor = Color(0x338A959E), spotColor = Color(0x338A959E))
            .background(Color(0xFFFFFFFF), RoundedCornerShape(4.dp))
            .padding(start = 16.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp)
        ) {
            SecondaryText(
                text = label,
                fontSize = 12.sp,
                color = Color(0xFF808080)
            )

            Spacer(modifier = Modifier.height(3.dp))
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                visualTransformation = visualTransformation,
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    color = Color(0xFF242424),
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.nunito_sans)),
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        if (trailingIcon != null) {
            trailingIcon()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InputFieldTextPrev() {
    var text by remember { mutableStateOf("bruno203@gmail.com") }
    InputFieldText(
        value = text,
        label = "Email",
        onValueChange = { text = it }
    )
}
