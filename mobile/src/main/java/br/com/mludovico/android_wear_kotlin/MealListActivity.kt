package br.com.mludovico.android_wear_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shared.Meal
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.wearable.Node
import com.google.android.gms.wearable.Wearable
import com.google.gson.Gson

class MealListActivity : AppCompatActivity(), MealLisAdapter.Callback, GoogleApiClient.ConnectionCallbacks {

    private var adapter: MealLisAdapter? = null
    private lateinit var client: GoogleApiClient
    private var connectedNode: List<Node>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meals = MealStore.fetchMeals(this)
        adapter = MealLisAdapter(meals, this)
        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this)

        client = GoogleApiClient.Builder(this)
            .addApi(Wearable.API)
            .addConnectionCallbacks(this)
            .build()
        client.connect()
    }

    override fun mealClicked(meal: Meal) {
        Log.i("Mobile", "Meal cleicked ${meal.title}")
        val gson = Gson()
        connectedNode?.forEach { node ->
            val bytes = gson.toJson(meal).toByteArray()
            Wearable.MessageApi.sendMessage(client, node.id, "/meal", bytes)
        }
    }

    override fun onConnected(bundle: Bundle?) {
        Wearable.NodeApi.getConnectedNodes(client).setResultCallback {
            connectedNode = it.nodes
        }
    }

    override fun onConnectionSuspended(id: Int) {
        connectedNode = null
    }


}