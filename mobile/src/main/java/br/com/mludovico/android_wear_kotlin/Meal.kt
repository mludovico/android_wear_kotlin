package br.com.mludovico.android_wear_kotlin

data class Meal(
    val title: String,
    val calories: Int,
    val ingredients: List<String>,
    val favorite: Boolean
)
