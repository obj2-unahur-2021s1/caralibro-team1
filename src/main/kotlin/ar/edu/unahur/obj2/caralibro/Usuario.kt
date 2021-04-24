package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()

  val amigos = mutableListOf<Usuario>()

  val listaDePermitidos = mutableListOf<Usuario>()

  val listaDeExcluidos = mutableListOf<Usuario>()

  val publicacionesQueMeGustan = mutableListOf<Publicacion>()

  fun agregarPublicacion(publicacion: Publicacion) {
    publicaciones.add(publicacion)
    publicacion.asignarAutor(this)
  }

  fun puedoDarMeGusta(publicacion: Publicacion) = this.puedeVerLaPublicacion(publicacion)

  fun darMeGusta(publicacion: Publicacion) {
    if(publicacionesQueMeGustan.contains(publicacion)) {
      error("Ya le diste me gusta a esta publicacion")
    }
    else if(this.puedoDarMeGusta(publicacion)){
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

  fun agregarAListaDeMejoresAmigos(usuario: Usuario) {
    if (listaDePermitidos.contains(usuario)) {
      error("Ya está en lista de permitidos.")
    }
    else {
      listaDePermitidos.add(usuario)
    }
  }
  fun eliminarDeListaDePermitidos(usuario: Usuario) {
    if(!listaDePermitidos.contains(usuario)) {
      error("No está en la lista de permitidos")
    }
    else {
      listaDePermitidos.remove(usuario)
    }
  }
  fun agregarAListaDeExcluidos(usuario: Usuario) {
    if (listaDeExcluidos.contains(usuario)) {
      error("Ya está en lista de excluidos.")
    }
    else {
      listaDeExcluidos.add(usuario)
    }
  }

  fun esMasAmistoso(usuario: Usuario) = this.cantidadDeAmigos() > usuario.cantidadDeAmigos()

  fun cantidadDeAmigos() = amigos.size

  fun puedeVerLaPublicacion(publicacion: Publicacion) = publicacion.puedeSerVista(this)

  fun cambiarPrivacidad(publicacion: Publicacion, privacidad: Privacidad) = publicacion.cambiarPrivacidad(privacidad)

  fun mejoresAmigos() = listaDePermitidos

}
