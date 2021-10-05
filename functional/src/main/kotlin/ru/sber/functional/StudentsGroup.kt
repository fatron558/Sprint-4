package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> = students.filter { predicate(it) }

    init {
        var listStudents = ArrayList<Student>()
        for (i in 0..5) {
            listStudents.add(Student(
                firstName = "firstName$i",
                lastName = "lastName$i",
                averageRate = i.toDouble() * 2
            ))
        }
        students = listStudents
    }
}

fun main() {
    val group = StudentsGroup().filterByPredicate { it.averageRate >= 5 }
    for (student in group)
        println("${student.firstName} ${student.lastName} ${student.middleName} ${student.averageRate}")
}
