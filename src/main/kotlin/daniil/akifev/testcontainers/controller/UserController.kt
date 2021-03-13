package daniil.akifev.testcontainers.controller

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
        userRepository.setMoney(userId, user.money + money)
    }

    @GetMapping("/get-stocks")
    fun getStocks(@RequestParam userId: Int): List<String> {
        val user = userRepository.getOne(userId)
        return user.stocks.map(Stock::toString)
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

    @PostMapping("/buy-stock")
    fun buyStock(@RequestParam userId: Int, @RequestParam stockId: Int): Boolean {
        val user = userRepository.getOne(userId)
        val stock = stockRepository.getOne(stockId)
        val price = stock.price()
        if (user.money < price) {
            return false
        }
        stockRepository.setOwner(stock.id, user.id)
        userRepository.setMoney(user.id, user.money - price)
        return true
    }

    @PostMapping("/cell-stock")
    fun cellStocks(@RequestParam userId: Int, @RequestParam stockId: Int) {
        val user = userRepository.getOne(userId)
        val stock = stockRepository.getOne(stockId)
        stockRepository.setOwner(stock.id, null)
        userRepository.setMoney(user.id, user.money + stock.price())
    }
}