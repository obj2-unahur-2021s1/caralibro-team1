package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil


abstract class Publicacion
{

  abstract fun espacioQueOcupa(): Int
  fun recibirMeGusta() = cantidadDeMeGusta = cantidadDeMeGusta + 1


}


class Foto(val alto: Int, val ancho: Int) : Publicacion()
{
    var factorDeCompresion = 0.7
    override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()

  fun cambiarFactorDeComprension(nuevoFactorDeCompresion:Double)
  {
      factorDeCompresion = nuevoFactorDeCompresion
  }
}

class Texto(val contenido: String) : Publicacion()
{
  override fun espacioQueOcupa() = contenido.length
}
//Es este archivo
class Video(val duracion:Int, var calidad:Calidad): Publicacion()
{
    override fun espacioQueOcupa(): Int = calidad.espacioQueOcupa(duracion)

}
abstract class Calidad {
    abstract fun espacioQueOcupa(duracion:Int):Int
}
object HD720p: Calidad()
{
    override fun espacioQueOcupa(duracion: Int) = duracion * 3
}
object HD1080p: Calidad()
{
    override fun espacioQueOcupa(duracion: Int) = (duracion * 3) * 2
}
object SD: Calidad()
{
    override fun espacioQueOcupa(duracion: Int) = duracion
}