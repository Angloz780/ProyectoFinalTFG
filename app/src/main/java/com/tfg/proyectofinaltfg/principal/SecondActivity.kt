package com.tfg.proyectofinaltfg.principal

import android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tfg.proyectofinaltfg.databinding.ActivitySecondBinding
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_second.view.*


class SecondActivity: AppCompatActivity(){

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(com.tfg.proyectofinaltfg.R.layout.activity_second)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = Firebase.database("https://ateneatfg-default-rtdb.europe-west1.firebasedatabase.app/").reference

        database.child("peliculas").child("informacion").child("1").child("nombre")
            .get().addOnSuccessListener {
                binding.nombre.text = it.value.toString()
                binding.lineartotal.visibility = View.VISIBLE
            }

        val videoView = findViewById<VideoView>(com.tfg.proyectofinaltfg.R.id.trailer)
        val videoPath = "android.resource://" + packageName + "/" + com.tfg.proyectofinaltfg.R.raw.piedra_filisofal
        val uri = Uri.parse(videoPath)
        videoView.setVideoURI(uri)

        val mediaController = MediaController(this)
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)

        binding.reproduccir.setOnClickListener{
            showAlert()
        }

        database.child("peliculas").child("informacion").child("1").child("descripcion")
            .get().addOnSuccessListener {
                binding.descripcion.text = it.value.toString()
                binding.lineartotal.visibility = View.VISIBLE
            }

        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Toast.makeText(this, "Rating: $rating", Toast.LENGTH_SHORT).show()
        }


        binding.home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showAlert() {
        val builder= android.app.AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Servicio de streaming en mantenimiento")
        builder.setPositiveButton("Salir",null)
        val dialog: android.app.AlertDialog =builder.create()
        dialog.show()
    }
}

