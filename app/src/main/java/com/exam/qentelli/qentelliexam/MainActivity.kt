package com.exam.qentelli.qentelliexam

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.exam.qentelli.qentelliexam.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.idIncludeLayout.idDecimalEditText.setSelection((binding.idIncludeLayout.idDecimalEditText.text)!!.length)

        binding.idIncludeLayout.idDecimalEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val text = s?.toString().orEmpty()
                binding.idIncludeLayout.idDecimalEditText.removeTextChangedListener(this)
                if(text.isNotEmpty()){
                    val formatedText = formatText(text.toFloat())
                    binding.idIncludeLayout.idDecimalTextVew.setText(formatedText)
                } else {
                    binding.idIncludeLayout.idDecimalTextVew.setText("00.00000")
                }
                binding.idIncludeLayout.idDecimalEditText.setSelection(binding.idIncludeLayout.idDecimalEditText.text!!.length)
                binding.idIncludeLayout.idDecimalEditText.addTextChangedListener(this)
            }

            override fun afterTextChanged(editable: Editable?) {

            }
        })
    }

        /*binding.idIncludeLayout.idDecimalEditText.setText("$00.00000")
        binding.idIncludeLayout.idDecimalEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                binding.idIncludeLayout.idDecimalEditText.removeTextChangedListener(this)
                val cleanString: String = s.toString().replace("[$]", "")
                val parsed = BigDecimal(cleanString).setScale(2, BigDecimal.ROUND_FLOOR)
                    .divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
                val formatted = NumberFormat.getCurrencyInstance().format(parsed)
                binding.idIncludeLayout.idDecimalEditText.setText(formatted)
                binding.idIncludeLayout.idDecimalEditText.setSelection(formatted.length)
                binding.idIncludeLayout.idDecimalEditText.addTextChangedListener(this)
                binding.idIncludeLayout.idDecimalEditText.addTextChangedListener(this)
            }

        })*/

    fun formatText(text: Float): String {
        val df = DecimalFormat("00.#####")
        val value = text / 100000
        return df.format(value)
    }
}