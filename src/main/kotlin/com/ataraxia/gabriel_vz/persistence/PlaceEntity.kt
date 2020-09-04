package com.ataraxia.gabriel_vz.persistence

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "place")
class PlaceEntity(

        @Id
        @GeneratedValue(generator = "system-uuid")
        @GenericGenerator(name = "system-uuid", strategy = "uuid")
        @Column(name = "id")
        val id: String? = null,
        var name: String,
        var locality: String,
        var country: String,

        @Embedded
        @AttributeOverrides(
                AttributeOverride(name = "latitude", column = Column(name = "latitude")),
                AttributeOverride(name = "longitude", column = Column(name = "longitude"))
        )
        var coordinates: CoordinatesEntity,

        @JsonBackReference
        @OneToMany(
                mappedBy = "placeOfPremiere"
        )
        var relatedWorks: MutableSet<WorkEntity>? = mutableSetOf()
) {
    fun addWork(workEntity: WorkEntity) {
        relatedWorks?.add(workEntity)
        workEntity.placeOfPremiere = this
    }

    fun removeWork(workEntity: WorkEntity) {
        relatedWorks?.remove(workEntity)
        workEntity.placeOfPremiere = null
    }
}