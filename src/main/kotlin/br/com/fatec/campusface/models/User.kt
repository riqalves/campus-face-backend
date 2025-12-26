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

    @Column(nullable = false, unique = true)
    val email: String = "",

    @Column(nullable = false)
    val hashedPassword: String = "",

    val document: String = "",

    val faceImageId: String? = null,

    val createdAt: Instant = Instant.now(),

    val updatedAt: Instant = Instant.now()
) : UserDetails {

    @Transient
    override fun getAuthorities(): Collection<GrantedAuthority> = emptyList()

    @Transient
    override fun getPassword(): String = hashedPassword

    @Transient
    override fun getUsername(): String = email

    @Transient
    override fun isAccountNonExpired(): Boolean = true

    @Transient
    override fun isAccountNonLocked(): Boolean = true

    @Transient
    override fun isCredentialsNonExpired(): Boolean = true

    @Transient
    override fun isEnabled(): Boolean = true
}