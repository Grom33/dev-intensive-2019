package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?) : Pair<String?, String?> {
        val trim = fullName?.trim()

        val parts : List<String>? = trim?.split(" ")
        

        var firstName = parts?.getOrNull(0)

        var lastName = parts?.getOrNull(1)

        if(firstName == "") firstName = null
        if(lastName == "") lastName = null
        return firstName to lastName
    }

    fun transliteration(sentence: String, delimiter: String = " "): String {
        val parts : List<String> = sentence.split(" ")
        var res = ""
        for(word: String in parts) {
            var translated = ""
            val charArray = word.toCharArray()
            for(ch:Char in charArray.iterator()) {
                val char = when(ch) {
                    'а' -> "a"
                    'б' -> "b"
                    'в' -> "v"
                    'г' -> "g"
                    'д' -> "d"
                    'е' -> "e"
                    'ё' -> "e"
                    'ж' -> "zh"
                    'з' -> "z"
                    'и' -> "i"
                    'й' -> "i"
                    'к' -> "k"
                    'л' -> "l"
                    'м' -> "m"
                    'н' -> "n"
                    'о' -> "o"
                    'п' -> "p"
                    'р' -> "r"
                    'с' -> "s"
                    'т' -> "t"
                    'у' -> "u"
                    'ф' -> "f"
                    'х' -> "h"
                    'ц' -> "c"
                    'ч' -> "ch"
                    'ш' -> "sh"
                    'щ' -> "sh'"
                    'ъ' -> ""
                    'ы' -> "i"
                    'ь' -> ""
                    'э' -> "e"
                    'ю' -> "yu"
                    'я' -> "ya"

                    'А' -> "A"
                    'Б' -> "B"
                    'В' -> "V"
                    'Г' -> "G"
                    'Д' -> "D"
                    'Е' -> "E"
                    'Ё' -> "E"
                    'Ж' -> "Zh"
                    'З' -> "Z"
                    'И' -> "I"
                    'Й' -> "I"
                    'К' -> "K"
                    'Л' -> "L"
                    'М' -> "M"
                    'Н' -> "N"
                    'О' -> "O"
                    'П' -> "P"
                    'Р' -> "R"
                    'С' -> "S"
                    'Т' -> "T"
                    'У' -> "U"
                    'Ф' -> "F"
                    'Х' -> "H"
                    'Ц' -> "C"
                    'Ч' -> "Ch"
                    'Ш' -> "Sh"
                    'Щ' -> "Sh'"
                    'Ъ' -> ""
                    'Ы' -> "I"
                    'Ь' -> ""
                    'Э' -> "E"
                    'Ю' -> "Yu"
                    'Я' -> "Ya"
                    else -> ch.toString()
                }
                translated = "$translated$char"
            }
            if(res.isNotEmpty()) res = "$res$delimiter$translated"
            else res = "$res$translated"
        }

        return res
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if(firstName == null && lastName == null) return null
        val first = "${firstName?.firstOrNull()?:""}${lastName?.firstOrNull()?:""}"
        val p = Regex("\\s")
        if(first.matches(p)) return null
        return first.toUpperCase()
    }




}