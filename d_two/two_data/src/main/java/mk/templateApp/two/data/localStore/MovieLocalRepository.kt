package mk.templateApp.two.data.localStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.json.Json
import mk.templateApp.two.data.localStore.MovieLocalRepositoryImpl.PreferencesKeys.FAVOURITE_MOVIE
import mk.templateApp.two.domain.model.Movie
import org.json.JSONArray
import javax.inject.Inject


interface MovieLocalRepository {

    val favMovies: Flow<List<Int>>

    suspend fun setFavMovie(movie: Movie)

    fun cachedMovies(): List<Movie>?
    fun cacheMovies(movies: List<Movie>)
    val hasCachedMovies: Boolean

}

class MovieLocalRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : MovieLocalRepository {

    private var allMovies: List<Movie>? = null

    override fun cacheMovies(movies: List<Movie>) {
        allMovies = movies
    }

    override val hasCachedMovies: Boolean
        get() = allMovies != null

    override fun cachedMovies(): List<Movie>? = allMovies

    override val favMovies: Flow<List<Int>> = dataStore.data.mapNotNull { prefs: Preferences ->
        prefs[FAVOURITE_MOVIE]?.let { json ->
            Json.decodeFromString(json)
        }
    }

    override suspend fun setFavMovie(movie: Movie) {
        dataStore.edit { prefs ->
            val array = JSONArray()
            val oldJson: String? = prefs[FAVOURITE_MOVIE]
            if (oldJson == null) {
                array.put(movie.id)
            } else {
                var foundFavourite: Boolean = false
                Json.decodeFromString<List<Int>>(oldJson).map { movieId ->
                    if (movie.id == movieId) {
                        foundFavourite = true
                    } else {
                        array.put(movieId)
                    }
                }

                if (!foundFavourite) {
                    array.put(movie.id)
                }
            }
            prefs[FAVOURITE_MOVIE] = array.toString()
        }
    }

    private object PreferencesKeys {
        val FAVOURITE_MOVIE: Preferences.Key<String> = stringPreferencesKey("fav_movie_ids")
    }
}
