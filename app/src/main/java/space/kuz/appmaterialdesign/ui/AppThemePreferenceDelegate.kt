package space.kuz.appmaterialdesign.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import space.kuz.appmaterialdesign.R

private const val NAME_SHARED_PREFERENCE = "APP_THEME"
private const val APP_THEME_KEY = "APP_THEME_KEY"
private const val ONE_THEME = R.style.ThemeOne
private const val TWO_THEME = R.style.ThemeTwo

class AppThemePreferenceDelegate(context: Context) {

    private val theme = context.getSharedPreferences(NAME_SHARED_PREFERENCE, AppCompatActivity.MODE_PRIVATE)

    fun savedThemeToStyleId(savedThemeNumber: Int): Int {
        return when (savedThemeNumber) {
            ONE_THEME -> R.style.ThemeTwo
            TWO_THEME -> R.style.ThemeOne
            else -> R.style.ThemeOne
        }
    }

    fun getSavedTheme(): Int {
        return theme.getInt(APP_THEME_KEY, ONE_THEME)
    }

    fun setSavedTheme(themeNumber: Int) {
      theme.edit().putInt(APP_THEME_KEY, themeNumber).apply()
    }
}