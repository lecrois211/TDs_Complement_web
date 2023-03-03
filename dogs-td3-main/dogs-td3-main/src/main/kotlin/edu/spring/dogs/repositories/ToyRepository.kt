package edu.spring.dogs.repositories

import edu.spring.dogs.entities.Toy
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
@Repository
interface ToyRepository : CrudRepository<Toy, Int> {
    abstract fun save(toy: Toy): Toy
    abstract fun findByType(s: String): MutableList<Toy>
}