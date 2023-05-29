package com.example.weatherapplication.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapplication.utils.Constants.LOCATION_DATA_STORE
import com.example.weatherapplication.utils.Constants.userLocationKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


private val Context.dataStore by preferencesDataStore(LOCATION_DATA_STORE)
class DataStoreManager(context: Context) {
    private val locationDataStore  =context.dataStore
    private val _userLocation = MutableLiveData<String>()
    val userLocation: LiveData<String> get() = _userLocation
    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    suspend fun setUserLocation(userLocation: String) {
        locationDataStore.edit { preferences ->
            preferences[userLocationKey] = userLocation
        }
    }

    val location:Flow<String> = locationDataStore.data.map {pref ->
        pref[userLocationKey]?:""
    }

    init {
        getLocation()
    }
    private fun getLocation() {
        scope.launch {
         location.collect { loc ->
               _userLocation.postValue(loc)
            }
        }

    }

}