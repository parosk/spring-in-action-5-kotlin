package hk.com.martiansapp.tacocloudkotlin.security

import hk.com.martiansapp.tacocloudkotlin.User
import hk.com.martiansapp.tacocloudkotlin.data.UserRepository
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

//stereotype annotations that flag it for inclusion in Spring’s component scanning
@Service
class UserRepositoryUserDetailsService @Autowired constructor(private val userRepo: UserRepository) : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val user: User = userRepo.findByUsername(username)
        if (user != null) {
            return user
        }
        throw UsernameNotFoundException(
                "User '$username' not found")
    }
}