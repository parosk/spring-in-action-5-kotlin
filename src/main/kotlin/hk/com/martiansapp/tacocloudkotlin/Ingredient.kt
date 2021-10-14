package hk.com.martiansapp.tacocloudkotlin

// no lombok is needed for Kotlin

class Ingredient(var id: String, var name: String, var type: Type) {
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

