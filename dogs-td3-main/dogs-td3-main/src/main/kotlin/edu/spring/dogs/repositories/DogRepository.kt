package edu.spring.dogs.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import edu.spring.dogs.entities.Dog

@Repository
interface DogRepository : CrudRepository<Dog, Int> {
    fun findByMasterIsNull(): List<Dog>
    fun findByNameAndMasterId(s: String, id: Int): Dog?


}