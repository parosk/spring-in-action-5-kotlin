package hk.com.martiansapp.tacocloudkotlin.data

import hk.com.martiansapp.tacocloudkotlin.User
import org.springframework.data.repository.CrudRepository


interface UserRepository : CrudRepository<User, Long?> {
    fun findByUsername(username: String): User
}
