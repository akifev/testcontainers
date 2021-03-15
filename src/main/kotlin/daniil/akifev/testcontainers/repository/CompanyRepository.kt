package daniil.akifev.testcontainers.repository

import daniil.akifev.testcontainers.entity.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Int>