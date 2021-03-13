package daniil.akifev.testcontainers.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity(name = "Stock")
@Table(name = "stock")
data class Stock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    @ManyToOne
    @JoinColumn(name = "company_id")
    var company: Company = Company(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    var owner: User? = null
) {
    fun price(): Int {
        return company.stockPrice
    }
}
