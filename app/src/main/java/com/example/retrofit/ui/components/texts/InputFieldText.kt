package com.example.retrofit.ui.components.texts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.retrofit.R
import com.example.retrofit.ui.theme.Black3
import com.example.retrofit.ui.theme.Gray

@Composable
fun InputFieldText(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation,
    placeholder: String,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Название поля (Email / Password) — прижато к левому краю
        Text(
            text = label,
            color = Gray,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.nunito_sans))
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Поле ввода без системных внутренних отступов Material по бокам
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            visualTransformation = visualTransformation,
            modifier = Modifier.fillMaxWidth(),
            textStyle = androidx.compose.ui.text.TextStyle(
                color = Black3,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.nunito_sans))
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = Gray.copy(alpha = 0.5f),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.nunito_sans))
                            )
                        }
                        innerTextField() // Сам вводимый текст — встает четко с левого края
                    }
                    if (trailingIcon != null) {
                        trailingIcon()
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Тонкая горизонтальная линия подчеркивания (Rectangle 6 в Figma)
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp,
            color = Color(0xFFE0E0E0)
        )
    }
}

@Preview(showSystemUi = true, showBackground = false)
@Composable
private fun InputFieldTextPrev() {
    var textState by remember { mutableStateOf("") }
    InputFieldText(
        value = textState,
        onValueChange = { textState = it },
        label = "Вход по email",
        placeholder = "example@mail.com",
        visualTransformation = VisualTransformation.None
    )

}