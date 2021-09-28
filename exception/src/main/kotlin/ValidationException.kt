class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(", ") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    PHONE_NUMBER_INVALID_FIRST_CHARACTER(1, "Номер телефона должен начинаться с цифры 7 или 8"),
    PHONE_NUMBER_INVALID_NULL(2, "Номер телефона не может быть пустым"),
    PHONE_NUMBER_INVALID_LENGTH(3, "Номер телефона должен состоять из 11 цифр"),
    PHONE_NUMBER_INVALID_CHARS(4, "Номер телефона должен содержать только цифры"),

    FIRST_NAME_INVALID_LENGTH(11, "Имя должно быть не более 16 символов"),
    FIRST_NAME_INVALID_CHARS(12, "Имя должно состоять только из кириллицы"),
    FIRST_NAME_INVALID_NULL(13, "Имя ине может быть пустым"),

    LAST_NAME_INVALID_LENGTH(21, "Фамилия должна быть не более 16 символов"),
    LAST_NAME_INVALID_CHARS(22, "Фамилия должна состоять только из кириллицы"),
    LAST_NAME_INVALID_NULL(23, "Фамилия не может быть пустой"),

    EMAIL_INVALID_LENGTH(31, "Email должен быть не более 32 символов"),
    EMAIL_INVALID_CHARS(32, "Email должен состоять только из латиницы и должен содержать @имя_домена"),
    EMAIL_INVALID_NULL(33, "Email не может быть пустым"),

    SNILS_NUMBER_INVALID_NULL(41, "СНИЛС не может быть пустым"),
    SNILS_NUMBER_INVALID_LENGTH(42, "СНИЛС должен состоять из 11 цифр"),
    SNILS_NUMBER_INVALID_CHARS(43, "СНИЛС должен содержать только цифры"),
    SNILS_NUMBER_INVALID_CONTROL_NUMBER(44, "Ошибка валидации контрольного числа СНИЛС")
}