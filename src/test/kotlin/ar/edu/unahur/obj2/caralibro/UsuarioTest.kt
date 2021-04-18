package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro")
  {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
<<<<<<< HEAD
    val juana = Usuario()
=======
    val videoDeLaUniversidad= Video(3,HD720p)
>>>>>>> 885f823268519a4c7b20ec9d4591ba8d90899f18


    describe("Una publicación") {
      describe("de tipo foto")
      {
        it("ocupa ancho * alto * compresion bytes")
        {
          fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
          fotoEnCuzco.cantidadDeMegusta().shouldBe(2)
        }
      }


      describe("de tipo texto") {
        it("ocupa tantos bytes como su longitud")
        {
          saludoCumpleanios.espacioQueOcupa().shouldBe(45)
          saludoCumpleanios.cantidadDeMegusta().shouldBe(0)
        }
      }

      describe("un video") {
        it("ocupa tantos bytes como su longitud")
        {
          videoDeLaUniversidad.espacioQueOcupa().shouldBe(9)
          videoDeLaUniversidad.calidad = HD1080p
          videoDeLaUniversidad.espacioQueOcupa().shouldBe(18)
<<<<<<< HEAD
=======
          videoDeLaUniversidad.cantidadDeMegusta().shouldBe(0)
>>>>>>> 885f823268519a4c7b20ec9d4591ba8d90899f18
        }
      }

      describe("me gusta") {
        val cristian = Usuario()
        juana.darMeGusta(saludoCumpleanios)
        saludoCumpleanios.cantidadDeMeGusta().shouldBe(1)
        cristian.darMeGusta(saludoCumpleanios)
        saludoCumpleanios.cantidadDeMeGusta().shouldBe(2)
      }
    }

    describe("Un usuario") {
      it("puede calcular el espacio que ocupan sus publicaciones") {
        val juana = Usuario()
        val brenda = Usuario()
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.espacioDePublicaciones().shouldBe(550548)
        juana.darMeGusta(FotoEnCuzco)
        brenda.darMeGusta(FotoEnCuzco)
      }

    }
  }

})

