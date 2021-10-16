package hk.com.martiansapp.tacocloudkotlin

import java.util.*
import javax.validation.constraints.Size

class Taco(
        var id: Long? = null,

        var createdAt: Date? = null,

        //@field meta-annotation is used to tell the compiler that the actual field named id should be considered
        // the target of the annotation, as opposed to the automatically generated accesssor/mutator methods
        // https://stackoverflow.com/a/37015815
        @field :Size(min = 5, message = "Name must be at least 5 characters long")
        var name: String = "",

        @field :Size(min = 1, message = "You must choose at least 1 ingredient")
        var ingredients: MutableList<Ingredient> = ArrayList()
)