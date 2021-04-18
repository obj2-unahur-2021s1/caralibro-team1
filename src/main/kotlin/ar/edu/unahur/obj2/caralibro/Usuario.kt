package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
    //Los usuarios que pueden ver una publicación pueden indicar que esa publicación les gusta,
    //aumentando el número de me gustas de la misma. A nuestra aplicación le importa tanto la cantidad
    //de me gustas que recibió una publicación, como saber quiénes le dieron me gusta. No es posible que
    //una misma persona le de me gusta más de una vez.
  fun agregarPublicacion(publicacion: Publicacion)
  {
    publicaciones.add(publicacion)
  }
  fun darMeGusta(publicacion: Publicacion)
  {
      publicacion.darMeGusta()

  }



  fun espacioDePublicaciones() = publicaciones.sumBy { it.espacioQueOcupa() }
}
