package daniil.akifev.testcontainers.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity(name="Company")
@Table(name = "company")
data class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    var name: String = "",

    @OneToMany(mappedBy = "company")
    var stocks: MutableSet<Stock> = mutableSetOf(),

    var stockPrice: Int = 0
)
