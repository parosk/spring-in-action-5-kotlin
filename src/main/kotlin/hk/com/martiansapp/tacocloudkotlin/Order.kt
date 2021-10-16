package hk.com.martiansapp.tacocloudkotlin

import org.hibernate.validator.constraints.CreditCardNumber
import java.io.Serializable
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import kotlin.collections.ArrayList


//custom name for the table in
//because order is a reserved word in sql
@Entity
@Table(name="Taco_Order")
class Order(

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        var id: Long? = null,
        var placedAt: Date? = null,

        @ManyToMany(targetEntity=Taco::class)
        val tacos: MutableList<Taco> = ArrayList(),

        // added delivery in front of name to distinguish from taco name in Form
        @field:NotBlank(message = "Name is required")
        var deliveryName: String? = null,

        @field:NotBlank(message = "Street is required")
        var street: String? = null,

        @field:NotBlank(message = "City is required")
        var city: String? = null,

        @field:NotBlank(message = "State is required")
        var state: String? = null,

        @field:NotBlank(message = "Zip code is required")
        var zip: String? = null,
        @field:CreditCardNumber(message = "Not a valid credit card number")
        var ccNumber: String? = null,

        @field:Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
        var ccExpiration: String? = null,

        @field:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
        var ccCVV: String? = null,
) : Serializable {
    @Transient
    private val serialVersionUID = 1L

    @PrePersist
    fun placedAt() {
        placedAt = Date()
    }
    fun addDesign(design: Taco) {
        tacos.add(design)
    }
}