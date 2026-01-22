package br.com.fatec.campusface.repository

import br.com.fatec.campusface.models.AuthCode
import org.springframework.stereotype.Repository

@Repository
class AuthCodeRepository() {

    fun save(authCode: AuthCode): AuthCode {

    }

    fun findValidByCode(code: String): AuthCode? {
        return collection
            .whereEqualTo("code", code)
            .whereEqualTo("valid", true)
            .limit(1)
            .get().get()
            .toObjects(AuthCode::class.java).firstOrNull()
    }

    fun invalidateCode(id: String) {
        collection.document(id).update("valid", false).get()
    }

    /**
     * Invalida códigos anteriores DESTA organização para este usuário.
     * Isso impede que ele gere 50 códigos válidos ao mesmo tempo para o mesmo lugar.
     */
    fun invalidatePreviousCodes(userId: String, organizationId: String) {
        val batch = firestore.batch()

        val query = collection
            .whereEqualTo("userId", userId)
            .whereEqualTo("organizationId", organizationId)
            .whereEqualTo("valid", true)
            .get().get()

        query.documents.forEach { doc ->
            batch.update(doc.reference, "valid", false)
        }

        if (!query.isEmpty) {
            batch.commit().get()
        }
    }
}