package br.com.mludovico.wear

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class MealActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        // Enables Always-on
        setAmbientEnabled()
    }
}