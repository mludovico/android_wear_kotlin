package br.com.mludovico.wear

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.shared.Meal
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_meal.*

class MealActivity : Activity(), GoogleApiClient.ConnectionCallbacks {

    private lateinit var client: GoogleApiClient
    private var currentMeal: Meal? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
    }

    override fun onConnected(bundle: Bundle?) {
        Wearable.MessageApi.addListener(client) { messageEvent ->
            currentMeal = Gson().fromJson(String(messageEvent.data), Meal::class.java)
            updateView()
        }
    }

    private fun updateView() {
        meal_title.text = currentMeal?.title
        meal_calories.text = currentMeal?.calories.toString()
        meal_ingredients.text = currentMeal?.ingredients?.joinToString(separator = ",\n")
    }

    override fun onConnectionSuspended(id: Int) {
        Log.i("Wear", "Connection suspended")
    }
}