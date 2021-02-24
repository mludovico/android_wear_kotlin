package br.com.mludovico.android_wear_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MealListActivity : AppCompatActivity(), MealLisAdapter.Callback {

    private var adapter: MealLisAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meals = MealStore.fetchMeals(this)
        adapter = MealLisAdapter(meals, this)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this)
    }

    override fun mealClicked(meal: Meal) {
        TODO("Not yet implemented")
    }


}