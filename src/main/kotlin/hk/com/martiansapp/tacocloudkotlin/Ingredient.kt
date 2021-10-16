package hk.com.martiansapp.tacocloudkotlin

import javax.persistence.*

// jpa annotation for entity
//
@Entity
class Ingredient(@Id val id: String?, val name: String?, @Enumerated(EnumType.STRING) val type: Type?) {

    //JPA requires that entities have a no-arguments constructor
    // create explicit no arg private constructor here, so we can accidentally use it.
    private constructor() : this(null, null, null)

    enum class Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE;

        companion object {
            fun valueOf(name: String): Type? = values().find { it.name == name }
        }

    }
}