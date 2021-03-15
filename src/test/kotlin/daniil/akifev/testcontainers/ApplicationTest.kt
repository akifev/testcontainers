package daniil.akifev.testcontainers

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.FixedHostPortGenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class ApplicationTest {
//    @Autowired
//    lateinit var controller: UserController

    companion object {
        @Container
        var container = FixedHostPortGenericContainer<Nothing>("docker-image-name")
            .apply {
                withFixedExposedPort(6379, 6379)
                start()
            }
    }

    @Test
    fun test() {
//        val user = controller.addUser("Petya")
//        assertEquals(0, controller.getTotalMoney(user.id))
    }
}
