package edu.spring.dogs.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.PreRemove

@Entity
open class Master(){
    constructor(firstname: String, lastname: String):this(){
        this.lastname = lastname
        this.firstname = firstname
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int = 0
    @Column(length = 20)
    open var firstname : String? = null
    @Column(length = 20)
    open var lastname : String? = null
    @OneToMany
    open lateinit var dogs : MutableSet<Dog>

    public fun addDog(chien: Dog) : Boolean{
        if (!this.dogs.contains(chien)){
            this.dogs += chien
            chien.master = this
            return true

        }
        return false
    }

    public fun giveUpDog(chien: Dog):Boolean{
        if (this.dogs.contains(chien)) {
            this.dogs -= chien
            chien.master = null
            return true
        }
        return false
    }

    @PreRemove
    public fun PreRemove(){
        for(dog in this.dogs){
            this.dogs -= dog
            dog.master = null
        }
    }

}
