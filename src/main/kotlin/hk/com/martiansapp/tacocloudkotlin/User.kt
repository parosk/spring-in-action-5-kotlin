package hk.com.martiansapp.tacocloudkotlin

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
        private var username: String,
        private var password: String,
        private var fullname: String?,
        private var street: String?,
        private var city: String?,
        private var state: String?,
        private var zip: String?,
        private var phoneNumber: String?,
) : UserDetails {
    private constructor() : this("", "", null, null, null, null, null, null)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: Long? = null
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return listOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String {
        return username
    }

    override fun getUsername(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}