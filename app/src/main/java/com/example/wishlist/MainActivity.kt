package com.example.wishlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlist.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val nutritionlists= mutableListOf<Fitbitentity>()
    private lateinit var nutritionRv: RecyclerView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        nutritionRv = findViewById(R.id.fitbitRv)
        val nutritionAdapter = NutritionAdapter(this, nutritionlists)
        nutritionRv.adapter = nutritionAdapter
        lifecycleScope.launch{
            (application as MyApplication).db.FitBitDao().getAll().collect{databaseList->
                databaseList.map{ mappedList ->
                    nutritionlists.addAll(listOf(mappedList))
                    nutritionAdapter.notifyDataSetChanged()
                }
            }
        }
        nutritionRv.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            nutritionRv.addItemDecoration(dividerItemDecoration)
        }



        val add = findViewById<Button>(R.id.entrybutton)
        add.setOnClickListener{
            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(intent)
        }
        nutritionAdapter.setOnItemClickListener(object: NutritionAdapter.OnItemClickListener{
            override fun onItemClick(position: Int){
                Toast.makeText(this@MainActivity,"Item removed at position $position", Toast.LENGTH_LONG).show()
                nutritionlists.removeAt(position)
                nutritionAdapter.notifyItemRemoved(position)
            }
        })


    }
}