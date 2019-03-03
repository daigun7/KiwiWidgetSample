package io.kiwi.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import java.text.DecimalFormat

class CurrencyEditText : StyledEditText, TextWatcher {
    private var mText = ""

    val intValue: Int
        @Throws(NumberFormatException::class)
        get() = Integer.valueOf(text.toString().replace(",", ""))

    val longValue: Long
        @Throws(NumberFormatException::class)
        get() = java.lang.Long.valueOf(text.toString().replace(",", ""))

    val floatValue: Float
        @Throws(NumberFormatException::class)
        get() = java.lang.Float.valueOf(text.toString().replace(",", ""))

    val doubleValue: Double
        @Throws(NumberFormatException::class)
        get() = java.lang.Double.valueOf(text.toString().replace(",", ""))

    constructor(context: Context) : super(context) {
        addTextChangedListener(this)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        addTextChangedListener(this)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        addTextChangedListener(this)
    }

    override fun afterTextChanged(s: Editable?) {
        val text = s.toString()
        if (!text.isEmpty() && text != mText) {
            try {
                val decimalFormat = DecimalFormat.getInstance() as DecimalFormat
                decimalFormat.applyPattern("#,###")
                mText = decimalFormat.format(java.lang.Long.parseLong(text.replace("\\D".toRegex(), "")))

            } catch (e: NumberFormatException) {

            } finally {
                setText(mText)
                setSelection(mText.length)
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}