package edu.spring.dogs.repositories

import edu.spring.dogs.entities.Master
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MasterRepository : CrudRepository<Master, Int> {
    abstract fun findByDogsName(s: String): MutableList<Master>
}