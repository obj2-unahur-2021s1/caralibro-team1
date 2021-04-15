package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil



abstract class Publicacion
{
  abstract fun espacioQueOcupa(): Int
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




class Video(val duracion:Int,var calidad:Calidades): Publicacion()
{
    override fun espacioQueOcupa(): Int = calidad.espacioQueOcupa()
    fun cambiarDeCalidad(nuevaCalidad: Calidades){calidad=nuevaCalidad}
}

class Calidades :Video(duracion)
{
    abstract fun espacioQueOcupa()
}
object HD720p: Calidades(duracion:Int)
{
    override fun espacioQueOcupa() {
        return
    }
}
object HD1080p: Calidades()
{
    override fun espacioQueOcupa() {
        return
    }
}
object SD: Calidades()
{
    override fun espacioQueOcupa() {
        return
    }
}