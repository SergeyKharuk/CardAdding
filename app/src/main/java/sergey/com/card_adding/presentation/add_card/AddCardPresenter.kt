package sergey.com.card_adding.presentation.add_card

import com.google.gson.Gson
import sergey.com.card_adding.data.model.request.CreditCard
import sergey.com.card_adding.presentation.base.BasePresenter

class AddCardPresenter :  BasePresenter<AddCardView>() {

    fun addCard(creditCard: CreditCard) {
        //todo: make request
        // if result success:
        getView().addCardSuccess(Gson().toJson(creditCard))
    }

}