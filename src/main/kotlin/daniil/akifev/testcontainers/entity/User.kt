package daniil.akifev.testcontainers.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*

@Entity(name="User")
@Table(name = "user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,

    var name: String = "",
    var money: Int = 0,

    @OneToMany(mappedBy = "owner")
    var stocks: MutableSet<Stock> = mutableSetOf()
)
