package br.com.mludovico.wear

import android.app.Activity
import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class MealActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
    }
}