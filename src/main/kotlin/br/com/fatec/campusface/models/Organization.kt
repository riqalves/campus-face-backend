package br.com.fatec.campusface.models

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "organizations")
data class Organization(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    @Column(nullable = false)
    val name: String = "",
    val description: String = "",

    @Column(name = "hub_code", nullable = false, unique = true)
    val hubCode: String = "",
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
)