package daniil.akifev.testcontainers.repository

import daniil.akifev.testcontainers.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int>