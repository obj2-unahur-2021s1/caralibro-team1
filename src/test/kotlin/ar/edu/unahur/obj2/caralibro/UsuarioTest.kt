package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val videoDeLaUniversidad = Video(3, HD720p)
    val fotoDeBebe = Foto(50, 60)
    val videoGracioso = Video(80, SD)

    val juana = Usuario()
    val brenda = Usuario()
    val cristian = Usuario()
    val patricio = Usuario()

    juana.agregarPublicacion(fotoEnCuzco)
    juana.cambiarPrivacidad(fotoEnCuzco, SoloAmigos)
    juana.agregarPublicacion(saludoCumpleanios)
    juana.cambiarPrivacidad(saludoCumpleanios, ListaDeExcluidos)
    cristian.agregarPublicacion(fotoDeBebe)
    cristian.cambiarPrivacidad(fotoDeBebe, Publico)


    juana.agregarAmigo(cristian)
    juana.agregarAmigo(brenda)
    cristian.agregarAmigo(juana)
    juana.agregarAListaDeMejoresAmigos(brenda)
    juana.agregarAListaDeMejoresAmigos(cristian)

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
        juana.espacioDePublicaciones().shouldBe(550548)
        juana.agregarPublicacion(videoDeLaUniversidad)
        juana.espacioDePublicaciones().shouldBe(550557)

      }

      describe("puede dar me gusta y cantidad de me gusta") {
        juana.darMeGusta(saludoCumpleanios)
        saludoCumpleanios.cantidadDeMeGusta().shouldBe(1)
        cristian.darMeGusta(saludoCumpleanios)
        saludoCumpleanios.cantidadDeMeGusta().shouldBe(2)

      }

      describe("es mas amistoso que otro") {
        it("tiene mas amigos") {
          juana.esMasAmistoso(cristian).shouldBeTrue()
          cristian.agregarAmigo(patricio)
          cristian.agregarAmigo(brenda)
          juana.esMasAmistoso(cristian).shouldBeFalse()
        }
      }

      describe("puede ver la publicacion") {
        describe("publicacion publica") {
          patricio.puedeVerLaPublicacion(fotoDeBebe).shouldBeTrue()
          juana.puedeVerLaPublicacion(fotoDeBebe).shouldBeTrue()
        }
        describe("publicacion solo amigos") {
          brenda.puedeVerLaPublicacion(fotoEnCuzco).shouldBeTrue()
          patricio.puedeVerLaPublicacion(fotoEnCuzco).shouldBeFalse()
        }
        describe("solo lista de permitidos") {
          juana.agregarAmigo(patricio)
          juana.cambiarPrivacidad(fotoEnCuzco, ListaDePermitidos)
          patricio.puedeVerLaPublicacion(fotoEnCuzco).shouldBeFalse()
          brenda.puedeVerLaPublicacion(fotoEnCuzco).shouldBeTrue()
        }
        describe("todos menos los exluidos") {
          juana.agregarAListaDeExcluidos(cristian)
          juana.agregarAListaDeExcluidos(brenda)
          brenda.puedeVerLaPublicacion(saludoCumpleanios).shouldBeFalse()
          patricio.puedeVerLaPublicacion(saludoCumpleanios).shouldBeTrue()
        }

      }
      describe("mejores amigos") {
        juana.mejoresAmigos().shouldBe(listOf(brenda, cristian))
        juana.eliminarDeListaDePermitidos(brenda)
        juana.mejoresAmigos().shouldBe(listOf(cristian))

      }
        //Saber si un usuario stalkea a otro. Lo cual ocurre si
        // el stalker le dio me gusta a más del 90% de sus publicaciones.

        describe("cual es el amigo más popular que tiene un usuario") {
            patricio.esStalkerDe(cristian).shouldBeFalse()
      }
        //Saber cual es el amigo más popular que tiene un usuario.
        // Es decir, el amigo que tiene mas me gusta entre todas sus publicaciones.
        describe("cual es el amigo más popular que tiene un usuario") {
            patricio.elAmigoMasPular().shouldBe(juana)
        }

    }
  }
})


