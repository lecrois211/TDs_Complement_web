package edu.spring.td2.Entites

import jakarta.persistence.*

@Entity
open class user {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Int? = null

    @Column(length = 60, nullable = false, unique = true)
    open lateinit var firstname: String

    @Column(length = 60, nullable = false, unique = true)
    open lateinit var lastname: String

    @Column(length = 60)
    open var email: String? = null

    @Column(length = 60)
    open var password: String? = null

    @Column(length = 60)
    open var suspended: Boolean = false

    @Column(nullable = false)
    open lateinit var organization: Organization

    @Column()
    open lateinit var groupes:List<Groupe>

}