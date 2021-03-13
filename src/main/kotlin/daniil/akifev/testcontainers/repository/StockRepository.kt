package daniil.akifev.testcontainers.repository

import daniil.akifev.testcontainers.entity.Stock
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Int>