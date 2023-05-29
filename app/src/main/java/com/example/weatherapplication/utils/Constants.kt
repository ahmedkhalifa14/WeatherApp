package com.example.weatherapplication.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.weatherapplication.R

object Constants {
    const val BASE_URL = "https://api.weatherapi.com/v1/"
    const val API_KEY = "3fa4124a5d334994a22150240232705"

    val userLocationKey = stringPreferencesKey("USER_LOCATION_KEY")

    const val LOCATION_DATA_STORE: String = "USER_LOCATION"



    val capitalCities = arrayOf(
        "Tokyo", "Delhi", "Beijing", "Moscow", "Berlin", "Paris", "Rome", "Madrid", "London", "Cairo",
        "Ankara", "Brasília", "Ottawa", "Washington", "Canberra", "Mexico City", "Buenos Aires",
        "Brussels", "Vienna", "Athens", "Amsterdam", "Stockholm", "Copenhagen", "Oslo", "Helsinki",
        "Dublin", "Lisbon", "Bern", "Warsaw", "Prague", "Budapest", "Belgrade", "Zagreb", "Sofia",
        "Riga", "Vilnius", "Tallinn", "Bucharest", "Skopje", "Reykjavik", "Hanoi", "Bangkok", "Singapore",
        "Kuala Lumpur", "Jakarta", "Manila", "Seoul", "Pyongyang", "Taipei", "New Delhi", "Islamabad",
        "Colombo", "Dhaka", "Kathmandu", "Tehran", "Baghdad", "Riyadh", "Cairo", "Nairobi", "Addis Ababa",
        "Johannesburg", "Pretoria", "Cape Town", "Accra", "Abuja", "Nouakchott", "Rabat", "Cotonou",
        "Dakar", "Lusaka", "Luanda", "Kinshasa", "Harare", "Gaborone", "Kigali", "Brazzaville", "Bamako"
    )
}