package sergey.com.getwinner.data.shared_preference

interface IgAuthHelper {

    fun storeSessionToken(token: String)
    fun getSessionToken(): String
    fun isLogged(): Boolean

}