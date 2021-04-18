package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil


abstract class Publicacion
{

  abstract fun espacioQueOcupa(): Int
<<<<<<< HEAD
  fun recibirMeGusta() = cantidadDeMeGusta = cantidadDeMeGusta + 1
=======
  abstract fun cantidadDeMegusta():Int
>>>>>>> 885f823268519a4c7b20ec9d4591ba8d90899f18


}


class Foto(val alto: Int, val ancho: Int) : Publicacion()
{
<<<<<<< HEAD
    var factorDeCompresion = 0.7
    override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()

=======
  var factorDeCompresion = 0.7
  var meGustaObetenidosActuales=0
  override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()
  override fun cantidadDeMegusta(): Int =meGustaObetenidosActuales
  fun darMegusta(){meGustaObetenidosActuales +=1}
>>>>>>> 885f823268519a4c7b20ec9d4591ba8d90899f18
  fun cambiarFactorDeComprension(nuevoFactorDeCompresion:Double)
  {
      factorDeCompresion = nuevoFactorDeCompresion
  }
}

class Texto(val contenido: String) : Publicacion()
{
  var meGustaObetenidosActuales=0
  override fun cantidadDeMegusta(): Int =meGustaObetenidosActuales
  override fun espacioQueOcupa() = contenido.length
  fun darMegusta(){meGustaObetenidosActuales +=1}
}
//Es este archivo
class Video(val duracion:Int, var calidad:Calidad): Publicacion()
{
    var meGustaObetenidosActuales=0
    override fun cantidadDeMegusta(): Int =meGustaObetenidosActuales
    override fun espacioQueOcupa(): Int = calidad.espacioQueOcupa(duracion)
    fun darMegusta(){meGustaObetenidosActuales +=1}
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