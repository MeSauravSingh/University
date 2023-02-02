package com.example.universities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.universities.databinding.ActivityMainBinding
import com.example.universities.retrofit.Api
import com.example.universities.retrofit.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val myAdapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val list = listOf("United States","China","Japan","Germany","United Kingdom","India","France","Italy","Canada","South Korea")
            val meterStatusAdapter = ArrayAdapter(this@MainActivity, R.layout.list_item, list)

            countryDropDown.apply {
                setAdapter(meterStatusAdapter)

                setOnItemClickListener { parent, view, position, id ->
                   val myData = parent.adapter.getItem(position).toString().trim()
                    fetchFromServer(myData)
                    progressBar.visibility = View.VISIBLE
                }
            }

            recyclerView.apply {
                adapter = myAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
            }

        }


    }
    private fun fetchFromServer(query:String){
        val quotesApi = RetrofitHelper.getInstance().create(Api::class.java)
        GlobalScope.launch {
            val result = quotesApi.getUniversities(query)
            if (result.isSuccessful) {
                val myList = result.body() as List<University>
                Log.d("ayush: ", result.body().toString())
                myAdapter.submitList(result.body())
                binding.progressBar.visibility = View.INVISIBLE

                runOnUiThread {
                    if (myList.isEmpty()) binding.notAvailable.visibility = View.VISIBLE else binding.notAvailable.visibility = View.INVISIBLE
                }

            }

        }
    }

}