package sergey.com.getwinner.data.shared_preference

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class IgAuthHelperImpl @Inject constructor(context: Context) : IgAuthHelper {

    private val mSharedPreferences: SharedPreferences
    companion object {
        const val IG_AUTH_HELPER_SHARED_PREF_NAME: String = "ig_auth_helper_shared_pref"
        const val KEY_SESSION_TOKEN: String = "ig_auth_session_token"
    }

    init {
        mSharedPreferences = context.getSharedPreferences(IG_AUTH_HELPER_SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun getSessionToken(): String {
        return mSharedPreferences.getString(KEY_SESSION_TOKEN, "")!!
    }

    override fun storeSessionToken(token: String) {
        mSharedPreferences.edit().putString(KEY_SESSION_TOKEN, token).apply()
    }

    override fun isLogged(): Boolean = getSessionToken().isNotEmpty()
}