package daniil.akifev.testcontainers.repository

import daniil.akifev.testcontainers.entity.Company
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyRepository : JpaRepository<Company, Int>