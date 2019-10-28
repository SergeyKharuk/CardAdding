package sergey.com.card_adding.presentation.common

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.StringBuilder
import java.util.*
import java.util.regex.Pattern

class ExpireDateTextWatcher(private val mExpireDateCallbacks: ExpireDateCallbacks, private val edit_expire_date: EditText) : TextWatcher {

    interface ExpireDateCallbacks {
        fun requestFocusToSecurityCode()
        fun showErrorMessage(text: String)
    }

    private val MAX_EXPIRE_YEAR = 50
    private val MIN_EXPIRE_YEAR = Calendar.getInstance().get(Calendar.YEAR) - 2000
    private val REG_EX_MIN_TO_MAX_YEAR = generateRegExFromMinToMaxYear()
    private var REG_EX_MM_YY = "^(0[1-9]|[1-9]|1[0-2])/($REG_EX_MIN_TO_MAX_YEAR)$"
    private var pattern = Pattern.compile(REG_EX_MM_YY)
    private var previousInput: String = ""
    private var previousSelectionPosition: Int = 0

    override fun afterTextChanged(s: Editable) {

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val input = s.toString()
        if (pattern.matcher(input).matches() && edit_expire_date.selectionStart == input.length) {
            mExpireDateCallbacks.requestFocusToSecurityCode()
            previousInput = input
            return
        }

        if (input.contains("/")) {
            val enteredMonth = input.substring(0, input.indexOf("/"))
            val enteredYear = input.substring(input.indexOf("/") + 1)
            if (enteredYear.length > 2 || enteredMonth.length > 2) {
                edit_expire_date.setText(previousInput)
                return
            }
            if (enteredYear.isNotEmpty() && enteredYear.toInt() > MAX_EXPIRE_YEAR) {
                previousInput = input.substring(0, input.length - 2) + MAX_EXPIRE_YEAR.toString()
                edit_expire_date.setText(previousInput)
                mExpireDateCallbacks.showErrorMessage("Invalid year. Max available is ${2000 + MAX_EXPIRE_YEAR}")
                return
            }
            if (enteredYear.length == 2 && enteredYear.toInt() < MIN_EXPIRE_YEAR) {
                previousInput = input.substring(0, input.length - 2) + MIN_EXPIRE_YEAR.toString()
                edit_expire_date.setText(previousInput)
                mExpireDateCallbacks.showErrorMessage("Invalid year. Min available is ${2000 + MIN_EXPIRE_YEAR}")
                return
            }
        }

        when {
            input.length >= previousInput.length -> {
                when {
                    input.length == 1 -> {
                        if (input != "/" && input.toInt() >= 2) {
                            edit_expire_date.setText(String.format("%s/", input))
                            edit_expire_date.setSelection(edit_expire_date.text.length)
                            return
                        }
                    }
                    input.length == 2 && !input.endsWith("/") -> {
                        if (input.toInt() <= 12) {
                            edit_expire_date.setText(String.format("%s/", input))
                        } else {
                            edit_expire_date.setText(String.format("%s/%s", input.substring(0, 1), input.substring(1, input.length)))
                        }
                        edit_expire_date.setSelection(edit_expire_date.text.length)
                        return
                    }
                    input.length > 2 && input.substring(0, 2).matches(Regex("(1[3-9]|[2-9][0-9])")) -> {
                        previousSelectionPosition = edit_expire_date.selectionStart
                        edit_expire_date.setText(previousInput)
                        edit_expire_date.setSelection(previousSelectionPosition)
                        return
                    }
                }

            }
            else -> {
                try {
                    if (edit_expire_date.selectionStart == previousInput.indexOf("/")) {
                        val tempString = previousInput.removeRange(previousInput.indexOf("/") - 1 until previousInput.indexOf("/"))
                        previousInput = input
                        edit_expire_date.setText(tempString)
                    }
                    if (previousInput.endsWith("/")) {
                        val tempString = previousInput.substring(0, previousInput.indexOf("/") - 1)
                        previousInput = input
                        edit_expire_date.setText(tempString)
                        edit_expire_date.setSelection(edit_expire_date.text.length)
                    }
                } catch (e: Exception) {
                    edit_expire_date.setText(previousInput)
                    return
                }
            }
        }

        previousInput = input
    }

    private fun generateRegExFromMinToMaxYear(): String {
        val sb = StringBuilder()
        for (year in MIN_EXPIRE_YEAR..MAX_EXPIRE_YEAR) {
            sb.append(year.toString())
            if (year != MAX_EXPIRE_YEAR) sb.append("|")
        }
        return sb.toString()
    }

    fun isExpireDateValid(): Boolean {
        return pattern.matcher(edit_expire_date.text.toString()).matches()
    }

}