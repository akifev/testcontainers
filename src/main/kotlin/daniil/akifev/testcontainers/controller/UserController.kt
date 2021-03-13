package daniil.akifev.testcontainers.controller

import daniil.akifev.testcontainers.entity.Company
import daniil.akifev.testcontainers.entity.Stock
import daniil.akifev.testcontainers.entity.User
import daniil.akifev.testcontainers.repository.StockRepository
import daniil.akifev.testcontainers.repository.UserRepository
import daniil.akifev.testcontainers.totalCost
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user/")
class UserController {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var stockRepository: StockRepository

    @PostMapping("/add-user")
    fun addUser(@RequestParam name: String): User {
        val user = User(name = name)
        userRepository.save(user)
        return user
    }

    @PostMapping("/add-money")
    fun addMoney(@RequestParam userId: Int, @RequestParam money: Int) {
        val user = userRepository.getOne(userId)
        user.money += money
    }

    @GetMapping("/get-stocks")
    fun getStocks(@RequestParam userId: Int): Set<Stock> {
        val user = userRepository.getOne(userId)
        return user.stocks
    }

    @GetMapping("/get-stocks-count")
    fun getStocksCount(@RequestParam userId: Int): Int {
        val user = userRepository.getOne(userId)
        return user.stocks.size
    }

    @GetMapping("/get-stocks-total-cost")
    fun getStocksTotalCost(@RequestParam userId: Int): Int {
        val user = userRepository.getOne(userId)
        return user.stocks.totalCost()
    }

    @GetMapping("/get-total-money")
    fun getTotalMoney(@RequestParam userId: Int): Int {
        val user = userRepository.getOne(userId)
        return user.stocks.totalCost() + user.money
    }

    @PostMapping("/buy-stocks")
    fun buyStocks(@RequestParam userId: Int, @RequestParam stockIds: List<Int>) {
        val user = userRepository.getOne(userId)
        for (stockId in stockIds) {
            val stock = stockRepository.getOne(stockId)
            if (user.money >= stock.price()) {
                user.stocks.add(stock)
                user.money -= stock.price()
            }
        }
    }

    @PostMapping("/cell-stocks")
    fun cellStocks(@RequestParam userId: Int, @RequestParam stockIds: List<Int>) {
        val user = userRepository.getOne(userId)
        for (stockId in stockIds) {
            val stock = stockRepository.getOne(stockId)
            user.stocks.remove(stock)
            user.money += stock.price()
        }
    }
}