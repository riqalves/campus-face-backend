package br.com.fatec.campusface.repository

import br.com.fatec.campusface.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : JpaRepository<User, String> {




    fun findByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean

    /**
     * Busca uma lista de usuários com base em uma lista de IDs.
     * @param userIds A lista de IDs dos usuários a serem buscados.
     * @return Uma lista de objetos User correspondentes aos IDs encontrados.
     */
    abstract fun findAllByIds(userIds: List<String>): List<User>

}
