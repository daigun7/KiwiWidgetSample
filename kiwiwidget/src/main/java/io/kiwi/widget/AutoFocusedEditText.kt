package io.kiwi.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputConnectionWrapper
import android.widget.EditText

class AutoFocusedEditText : StyledEditText, TextWatcher {
    private var mPrevEditText: EditText? = null
    private var mNextEditText: EditText? = null
    private var mMaxLength: Int = 0

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        for(i in 0 until attrs.attributeCount) {
            if("maxLength" == attrs.getAttributeName(i)) {
                mMaxLength = attrs.getAttributeIntValue(i, 0)
                addTextChangedListener(this)
                break
            }
        }
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        for(i in 0 until attrs.attributeCount) {
            if("maxLength" == attrs.getAttributeName(i)) {
                mMaxLength = attrs.getAttributeIntValue(i, 0)
                addTextChangedListener(this)
                break
            }
        }
    }

    fun setPrevEditText(prev: EditText) {
        this.mPrevEditText = prev
    }

    fun setNextEditText(next: EditText) {
        this.mNextEditText = next
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if(s.isEmpty()) {
            mPrevEditText?.also {
                it.setSelection(mPrevEditText!!.text.length)
                it.requestFocus()
            }
        } else if(s.length == mMaxLength) {
            mNextEditText?.also {
                it.setSelection(0)
                it.requestFocus()
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable) {}

    override fun onCreateInputConnection(outAttrs: EditorInfo?): InputConnection {
        return MyInputConnection(super.onCreateInputConnection(outAttrs), true)
    }

    private inner class MyInputConnection(target: InputConnection, mutable: Boolean)
        : InputConnectionWrapper(target, mutable) {

        override fun sendKeyEvent(event: KeyEvent): Boolean {
            if(event.action == KeyEvent.ACTION_DOWN) {
                if(event.keyCode == KeyEvent.KEYCODE_DEL && selectionStart == 0) {
                    mPrevEditText?.also {
                        it.setSelection(mPrevEditText!!.text.length)
                        it.requestFocus()
                    }
                } else if(event.keyCode != KeyEvent.KEYCODE_DEL && selectionStart == mMaxLength) {
                    mNextEditText?.also {
                        it.setSelection(0)
                        it.requestFocus()
                    }
                }
            }

            return super.sendKeyEvent(event)
        }
    }
}