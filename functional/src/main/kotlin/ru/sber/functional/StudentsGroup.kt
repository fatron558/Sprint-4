package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> = students.filter { predicate(it) }

    init {
        var listStudents = ArrayList<Student>()
        for (i in 0..5) {
            listStudents.add(Student("firstName$i", "lastName$i",
                "middleName$i", i, i.toDouble() * 2,
                "city$i", "specialization$i", "prevEducation$i"))
        }
        students = listStudents
    }
}

fun main() {
    val group = StudentsGroup().filterByPredicate { it.age >= 3 }
    for (student in group)
        println("${student.firstName} ${student.lastName} ${student.middleName} ${student.age}")
}
