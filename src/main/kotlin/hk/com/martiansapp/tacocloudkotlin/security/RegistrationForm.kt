package hk.com.martiansapp.tacocloudkotlin.security

import hk.com.martiansapp.tacocloudkotlin.User
import org.springframework.security.crypto.password.PasswordEncoder


class RegistrationForm(
        private var username: String = "",
        private var password: String? = null,
        private var fullname: String? = null,
        private var street: String? = null,
        private var city: String? = null,
        private var state: String? = null,
        private var zip: String? = null,
        private var phone: String? = null){
    fun toUser(passwordEncoder: PasswordEncoder): User {
        return User(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone)
    }
}