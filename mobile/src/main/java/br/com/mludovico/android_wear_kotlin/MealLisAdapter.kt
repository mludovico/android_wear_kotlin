package br.com.mludovico.android_wear_kotlin

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shared.Meal
import kotlinx.android.synthetic.main.adapter_meal.view.*

class MealLisAdapter(
    private val meals: MutableList<Meal>,
    private val callback: Callback?
): RecyclerView.Adapter<MealListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_meal, parent, false)
        return MealListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealListViewHolder, position: Int) {
        val meal = meals[position]
        holder.itemView.title.text = meal.title
        holder.itemView.ingredients.text = meal.ingredients.joinToString(separator = ", ")
        holder.itemView.calories.text = meal.calories.toString()
        holder.itemView.star.visibility = if (meal.favorite) View.VISIBLE else View.INVISIBLE
        holder.itemView.setOnClickListener {
            Log.i("Adapter", "Meal clicked ${meal.title}")
            callback?.mealClicked(meal)
        }
    }

    override fun getItemCount(): Int = meals.size

    interface Callback {
        fun mealClicked(meal: Meal)
    }
}

class MealListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)