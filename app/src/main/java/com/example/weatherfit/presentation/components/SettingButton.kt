package com.example.weatherfit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherfit.R
import com.example.weatherfit.presentation.modifiers.customShadow
import com.example.weatherfit.presentation.theme.LightColorScheme

@Composable
fun SettingButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    val shadowColor: Color = if (MaterialTheme.colorScheme == LightColorScheme) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.background
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .customShadow(
                shadowColor = shadowColor,
                alpha = 0.4f,
                shape = RoundedCornerShape(14.dp)
            )
            .clip(RoundedCornerShape(14.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(
                horizontal = 15.dp,
                vertical = 10.dp
            )
            .clickable(
                onClick = { onClick() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )

        Icon(
            modifier = Modifier.rotate(180f),
            painter = painterResource(R.drawable.interface_arrow_icon),
            contentDescription = stringResource(R.string.arrow_image_description),
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}