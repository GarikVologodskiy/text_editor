/*
Интерфейс имеет следующие элементы:

editTextView (id editText) - поле для ввода текста.
TextView (id stats_view) - поле, отображающее количество слов.
TextView (id unsaved_changes_view) - поле, отображающее статус сохранения.
Button (id save_button) - кнопка сохранения изменений в тексте.
Button (id load_button) - кнопка загрузки текста.
Button (id clear_button) - кнопка стирания текста.
Пользователь вводит текст в поле ввода, по мере ввода отображается число введенных им слов. Словом считается любое количество латинских или кириллических символов, а также знаков "_".

Если введенный текст отличается от сохраненного в памяти то отображается статус "Unsaved changes", иначе все изменения сохранены и статус "All changes saved".

После нажатия кнопки save текст сохраняется в памяти.

По нажатию кнопки clear текст из поля ввода стирается.

Сохранять данные куда-то кроме памяти не требуется.
*/

package com.example.company.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var prefs = getSharedPreferences("PREF", Context.MODE_PRIVATE)
        //var editor = prefs.edit()
        //val regexp = Regex("^A-Za-zА-Яа-я0-9_")
        var saver = ""
        val unsavedText = "Unsaved changes"
        val savedText = "All changes saved"


        fun save() {
            saver = editText.text.toString() //editor.putString("id", editText.text.toString()).apply()
            unsaved_changes_view.text = savedText
        }

        fun load() {
            editText.setText(saver) //prefs.getString("id", editor).toString()
            //unsaved_changes_view.text = saver
            /*if(saver == editText.text.toString()) {
                unsaved_changes_view.text = savedText
            }
            else {
                unsaved_changes_view.text = unsavedText
            }*/
        }

        fun clear() {
            editText.setText("")
            unsaved_changes_view.text = ""
            stats_view.text = ""
        }

        // s.toString().split("[^A-Za-zА-Яа-я0-9_]".toRegex()).filter { it != "" }.size
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val edit_str = s.toString().split("[^A-Za-zА-Яа-я0-9_]".toRegex()).filter { it != "" }
                /*option #2 editText.text.replace(regexp, " ").trim().split(" ")*/
                stats_view.text = edit_str.size.toString()

                    if(saver == editText.text.toString()) {
                        unsaved_changes_view.text = savedText
                    } else {
                        unsaved_changes_view.text = unsavedText
                    }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        // call functions

        save_button.setOnClickListener {save()}

        load_button.setOnClickListener {load()}

        clear_button.setOnClickListener {clear()}
    }
}