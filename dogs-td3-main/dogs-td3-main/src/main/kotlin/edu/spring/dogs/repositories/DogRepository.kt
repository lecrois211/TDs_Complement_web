package edu.spring.dogs.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import edu.spring.dogs.entities.Dog

@Repository
interface DogRepository : CrudRepository<Dog, Int> {
    abstract fun findByMasterIsNull(): List<Dog>
    abstract fun findByNameAndMasterId(s: String, id: Int): Dog


}