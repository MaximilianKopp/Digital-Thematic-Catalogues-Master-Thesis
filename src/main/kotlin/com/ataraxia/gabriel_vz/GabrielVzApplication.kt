package com.ataraxia.gabriel_vz

import com.ataraxia.gabriel_vz.persistence.*
import com.ataraxia.gabriel_vz.repository.DiscographyRepository
import com.ataraxia.gabriel_vz.repository.WorkRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.OffsetDateTime

@SpringBootApplication
class GabrielVzApplication {

//    @Bean
//    fun init(workRepository: WorkRepository, discographyRepository: DiscographyRepository) = CommandLineRunner {
//
//        val testDiscography = DiscographyEntity(
//                id = null,
//                created = OffsetDateTime.now(),
//                modified = null,
//                recordId = "222",
//                label = "Superdibummel",
//                dateOfPublishing = "23.12.22",
//                title = "SuperDamdam"
//        )
//
//        testDiscography.addMusicians(PersonEntity(
//                id = null,
//                title = "Murks",
//                created = OffsetDateTime.now(),
//                modified = null,
//                name = "Maria",
//                description = "Versucherin",
//                pnd = "232",
//                role = "User"
//        ))
//
//        val testWork = WorkEntity(
//                id = null,
//                created = OffsetDateTime.now(),
//                modified = null,
//                title = "Violinsonata G-Dur",
//                opus = "opus 20",
//                category = "Klaviermusik",
//                commentary = "Tolles Werk",
//                dateOfCreation = "23.12.1989",
//                dateOfPremiere = "26.09.2010",
//                dedication = "Für mich",
//                placeOfPremiere = PlaceEntity(
//                        id = null,
//                        created = OffsetDateTime.now(),
//                        modified = null,
//                        title = "Place",
//                        name = "Holländer",
//                        locality = "Ballsaal",
//                        country = "Deutschland",
//                        coordinates = CoordinatesEntity(
//                                longitude = 48.200683,
//                                latitude = 16.372793
//                        )
//
//                ),
//                duration = "2 Minuten",
//                editor = "Max",
//                relatedText = TextEntity(
//                        id = null,
//                        created = OffsetDateTime.now(),
//                        modified = null,
//                        title = "Ode an die Freude",
//                        author = "Schiller",
//                        excerpt = "Freude mimimi"
//                ),
//                instrumentation = "Für Klavier"
//        )
//
//        testWork.addIncipit(incipit = IncipitEntity(
//                id = null,
//                created = OffsetDateTime.now(),
//                modified = null,
//                title = "Incipit",
//                clef = "G-2",
//                description = "hihi",
//                keysig = "xF",
//                score = "'6{DD,AA}'{DDFF}{DD,AA}'{DDFF}/{D,AB'C}{DEFG}{FFDD}{FFAA}/{FFDD}{FFAA}{8F6GA}{B''CDE}",
//                text = "Mimimi",
//                timesig = "4/4"
//        ))
//
//        testWork.addDiscography(DiscographyEntity(
//                id = null,
//                created = OffsetDateTime.now(),
//                modified = null,
//                title = "Collection",
//                dateOfPublishing = "22.23.29",
//                label = "Gramophon",
//                musicians = null,
//                recordId = "2323"
//        ))
//
//        testWork.addDiscography(DiscographyEntity(
//                id = null,
//                created = OffsetDateTime.now(),
//                modified = null,
//                title = "F",
//                dateOfPublishing = "22.23.29",
//                label = "Gramophon",
//                musicians = null,
//                recordId = "2323"
//        ))
//
//        testWork.addDiscography(DiscographyEntity(
//                id = null,
//                created = OffsetDateTime.now(),
//                modified = null,
//                title = "G",
//                dateOfPublishing = "22.23.29",
//                label = "Gramophon",
//                musicians = null,
//                recordId = "2323"
//        ))
//
//        testWork.addDiscography(testDiscography)
//
//        testWork.addDiscography(DiscographyEntity(
//                id = null,
//                created = OffsetDateTime.now(),
//                modified = null,
//                title = "Musicial",
//                dateOfPublishing = "22.10.19",
//                label = "UDEMY",
//                recordId = "21"
//        ))
//
//        testWork.addLiterature(LiteratureEntity(
//                id = null,
//                created = OffsetDateTime.now(),
//                modified = null,
//                title = "Work",
//                author = "Schiller",
//                isbn = "232323",
//                yearOfPublishing = "20.02.1989",
//                relatedWork = null
//        ))
//
//        testWork.addPerson(PersonEntity(
//                id = null,
//                created = OffsetDateTime.now(),
//                modified = null,
//                title = "Work",
//                name = "Maximilian",
//                description = "Versucher",
//                pnd = "232",
//                role = "User"
//        ))
//
//        testWork.addPerson(PersonEntity(
//                id = null,
//                created = OffsetDateTime.now(),
//                modified = null,
//                title = "Work",
//                name = "Luise",
//                description = "Versucher",
//                pnd = "232",
//                role = "User"
//        ))
//        workRepository.save(testWork)
//
//        testWork.relatedPersons?.elementAt(0)?.name = "Otto"
//        workRepository.save(testWork)
//
//    }

}

fun main(args: Array<String>) {
    runApplication<GabrielVzApplication>(*args)
}





