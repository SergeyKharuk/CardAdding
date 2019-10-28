package sergey.com.card_adding.presentation.add_card

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_add_card.*
import sergey.com.card_adding.R
import sergey.com.card_adding.data.model.request.CreditCard
import sergey.com.card_adding.presentation.base.BaseActivity
import sergey.com.card_adding.presentation.common.ExpireDateTextWatcher

class AddCardActivity : BaseActivity<AddCardPresenter>(), AddCardView, ExpireDateTextWatcher.ExpireDateCallbacks {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            val intent = Intent(context, AddCardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent .FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }

    private lateinit var mExpireDateTextWatcher: ExpireDateTextWatcher

    override val layoutResource: Int
        get() = R.layout.activity_add_card

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    override fun requestFocusToSecurityCode() {
        edit_security_code.requestFocus()
    }

    override fun showErrorMessage(text: String) {
        showMessage(text)
    }

    override fun addCardSuccess(text: String) {
        val adb: AlertDialog.Builder = AlertDialog.Builder(this)
        adb.setTitle(getString(R.string.card_successfully_added))
        adb.setMessage(text)
        adb.show()
    }

    private fun setupViews() {
        mExpireDateTextWatcher = ExpireDateTextWatcher(this, edit_expire_date)
        edit_expire_date.addTextChangedListener(mExpireDateTextWatcher)
        edit_card_number1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 4 && edit_card_number1.selectionStart == 4) edit_card_number2.requestFocus()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        edit_card_number2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 4 && edit_card_number2.selectionStart == 4) edit_card_number3.requestFocus()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (before > 0 && s.toString().isEmpty()) edit_card_number1.requestFocus()
            }

        })
        edit_card_number3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 4 && edit_card_number3.selectionStart == 4) edit_card_number4.requestFocus()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (before > 0 && s.toString().isEmpty()) edit_card_number2.requestFocus()
            }

        })
        edit_card_number4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (before > 0 && s.toString().isEmpty()) edit_card_number3.requestFocus()
            }

        })
        text_add_card.setOnClickListener {
            if (isFieldsValid()) addCard()
            else showMessage(getString(R.string.pelase_check_your_inputs))
        }
    }

    private fun addCard() {
        presenter.addCard(
            CreditCard(
                edit_card_name.text.toString(),
                (edit_card_number1.text.toString() + edit_card_number2.text.toString() + edit_card_number3.text.toString() + edit_card_number4.text.toString()).toLong(),
                edit_expire_date.text.toString()
            )
        )
    }

    private fun isFieldsValid(): Boolean {
        return edit_card_number1.length() == 4 &&
                edit_card_number2.length() == 4 &&
                edit_card_number3.length() == 4 &&
                edit_card_number4.length() == 4 &&
                mExpireDateTextWatcher.isExpireDateValid() &&
                edit_security_code.text.toString().isNotEmpty()
    }

}