package com.learning.helloworld.structures

import java.io.File
import kotlin.random.Random

data class Student(
    val id: Int,
    val firstName: String,
    val lastName: String
) {
    val name get() = "$firstName $lastName"
}

object StudentLoader {
    fun loadStudents(file: File): List<Student> {
        if (!file.exists()) {
            val students = generateStudents()
            saveStudents(students, file)
            return students
        }
        val lines = file.readLines()
        return lines.filter(String::isNotEmpty).map(StudentLoader::loadStudent)
    }

    private fun loadStudent(str: String): Student {
        val parts = str.split(':')
        return Student(parts[0].toInt(), parts[1], parts[2])
    }

    private fun generateStudents(): List<Student> {
        val r = Random(333333)
        return generateSequence(
            1,
            { i -> if (i < 500) i + 1 else null }).map {i ->
            Student(i, firstNames.random(r), lastNames.random(r))
        }.toList()
    }

    private fun saveStudents(students: List<Student>, file: File) {
        val lines = students.map(StudentLoader::saveStudent)
        file.writeText("")
        lines.forEach {
            file.appendText(it + "\n")
        }
    }

    private fun saveStudent(student: Student): String {
        return "${student.id}:${student.firstName}:${student.lastName}"
    }

    private val lastNames = listOf(
        "Иванов",
        "Смирнов",
        "Кузнецов",
        "Попов",
        "Васильев",
        "Петров",
        "Соколов",
        "Михайлов",
        "Новиков",
        "Фёдоров",
        "Морозов",
        "Волков",
        "Алексеев",
        "Лебедев",
        "Семенов",
        "Егоров",
        "Павлов",
        "Николаев",
        "Андреев",
        "Никитин",
        "Грибов",
        "Елизаров"
    )

    private val firstNames = listOf(
        "Артем",
        "Виталий",
        "Рома",
        "Александр",
        "Дмитрий",
        "Владимир",
        "Михаил",
        "Аркадий",
        "Алексей",
        "Олег",
        "Иван",
        "Вячеслав",
        "Константин",
        "Фёдор",
        "Игорь",
        "Леонид",
        "Денис",
        "Кирилл",
        "Евгений",
        "Никита",
        "Егор",
        "Даниил",
        "Андрей"
    )
}