package edu.spring.td2.Entites

import jakarta.persistence.*


@Entity
open class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int? = null

    @Column(length = 60, nullable = false, unique = true)
    open lateinit var name: String

    @Column(length = 20)
    open var domain: String? = null

    @Column(length = 20)
    open var aliases: String? = null

    @Column(length = 60)
    open var OrganizationSettings: String? = null

    @Column(nullable = false)
    open lateinit var users:List<user>

    @Column(nullable = false)
    open lateinit var groupes: List<Groupe>


}