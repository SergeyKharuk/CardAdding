package sergey.com.card_adding.data.model.request

import com.google.gson.annotations.SerializedName

data class CreditCard(
    @SerializedName("card_name") val mCardName: String = "",
    @SerializedName("card_number") val mCardNumber: Long = 0,
    @SerializedName("expire_card_date") val mExpireDate: String = ""
)