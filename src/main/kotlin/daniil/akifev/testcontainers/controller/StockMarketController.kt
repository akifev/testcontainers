package daniil.akifev.testcontainers.controller

import daniil.akifev.testcontainers.entity.Company
import daniil.akifev.testcontainers.entity.Stock
import daniil.akifev.testcontainers.repository.CompanyRepository
import daniil.akifev.testcontainers.repository.StockRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/stock-market/")
class StockMarketController {
    @Autowired
    lateinit var companyRepository: CompanyRepository

    @Autowired
    lateinit var stockRepository: StockRepository

    @PostMapping("/add-company")
    fun addCompany(@RequestParam name: String, @RequestParam stockPrice: Int): Company {
        val company = Company(name = name, stockPrice = stockPrice)
        companyRepository.save(company)
        return company
    }

    @PostMapping("/add-stocks")
    fun addStocks(@RequestParam companyId: Int, @RequestParam count: Int) {
        val company = companyRepository.getOne(companyId)

        repeat(count) {
            val stock = Stock(company = company)
            stockRepository.save(stock)
            company.stocks.add(stock)
        }
    }

    @GetMapping("/get-stock-price")
    fun getStockPrice(@RequestParam companyId: Int): Int {
        val company = companyRepository.getOne(companyId)
        return company.stockPrice
    }
}