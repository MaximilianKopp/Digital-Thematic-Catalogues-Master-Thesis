package com.ataraxia.gabriel_vz

import com.ataraxia.gabriel_vz.persistence.*
import com.ataraxia.gabriel_vz.repository.DiscographyRepository
import com.ataraxia.gabriel_vz.repository.WorkRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GabrielVzApplication {

    @Bean
    fun init(workRepository: WorkRepository, discographyRepository: DiscographyRepository) = CommandLineRunner {

        var testWork = WorkEntity(
                id = null,
                title = "Sonata C-Dur",
                category = "Klaviermusik",
                commentary = "Tolles Werk",
                dateOfCreation = "23.12.1989",
                dateOfPremiere = "26.09.2010",
                dedication = "Für mich",
                placeOfPremiere = PlaceEntity(
                        id = null,
                        name = "Holländer",
                        locality = "Ballsaal",
                        country = "Deutschland",
                        coordinates = CoordinatesEntity(
                                longitude = 23.12,
                                latitude = 22.11
                        )
                ),
                duration = "2 Minuten",
                editor = "Max",
                relatedText = TextEntity(
                        id = null,
                        title = "Ode an die Freude",
                        author = "Schiller",
                        excerpt = "Freude mimimi"
                ),
                instrumentation = "Für Klavier",
                incipit = IncipitEntity(
                        id = null,
                        description = "hihi",
                        keysig = "g",
                        score = "f-g-a-b",
                        text = "Mimimi",
                        timesig = "2/3"
                )
        )
        testWork.addDiscography(DiscographyEntity(
                id = null,
                title = "Collection",
                dateOfPublishing = "22.23.29",
                label = "Gramophon",
                musicians = null,
                recordId = "2323"
        ))
        testWork.addDiscography(DiscographyEntity(
                id = null,
                title = "Musicial",
                dateOfPublishing = "22.10.19",
                label = "UDEMY",
                musicians = null,
                recordId = "21"
        ))

        testWork.addLiterature(LiteratureEntity(
                id = null,
                author = "Schiller",
                isbn = "232323",
                yearOfPublishing = "20.02.1989"
        ))

        testWork.addPerson(PersonEntity(
                id = null,
                name = "Maximilian",
                description = "Versucher",
                pnd = "232",
                role = "User"
        ))
        workRepository.save(testWork)
    }

}

fun main(args: Array<String>) {
    runApplication<GabrielVzApplication>(*args)
}





