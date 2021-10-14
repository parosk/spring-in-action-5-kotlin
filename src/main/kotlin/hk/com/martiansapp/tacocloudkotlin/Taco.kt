package hk.com.martiansapp.tacocloudkotlin

import java.util.ArrayList
import javax.validation.constraints.Size

class Taco(
        @field :Size(min = 5, message = "Name must be at least 5 characters long")
        var name: String = "",
        @field :Size(min = 1, message = "You must choose at least 1 ingredient")
        var ingredients: MutableList<Ingredient> = ArrayList()
)