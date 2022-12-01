package com.tfg.proyectofinaltfg.principal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tfg.proyectofinaltfg.databinding.ActivityMainBinding
import com.tfg.proyectofinaltfg.pelicula.Movie
import com.tfg.proyectofinaltfg.seccion.Section
import com.tfg.proyectofinaltfg.seccion.SectionAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sectionRecycler.layoutManager = LinearLayoutManager(this)
        val sectionAdapter = SectionAdapter(this, binding.sectionRecycler) {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
        }
        binding.sectionRecycler.adapter = sectionAdapter

        sectionAdapter.submitList(downloadFakeMovies())
    }

    private fun downloadFakeMovies(): List<Section> {
        val database = Firebase.database("https://ateneatfg-default-rtdb.europe-west1.firebasedatabase.app/").reference;
        var nombre0 = "";
        database.child("peliculas").child("fantasia").child("informacion").child("0").child("nombre").get().addOnSuccessListener {
            nombre0 = it.value.toString()
            println(nombre0)
        }


        val fantasy1 = Movie("El Señor de los Anillos", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FSeñorDeLosAnillos.jpg?alt=media&token=0e484f87-553e-44a8-bf13-002ef17ec3f1")
        val fantasy2 = Movie("Harry Potter y la Piedra Filosofal", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FPiedraFilosofal.jpg?alt=media&token=a9a13428-6cfd-435a-b93c-35847cdba8d0")
        val fantasy3 = Movie("The Witcher", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Fthe%20witcher.jpg?alt=media&token=c2ed1db2-de43-4816-acff-737113a4d619")
        val fantasy4 = Movie("Harry Potter Y el Prisionero de Azkaban", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FPrisioneroDeAzkaban.png?alt=media&token=7921de81-1a87-4b5e-a5a9-475d269a2efc")
        val fantasy5 = Movie("El Señor de los Anillos: Los Anillos de Poder", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Flosanillosdepoder.jpg?alt=media&token=fd571cc2-d2e5-4553-ba4a-a131bc6dbaa9")
        val fanatsy6 = Movie("The Sandman", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Fsandman.jpg?alt=media&token=c55a3ce6-0b69-44a9-b52a-2008f98d1783")
        val fantasy7 = Movie("Spider-man: Un nuevo universo", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Fspiderman.jpg?alt=media&token=afae3379-6f72-4737-aaf9-3b172e69f836")
        val fantasyMovies = listOf(fantasy1, fantasy2, fantasy3, fantasy4, fantasy5, fanatsy6, fantasy7)

        val fantasySection = Section("Fantasía", fantasyMovies)
        val scienceFiction1 = Movie("The Mandalorian", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Fthe%20mandalorian.jpg?alt=media&token=2eaa15fe-e822-48ee-8c2b-964ea0495222")
        val scienceFiction2 = Movie("Regreso al futuro", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FRegresoAlFututo.jpg?alt=media&token=2367b77e-8f52-41d3-8997-0f5852742376")
        val scienceFiction3 = Movie("Ready player one", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FReadyPlayerOne.webp?alt=media&token=518dd1bf-7e11-4f14-ad9a-aeae742e7f86")
        val scienceFiction4 = Movie("Interestelar", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FInterestelar.jpg?alt=media&token=eec24082-1ad8-4412-b571-56ffd5bb15e2")
        val scienceFiction5 = Movie("Matrix", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FMatrix.jpg?alt=media&token=662948f3-f299-4ee8-901d-cebeb14f748b")
        val scienceFiction6 = Movie("Stranger Things", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Fstranger%20things.jpg?alt=media&token=2c2307da-70dd-4964-9db3-9ea43156c867")
        val scienceFiction7 = Movie("The Umbrella Academy", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Fthe%20umbrella%20academy.jpg?alt=media&token=330e942f-7a7f-4837-bd1b-87dcf5187cbc")
        val sfMovies = listOf(scienceFiction1, scienceFiction2, scienceFiction3, scienceFiction4, scienceFiction5, scienceFiction6, scienceFiction7)

        val sfSection = Section("Ciencia ficción", sfMovies)

        val christmas1 = Movie("Crónicas de navidad 1", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FCronicasDeNavidad1.jpg?alt=media&token=48bed150-98a1-4a59-80a0-3a241c1e708d")
        val christmas2 = Movie("Crónicas de navidad 2", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FCronicasDeNavidad2.jpg?alt=media&token=0b53fd74-5ba8-491a-8224-c06d86dccd1b")
        val christmas3 = Movie("Klaus", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FKlaus.jpg?alt=media&token=07160fb7-944e-4c47-b535-df207c97cc1d")
        val christmas4 = Movie("El Grinch", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Fel%20grinch.jpg?alt=media&token=ad668337-e7ae-406b-bdaa-f530af87f2f4")
        val christmas5 = Movie("El Cascanueces y los Cuatro Reinos", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Fcascanueces.jpg?alt=media&token=243030fa-7b6b-4cbc-bd32-61e57d2f6ba9")
        val christmas6 = Movie("El Grinch", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Fthe%20grinch%20animado.jpg?alt=media&token=9456a25a-cfab-46fa-9e98-e9794bddfda4")
        val christmas7 = Movie("Solo en casa 2", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Fsolo%20en%20casa.jpeg?alt=media&token=f01ebcaf-ee21-45ac-9548-92ff916aa89a")
        val christmasMovies = listOf(christmas1, christmas2, christmas3, christmas4, christmas5, christmas6, christmas7)
        val christmasSection = Section("Navidad", christmasMovies)

        val comedy1 = Movie("Dos padres por desigual", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FGuerraDePapas.webp?alt=media&token=b331359b-dda3-4b80-b1f8-ddb4241ae812")
        val comedy2 = Movie("Dos padres por desigual 2", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FGuerraDePapas2.webp?alt=media&token=06fc9b7f-a40d-4f4f-a263-23b3c09eb2c2")
        val comedy3 = Movie("Scary Movie", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FScaryMovie.jpg?alt=media&token=57bf7d02-649e-4b66-8855-58caa55320f2")
        val comedy4 = Movie("Como si fuera la primera vez", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2FComoSiFueraLaPrimeraVez.jpg?alt=media&token=e3416d4a-cbff-422a-91ab-aa599f9c2de9")
        val comedy5 = Movie("Brooklyn Nine-Nine", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Fbrooklyn%20nine%20nine.jpg?alt=media&token=70e9d885-542d-4f60-92c5-25db244454c0")
        val comedy6 = Movie("Padre de Familia", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Ffamily%20guy.png?alt=media&token=6c2dbef0-6a59-4329-b29f-de1fd2629df4")
        val comedy7 = Movie("Rick y Morty", "https://firebasestorage.googleapis.com/v0/b/ateneatfg.appspot.com/o/Imagenes%2Frick%20y%20morty.jpg?alt=media&token=b948d5be-dd6b-47c1-b4d7-c8a56fd0dd2d")
        val comedyMovies = listOf(comedy1, comedy2, comedy3, comedy4, comedy5, comedy6, comedy7)
        val comedySection = Section("Comedia", comedyMovies)

        return listOf(fantasySection, sfSection, christmasSection, comedySection)
    }
}