package daniil.akifev.testcontainers.repository

import daniil.akifev.testcontainers.entity.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockRepository : JpaRepository<Stock, Int>