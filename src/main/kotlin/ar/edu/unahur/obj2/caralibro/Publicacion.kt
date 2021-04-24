package ar.edu.unahur.obj2.caralibro

import kotlin.math.ceil


abstract class Publicacion {
    var cantidadDeMeGusta = 0

    var autor: Usuario? = null

    var privacidad: Privacidad? = null


    abstract fun espacioQueOcupa(): Int

    fun recibirMeGusta() {
        cantidadDeMeGusta += 1
    }

    fun cantidadDeMeGusta() = this.cantidadDeMeGusta

    fun puedeSerVista(usuario: Usuario) = privacidad!!.puedeVerPublicacion(this, usuario )

    fun cambiarPrivacidad(nuevaPrivacidad: Privacidad) {
        privacidad = nuevaPrivacidad
    }

    fun asignarAutor(autorAAsignar: Usuario) {
        autor = autorAAsignar
    }

}


class Foto(val alto: Int, val ancho: Int) : Publicacion()
{
    var factorDeCompresion = 0.7

    override fun espacioQueOcupa() = ceil(alto * ancho * factorDeCompresion).toInt()


    fun cambiarFactorDeComprension(nuevoFactorDeCompresion:Double) {
      factorDeCompresion = nuevoFactorDeCompresion
    }
}

class Texto(val contenido: String) : Publicacion()
{

  override fun espacioQueOcupa() = contenido.length

}

class Video(val duracion:Int, var calidad:Calidad): Publicacion()
{
    override fun espacioQueOcupa(): Int = calidad.espacioQueOcupa(duracion)

    fun cambiarCalidad(calidadNueva: Calidad) {
        calidad = calidadNueva
    }

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

abstract class Privacidad {
    abstract fun puedeVerPublicacion(publicacion: Publicacion, usuario: Usuario): Boolean
}

object Publico: Privacidad() {
    override fun puedeVerPublicacion(publicacion: Publicacion, usuario: Usuario) = true
}

object SoloAmigos: Privacidad() {
    override fun puedeVerPublicacion(publicacion: Publicacion, usuario: Usuario): Boolean {
        val amigos = publicacion.autor!!.amigos
        return amigos.contains(usuario)
    }
}

object ListaDePermitidos: Privacidad() {
    override fun puedeVerPublicacion(publicacion: Publicacion, usuario: Usuario): Boolean {
        val permitidos = publicacion.autor!!.mejoresAmigos()
        return permitidos.contains(usuario)
    }
}

object ListaDeExcluidos: Privacidad() {
    override fun puedeVerPublicacion(publicacion: Publicacion, usuario: Usuario): Boolean {
        val excluidos = publicacion.autor!!.listaDeExcluidos
        return !excluidos.contains(usuario)
    }
}