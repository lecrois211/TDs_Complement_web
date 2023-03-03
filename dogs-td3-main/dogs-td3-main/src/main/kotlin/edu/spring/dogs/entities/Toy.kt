package edu.spring.dogs.entities

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

open class Toy() {
    constructor(type:String, label:String) : this() {
        this.type = type
        this.label = label
    }
    @Column(length = 20)
    open var type:String? = null

    @Column(length = 20)
    open lateinit var label:String

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id:Int = 0
}