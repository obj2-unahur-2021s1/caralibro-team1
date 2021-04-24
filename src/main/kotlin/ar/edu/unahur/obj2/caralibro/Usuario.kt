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

    //Esta funcion busca al amigo con mas me gusta en sus publicaciones
  fun elAmigoMasPular() = amigos.maxByOrNull{ c->c.meGustasDeTodaslasPublicaciones()}
    //Esta funcion suma todas los me gusta de todas sos publicaciones
  fun meGustasDeTodaslasPublicaciones()=publicaciones.sumBy{c->c.cantidadDeMeGusta()}
    //Saber si un usuario stalkea a otro. Lo cual ocurre si
    // el stalker le dio me gusta a más del 90% de sus publicaciones.
    //Esta funcion devuelve si la cantidad de megusta al usuario stalkeado es mayor o igual ala cantidad de publicaciones de stalkeado
  fun esStalkerDe(usuarioStalkeado: Usuario)= cantidadDeMegustaA(usuarioStalkeado)>=usuarioStalkeado.cantidadDePublicaciones()*0.9
    //Esta funcion devuelve la cantidad de publicaciones hechas
  fun cantidadDePublicaciones() = this.publicaciones.size
    //esta funcion devuelve la cantidad de me gustas hechos a tal usuario
  fun cantidadDeMegustaA(usuario: Usuario)= publicacionesQueMeGustan.count(){c->c.esDe()==usuario}

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
