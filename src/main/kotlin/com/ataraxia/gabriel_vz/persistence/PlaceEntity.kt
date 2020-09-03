package com.ataraxia.gabriel_vz.persistence

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

        @OneToMany(mappedBy = "placeOfPremiere",
                cascade = [CascadeType.ALL],
                orphanRemoval = true)
        var relatedWorks: MutableList<WorkEntity>? = mutableListOf()
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