package io.kiwi.widget

import android.content.Context
import android.content.res.TypedArray
import android.support.annotation.StyleRes
import android.util.AttributeSet
import android.widget.Button

open class StyledButton : Button {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        applyTypeface(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        applyTypeface(context, attrs)
    }

    override fun setTextAppearance(@StyleRes resId: Int) {
        super.setTextAppearance(resId)

        applyTypeface(context, resId)
    }

    override fun setTextAppearance(context: Context, @StyleRes resId: Int) {
        super.setTextAppearance(context, resId)

        applyTypeface(context, resId)
    }

    private fun applyTypeface(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.StyledTextView)
        applyTypeface(context, typedArray)

        typedArray.recycle()
    }

    private fun applyTypeface(context: Context, @StyleRes resId: Int) {
        val typedArray = context.obtainStyledAttributes(resId, R.styleable.StyledTextView)
        applyTypeface(context, typedArray)

        typedArray.recycle()
    }

    private fun applyTypeface(context: Context, typedArray: TypedArray) {
        val manager = TypefaceManager.getInstance(context.applicationContext)

        if(typedArray.hasValue(R.styleable.StyledTextView_customFont)) {
            val flag = typedArray.getInteger(R.styleable.StyledTextView_customFont, 0)

            typeface = when(flag) {
                0x01 -> manager.nanumBarunGothicRegular
                0x02 -> manager.nanumBarunGothicBold
                0x03 -> manager.nanumBarunGothicLight
                0x04 -> manager.nanumBarunGothicUltraLight
                0x05 -> manager.nanumSquareRegular
                0x06 -> manager.nanumSquareBold
                else -> manager.nanumBarunGothicRegular
            }
        }
    }
}