package io.kiwi.widget.sample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        auto_focused_edit_text1.setNextEditText(auto_focused_edit_text2)

        auto_focused_edit_text2.setPrevEditText(auto_focused_edit_text1)
        auto_focused_edit_text2.setNextEditText(auto_focused_edit_text3)

        auto_focused_edit_text3.setPrevEditText(auto_focused_edit_text2)
    }
}
