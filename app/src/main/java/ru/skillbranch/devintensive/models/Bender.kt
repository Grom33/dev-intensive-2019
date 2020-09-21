package ru.skillbranch.devintensive.models

import android.util.Log
import java.util.*

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    private fun validateQuestion(input: String): Boolean = when (question) {
        Question.NAME -> !input[0].isLowerCase()
        Question.PROFESSION -> input[0].isLowerCase()
        Question.MATERIAL -> !input.contains("[\\d]")
        Question.BDAY -> !input.contains("[a-zA-Z]")
        Question.SERIAL -> !input.contains("[a-zA-Z]") && input.length == 7
        Question.IDLE -> true
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        if (question.question == Question.IDLE.question)
            return "Отлично - ты справился\nНа этом все, вопросов больше нет" to status.color
        return if (question.answer.contains(answer.toLowerCase(Locale.ROOT)) && validateQuestion(answer)) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            "Это неправильный ответ\\n${question.question}" to status.color
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(
            val question: String,
            val answer: List<String>) {
        NAME("Как меня зовут?", listOf("биуендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "bender")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }
}