package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val juana = Usuario()
    val videoDeLaUniversidad = Video(3, HD720p)
    val brenda = Usuario()
    val cristian = Usuario()
    val patricio = Usuario()
    val fotoDeBebe = Foto(50, 60)
    val videoGracioso = Video(80, SD)


    describe("Una publicación") {
      describe("de tipo foto") {
        it("ocupa ancho * alto * compresion bytes") {
          fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
        }
      }


      describe("de tipo texto") {
        it("ocupa tantos bytes como su longitud") {
          saludoCumpleanios.espacioQueOcupa().shouldBe(45)
        }
      }

      describe("de tipo video") {
        it("ocupa tantos bytes como su longitud") {
          videoDeLaUniversidad.espacioQueOcupa().shouldBe(9)
          videoDeLaUniversidad.cambiarCalidad(HD1080p)
          videoDeLaUniversidad.espacioQueOcupa().shouldBe(18)
        }
      }
    }

    describe("Un usuario") {
      describe("puede calcular el espacio que ocupan sus publicaciones") {
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.espacioDePublicaciones().shouldBe(550548)
        juana.darMeGusta(fotoEnCuzco)
        brenda.darMeGusta(fotoEnCuzco)
      }

      describe("puede dar me gusta") {
        juana.darMeGusta(saludoCumpleanios)
        saludoCumpleanios.cantidadDeMeGusta().shouldBe(1)
        cristian.darMeGusta(saludoCumpleanios)
        saludoCumpleanios.cantidadDeMeGusta().shouldBe(2)

      }

      describe("es mas amistoso que otro") {
        it("tiene mas amigos") {
          juana.agregarAmigo(cristian)
          juana.agregarAmigo(brenda)
          cristian.agregarAmigo(juana)
          juana.esMasAmistoso(cristian).shouldBeTrue()
          cristian.agregarAmigo(patricio)
          cristian.agregarAmigo(brenda)
          juana.esMasAmistoso(cristian).shouldBeFalse()
        }
      }

      describe("puede ver la publicacion") {
        describe("publicacion publica") {
          cristian.agregarPublicacion(fotoDeBebe)
          cristian.cambiarPrivacidad(fotoDeBebe, Publico)
          patricio.puedeVerLaPublicacion(fotoDeBebe).shouldBeTrue()
          juana.puedeVerLaPublicacion(fotoDeBebe).shouldBeTrue()
        }
        describe("publicacion solo amigos") {
          juana.agregarPublicacion(videoGracioso)
          juana.agregarAmigo(brenda)
          juana.cambiarPrivacidad(videoGracioso, SoloAmigos)
          brenda.puedeVerLaPublicacion(videoGracioso).shouldBeTrue()
          patricio.puedeVerLaPublicacion(videoGracioso).shouldBeFalse()
        }
        describe("solo lista de permitidos") {
          juana.agregarAmigo(patricio)
          juana.agregarAmigo(cristian)
          juana.agregarAListaDePermitidos(patricio)
          juana.agregarAListaDePermitidos(brenda)
          juana.agregarPublicacion(videoGracioso)
          juana.cambiarPrivacidad(videoGracioso, ListaDePermitidos)
          cristian.puedeVerLaPublicacion(videoGracioso).shouldBeFalse()
          brenda.puedeVerLaPublicacion(videoGracioso).shouldBeTrue()
        }
        describe("todos menos los exluidos") {
          juana.agregarPublicacion(fotoDeBebe)
          juana.cambiarPrivacidad(fotoDeBebe, ListaDeExcluidos)
          juana.agregarAListaDeExcluidos(cristian)
          juana.agregarAListaDeExcluidos(brenda)
          brenda.puedeVerLaPublicacion(fotoDeBebe).shouldBeFalse()
          patricio.puedeVerLaPublicacion(fotoDeBebe).shouldBeTrue()
        }
      }
    }
  }
})


