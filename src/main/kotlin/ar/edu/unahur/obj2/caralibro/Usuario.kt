package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()

  val amigos = mutableListOf<Usuario>()

  val publicacionesQueMeGustan = mutableListOf<Publicacion>()

  fun agregarPublicacion(publicacion: Publicacion) = publicaciones.add(publicacion)

  fun darMeGusta(publicacion: Publicacion) {
    if(publicacionesQueMeGustan.contains(publicacion)) {
      error("Ya le diste me gusta a esta publicacion")
    }
    else {
      publicacion.recibirMeGusta()
    }
  }

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

  fun agregarAmigo(amigo: Usuario) {
    if (amigos.contains(amigo)) {
      error("Ya es tu amigo.")
    } else {
      amigos.add(amigo)
    }
  }

  fun esMasAmistoso(usuario: Usuario) = this.cantidadDeAmigos() > usuario.cantidadDeAmigos()

  fun cantidadDeAmigos() = amigos.size


}
