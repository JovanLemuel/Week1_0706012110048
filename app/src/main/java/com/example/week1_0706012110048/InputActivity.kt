package com.example.week1_0706012110048

import Database.GlobalVar
import Model.Model
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.week1_0706012110048.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {

    private lateinit var animal:Model
    private lateinit var binding:ActivityInputBinding
    private var position = -1
    var tempUri:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        GetIntent()
        listener()
    }

    private fun listener(){
        binding.createButton.setOnClickListener {
            val name = binding.nameInput.editText?.text.toString().trim()
            val type = binding.typeInput.editText?.text.toString().trim()
            val age = binding.ageInput.editText?.text.toString().trim()
            animal = Model(name, type, age)
            checker()
        }

        binding.backButton.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
    }


    private fun GetIntent(){
        position = intent.getIntExtra("position",-1)
        if(position!=-1){
            binding.textView4.setText("Edit Hewan")
            binding.createButton.setText("Save")
            val tempMov = GlobalVar.listData[position]
            Display(tempMov)
        }
    }

    private fun Display(movie:Model?){
        binding.nameInput.editText?.setText((movie?.name))
        binding.typeInput.editText?.setText((movie?.type))
        binding.ageInput.editText?.setText((movie?.age))

    }

    private fun checker(){
        var isCompleted:Boolean = true
        if(animal.name!!.isEmpty()){
            binding.nameInput.error = "Nama harus diisi."
            isCompleted = false
        }else{
            binding.nameInput.error = ""
        }

        if(animal.age!!.isEmpty()){
            binding.ageInput.error = "Usia harus diisi."
            isCompleted=false
        }else if(animal.age!!.contains(".*[A-Z].*".toRegex())){
            binding.ageInput.error = "Harap masukkan angka."
            isCompleted=false
        }else if(animal.age!!.contains(".*[a-z].*".toRegex())){
            binding.ageInput.error = "Harap masukkan angka."
            isCompleted=false
        }

        if(animal.type!!.isEmpty()){
            binding.typeInput.error = "Jenis harus diisi."
            isCompleted=false
        }else{
            binding.typeInput.error = ""
        }

        if(isCompleted){
            if(position==-1){
                animal.imageUri= tempUri
                GlobalVar.listData.add(animal)
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                val myIntent = Intent(this,MainActivity::class.java)
                startActivity(myIntent)
            }
            else{
                if(tempUri== GlobalVar.listData[position].imageUri){
                    animal.imageUri = GlobalVar.listData[position].imageUri
                }else if(tempUri==""){
                    animal.imageUri = GlobalVar.listData[position].imageUri
                }else{
                    animal.imageUri = tempUri
                }
                GlobalVar.listData[position]=animal
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                val myIntent = Intent(this,MainActivity::class.java)
                startActivity(myIntent)
            }
            finish()
        }
    }
}