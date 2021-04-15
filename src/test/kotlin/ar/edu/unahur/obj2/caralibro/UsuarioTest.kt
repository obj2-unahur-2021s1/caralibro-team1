package ar.edu.unahur.obj2.caralibro

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)

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
        //Los videos tienen un tamaño que depende de la calidad con la cual el usuario elija publicarlo.
        //Para la calidad SD, el tamaño es igual a la duración del video en segundos. Para los videos HD 720p
        //el tamaño es igual al triple de la duración en segundos del video y para los videos de HD 1080p el tamaño
        //es el doble de los HD 720p. Debe poder modificarse la calidad sin tener que volver a hacer la publicación.
        describe("un video") {
            it("ocupa tantos bytes como su longitud") {
                val videoDeLaUniversidad= Video(3,HD720p)
                videoDeLaUniversidad.espacioQueOcupa().shouldBe(45)
                videoDeLaUniversidad.cambiarDeCalidad(HD1080p).espacioQueOcupa().shouldBe(55)

            }
        }
    }

    describe("Un usuario") {
      it("puede calcular el espacio que ocupan sus publicaciones") {
        val juana = Usuario()
        juana.agregarPublicacion(fotoEnCuzco)
        juana.agregarPublicacion(saludoCumpleanios)
        juana.espacioDePublicaciones().shouldBe(550548)
      }

    }
  }
})

