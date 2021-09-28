import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(5, exception.errorCode.size)
        assertEquals(exception.errorCode[0], ErrorCode.PHONE_NUMBER_INVALID_FIRST_CHARACTER)
        assertEquals(exception.errorCode[1], ErrorCode.PHONE_NUMBER_INVALID_LENGTH)
        assertEquals(exception.errorCode[2], ErrorCode.FIRST_NAME_INVALID_NULL)
        assertEquals(exception.errorCode[3], ErrorCode.LAST_NAME_INVALID_LENGTH)
        assertEquals(exception.errorCode[4], ErrorCode.SNILS_NUMBER_INVALID_CONTROL_NUMBER)
    }

    @Test
    fun `fail save client with validation errors name and snils`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(3, exception.errorCode.size)
        assertEquals(exception.errorCode[0], ErrorCode.FIRST_NAME_INVALID_CHARS)
        assertEquals(exception.errorCode[1], ErrorCode.SNILS_NUMBER_INVALID_LENGTH)
        assertEquals(exception.errorCode[2], ErrorCode.SNILS_NUMBER_INVALID_CHARS)
    }

    @Test
    fun `fail save client with validation errors null`() {
        val client = getClientFromJson("/fail/user_null.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(5, exception.errorCode.size)
        assertEquals(exception.errorCode[0], ErrorCode.PHONE_NUMBER_INVALID_NULL)
        assertEquals(exception.errorCode[1], ErrorCode.FIRST_NAME_INVALID_NULL)
        assertEquals(exception.errorCode[2], ErrorCode.LAST_NAME_INVALID_NULL)
        assertEquals(exception.errorCode[3], ErrorCode.EMAIL_INVALID_NULL)
        assertEquals(exception.errorCode[4], ErrorCode.SNILS_NUMBER_INVALID_NULL)
    }

    @Test
    fun `fail save client with validation errors lastname and email`() {
        val client = getClientFromJson("/fail/user_with_bad_lastname_and_email.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(3, exception.errorCode.size)
        assertEquals(exception.errorCode[0], ErrorCode.LAST_NAME_INVALID_CHARS)
        assertEquals(exception.errorCode[1], ErrorCode.EMAIL_INVALID_LENGTH)
        assertEquals(exception.errorCode[2], ErrorCode.EMAIL_INVALID_CHARS)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}