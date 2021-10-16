package hk.com.martiansapp.tacocloudkotlin

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


@Entity
class Taco(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        var createdAt: Date? = null,

        //@field meta-annotation is used to tell the compiler that the actual field named id should be considered
        // the target of the annotation, as opposed to the automatically generated accesssor/mutator methods
        // https://stackoverflow.com/a/37015815
        @field :Size(min = 5, message = "Name must be at least 5 characters long")
        @field: NotNull
        var name: String = "",

        // A Taco can have many Ingredient objects, and an Ingredient can be a part of many Tacos.
        @field :Size(min = 1, message = "You must choose at least 1 ingredient")
        @ManyToMany(targetEntity = Ingredient::class)
        var ingredients: MutableList<Ingredient> = ArrayList()
) {
    //use this to set the createdAt property to the current date and time before Taco is persisted
    @PrePersist
    fun createdAt() {
        createdAt = Date()
    }
}