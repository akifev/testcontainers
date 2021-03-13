package daniil.akifev.testcontainers.repository

import daniil.akifev.testcontainers.entity.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface StockRepository : JpaRepository<Stock, Int> {
    @Modifying
    @Transactional
    @Query(value = "update stock set user_id = ?2 where id = ?1", nativeQuery = true)
    fun setOwner(id: Int, owner: Int?)
}