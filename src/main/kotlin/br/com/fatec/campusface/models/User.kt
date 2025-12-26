package br.com.fatec.campusface.models
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    @Column(nullable = false)
    val fullName: String = "",

    @Column(unique = true, nullable = false)
    val email: String = "",

    @Column(nullable = false)
    val hashedPassword: String = "", // será criptografada

    val document: String = "",
    val faceImageId: String? = "",

    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return emptyList()
    }
    override fun getPassword(): String = hashedPassword
    override fun getUsername(): String = email
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true

}


