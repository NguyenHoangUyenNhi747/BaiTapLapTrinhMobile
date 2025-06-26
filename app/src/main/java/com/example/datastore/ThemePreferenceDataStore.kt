import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_preferences")

class ThemePreferenceManager(private val context: Context) {

    companion object {
        val THEME_COLOR_KEY = stringPreferencesKey("theme_color")
    }

    suspend fun saveThemeColor(color: String) {
        context.themeDataStore.edit { preferences ->
            preferences[THEME_COLOR_KEY] = color
        }
    }

    val themeColor: Flow<String?> = context.themeDataStore.data
        .map { preferences ->
            preferences[THEME_COLOR_KEY]
        }
}