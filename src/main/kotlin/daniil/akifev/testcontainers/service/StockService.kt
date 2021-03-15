package daniil.akifev.testcontainers.service

import daniil.akifev.testcontainers.entity.Company
import daniil.akifev.testcontainers.repository.CompanyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.*

@Service
class StockService {
    @Autowired
    lateinit var companyRepository: CompanyRepository

    @Scheduled(fixedRate = 5000)
    fun changePrices() {
        companyRepository.findAll().forEach { company: Company ->
            company.stockPrice = company.stockPrice + 1
            companyRepository.save(company)
            println("${Date().time}: ${company.stockPrice}")
        }
    }
}