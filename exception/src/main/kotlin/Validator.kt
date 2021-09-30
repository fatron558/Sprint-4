import sun.applet.Main
import java.util.stream.Collector
import java.util.stream.Collectors
import kotlin.streams.asStream

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return if (value != null) {
            val list = ArrayList<ErrorCode>()
            if (!(value.startsWith('8') || value.startsWith('7'))) {
                list.add(ErrorCode.PHONE_NUMBER_INVALID_FIRST_CHARACTER)
            }
            if (value.length != 11) {
                list.add(ErrorCode.PHONE_NUMBER_INVALID_LENGTH)
            }
            if (!value.all { it in '0'..'9' }) {
                list.add(ErrorCode.PHONE_NUMBER_INVALID_CHARS)
            }
            list
        } else {
            listOf(ErrorCode.PHONE_NUMBER_INVALID_NULL)
        }
    }
}

class FirstNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return if (!value.isNullOrEmpty()) {
            val list = ArrayList<ErrorCode>()
            if (value.length > 16) {
                list.add(ErrorCode.FIRST_NAME_INVALID_LENGTH)
            }
            if (!value.all { it in 'А'..'я' }) {
                list.add(ErrorCode.FIRST_NAME_INVALID_CHARS)
            }
            list
        } else {
            listOf(ErrorCode.FIRST_NAME_INVALID_NULL)
        }
    }
}

class LastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return if (!value.isNullOrEmpty()) {
            val list = ArrayList<ErrorCode>()
            if (value.length > 16) {
                list.add(ErrorCode.LAST_NAME_INVALID_LENGTH)
            }
            if (!value.all { it in 'А'..'я' }) {
                list.add(ErrorCode.LAST_NAME_INVALID_CHARS)
            }
            list
        } else {
            listOf(ErrorCode.LAST_NAME_INVALID_NULL)
        }
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return if (!value.isNullOrEmpty()) {
            val list = ArrayList<ErrorCode>()
            if (value.length > 32) {
                list.add(ErrorCode.EMAIL_INVALID_LENGTH)
            }
            if (!value.matches("^[A-z]+@[A-z]+.[A-z]+".toRegex())) {
                list.add(ErrorCode.EMAIL_INVALID_CHARS)
            }
            list
        } else {
            listOf(ErrorCode.EMAIL_INVALID_NULL)
        }
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return if (value != null) {
            val list = ArrayList<ErrorCode>()
            if (value.length != 11) {
                list.add(ErrorCode.SNILS_NUMBER_INVALID_LENGTH)
            }
            if (!value.all { it in '0'..'9' }) {
                list.add(ErrorCode.SNILS_NUMBER_INVALID_CHARS)
            }
            if (list.isEmpty()) {
                if (!checkControlNumber(value)) {
                    list.add(ErrorCode.SNILS_NUMBER_INVALID_CONTROL_NUMBER)
                }
            }
            list
        } else {
            listOf(ErrorCode.SNILS_NUMBER_INVALID_NULL)
        }
    }

    private fun checkControlNumber(value: String): Boolean {
        var i = 9
        var listNumber: ArrayList<Int>
        var sum =
            value.map { x -> x.toString().toInt() * i-- }.stream().limit(9).collect(Collectors.summingInt(Int::toInt))
        val controlNumber = value.substring(9).toInt()
        if (sum == 100 || sum == 101) {
            return controlNumber == 0
        } else if (sum < 100) {
            return controlNumber == sum
        } else {
            return controlNumber == sum % 101
        }
    }
}