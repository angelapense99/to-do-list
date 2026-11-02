package com.example.to_do_list.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFD6A6B5),       // Altrosa
    secondary = Color(0xFFB0A6AA),     // Warmes Grau
    tertiary = Color(0xFFC9B8C0),      // Helles Rosé-Grau

    background = Color(0xFF1C1B1D),    // Dunkles Anthrazit
    surface = Color(0xFF29272A),       // Dunkelgrau

    onPrimary = Color(0xFF30252A),     // Dunkles Braun-Grau
    onSecondary = Color(0xFF292629),  // Dunkelgrau
    onTertiary = Color(0xFF30272C),    // Dunkles Grau-Rosé
    onBackground = Color(0xFFEAE6E8),  // Helles Grau-Rosé
    onSurface = Color(0xFFEAE6E8)      // Helles Grau-Rosé
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF987681),       // Gedämpftes Altrosa
    secondary = Color(0xFF898286),     // Mittelgrau
    tertiary = Color(0xFFC49BA9),      // Zartes Rosé

    background = Color(0xFFF0EFF0),    // Sehr helles Grau
    surface = Color(0xFFFAF8F9),       // Fast Weiß mit Roséstich

    onPrimary = Color.White,           // Weiß
    onSecondary = Color.White,         // Weiß
    onTertiary = Color.White,          // Weiß
    onBackground = Color(0xFF302D30),  // Dunkles Grau
    onSurface = Color(0xFF302D30)      // Dunkles Grau
)

@Composable
fun TodolistTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}