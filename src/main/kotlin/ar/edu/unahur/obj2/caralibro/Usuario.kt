package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()

  val amigos = mutableListOf<Usuario>()

  fun agregarPublicacion(publicacion: Publicacion) = publicaciones.add(publicacion)

  fun darMeGusta(publicacion: Publicacion) = publicacion.recibirMeGusta()

  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }

  fun agregarAmigo(amigo: Usuario) = amigos.add(amigo)

  fun esMasAmistoso(usuario: Usuario) = this.cantidadDeAmigos() > usuario.cantidadDeAmigos()

  fun cantidadDeAmigos() = amigos.size

}
