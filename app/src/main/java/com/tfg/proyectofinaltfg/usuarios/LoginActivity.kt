package com.tfg.proyectofinaltfg.usuarios

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.tfg.proyectofinaltfg.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.tfg.proyectofinaltfg.R
import com.tfg.proyectofinaltfg.principal.MainActivity
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val sliderInicio= mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val carousel: ImageCarousel = findViewById(R.id.carousel)
        sliderInicio.add(CarouselItem("https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FPiedraFilosofal.jpg?alt=media&token=a9a13428-6cfd-435a-b93c-35847cdba8d0"))
        sliderInicio.add(CarouselItem("https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Frick%20y%20morty.jpg?alt=media&token=b948d5be-dd6b-47c1-b4d7-c8a56fd0dd2d"))
        sliderInicio.add(CarouselItem("https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FIndiana%20Jones.jpg?alt=media&token=766e6f76-dd43-48ea-b440-a9d67c960d6b"))
        carousel.addData(sliderInicio)

        //union entre activities
        binding.lUsu.doAfterTextChanged {
            comprobartexto()
        }
        binding.lPass.doAfterTextChanged {
            comprobartexto()
        }
        binding.bContinue.setOnClickListener {

            if (binding.lUsu.text.isNotEmpty() && binding.lPass.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        binding.lUsu.text.toString(),
                        binding.lPass.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful){
                            showMainActivity(it.result?.user?.email?: "")
                        }else{
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert() {
        val builder= android.app.AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El usuario no se encuentra registrado dentro de la app contacte con el servicio tecnico")
        builder.setPositiveButton("Salir",null)
        val dialog: android.app.AlertDialog =builder.create()
        dialog.show()
    }
    private fun showMainActivity (email:String){

        val mainIntent=Intent(this,MainActivity::class.java).apply {
            putExtra("email",email)
        }
        startActivity(mainIntent)

    }


    fun comprobartexto(){
        if(binding.lUsu.text.length>3 && binding.lPass.text.length==6 ){
            binding.bContinue.visibility= View.VISIBLE
        }else{
            binding.bContinue.visibility= View.GONE
        }
    }
}

