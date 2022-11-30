package com.tfg.proyectofinaltfg.seccion

import com.tfg.proyectofinaltfg.pelicula.Movie

data class Section(val name: String, val movieList: List<Movie>, var expanded: Boolean = true)