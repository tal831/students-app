package com.example.studentlist.model

class Model private constructor() {
    val students: MutableList<Student> = ArrayList()

    companion object {
        val share = Model()
    }

    init {
        (0 until 20)
            .map { index ->
                Student(
                    "Smurf $index",
                    index.toString(),
                    "052-53816$index",
                    "Room number $index",
                    "",
                    false
                )
            }.forEach { s -> students.add(s) }
    }

    fun remove(index: Int) {
        students.removeAt(index)
    }

    fun get(index: Int): Student {
        return students[index]
    }
}