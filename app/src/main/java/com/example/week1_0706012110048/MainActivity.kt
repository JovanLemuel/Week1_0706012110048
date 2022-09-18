package com.example.week1_0706012110048

import Adapter.RVAdapter
import Database.GlobalVar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week1_0706012110048.databinding.ActivityMainBinding
import Database.GlobalVar.Companion.listData
import Interface.CardListener
import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week1_0706012110048.databinding.CardviewBinding
import com.google.android.gms.ads.mediation.Adapter

class MainActivity : AppCompatActivity(), CardListener {

    private val adapter=RVAdapter(GlobalVar.listData,this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setupRecyclerView()
        hidetext()
        listener()
    }

    private fun listener(){
        binding.floatingActionButton.setOnClickListener {
            val myIntent = Intent(this, InputActivity::class.java)
            startActivity(myIntent)
        }

    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun hidetext(){
        if(listData.isEmpty()){
            binding.addTextview.isVisible = true
        } else {
            binding.addTextview.isInvisible = true
        }
    }

    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(baseContext)
        binding.mainRV.layoutManager= layoutManager //Set layout
        binding.mainRV.adapter=adapter //Set adapter
    }


}
