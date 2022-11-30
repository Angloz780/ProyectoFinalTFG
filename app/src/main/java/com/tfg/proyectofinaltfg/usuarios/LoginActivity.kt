package com.tfg.proyectofinaltfg.usuarios

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.tfg.proyectofinaltfg.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.tfg.proyectofinaltfg.principal.MainActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

