package edu.spring.td2.Entites

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity class Groupe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int? = null

    @Column(length = 60, nullable = false, unique = true)
    open lateinit var name: String

    @Column(length = 60)
    open var email:String? = null

    @Column(length = 20)
    open var aliases:String? = null

    @Column()
    open var users:List<user>? = null

    @Column(nullable = false)
    open lateinit var organization:Organization
}