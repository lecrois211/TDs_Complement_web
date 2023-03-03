package edu.spring.dogs.entities

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

open class Dog() {
    constructor(name: String):this(){
        this.name = name
    }

    @Column(length = 20)
    open lateinit var name:String

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var Id:Int = 0

    @Column
    open var master: Master?=null

    @Column
    open lateinit var toys:MutableSet<Toy>



}