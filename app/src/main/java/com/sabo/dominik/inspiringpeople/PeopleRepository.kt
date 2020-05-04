package com.sabo.dominik.inspiringpeople

class PeopleRepository private constructor() {

    private object HOLDER {
        val INSTANCE = PeopleRepository()
    }

    companion object {
        val instance: PeopleRepository by lazy { HOLDER.INSTANCE }
    }

    val people: ArrayList<InspiringPerson> = ArrayList<InspiringPerson>(0)

    fun add(person: InspiringPerson){
        people.add(person)
    }

    fun remove(index: Int){
        people.removeAt(index)
    }

    fun quotePerson(position: Int): String{
        return people[position].getRandomQuote()
    }
}