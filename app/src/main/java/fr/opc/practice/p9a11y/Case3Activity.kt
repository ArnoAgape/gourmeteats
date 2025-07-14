package fr.opc.practice.p9a11y

import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import fr.opc.practice.p9a11y.databinding.ActivityCase3Binding

class Case3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.validateButton.isEnabled = false

        binding.validateButton.setOnClickListener {
            Toast.makeText(
                this,
                getString(R.string.validate_nickname),
                Toast.LENGTH_SHORT
            )
                .show()
        }

        binding.pseudoEdit.doOnTextChanged { text, _, _, _ ->
            val textLength = text?.length ?: 0

            binding.validateButton.isEnabled = textLength > 2

            binding.pseudoEdit.backgroundTintList = if (textLength > 2) {
                ColorStateList.valueOf(resources.getColor(R.color.green400, theme))
            } else {
                ColorStateList.valueOf(resources.getColor(R.color.red400, theme))
            }
            when (textLength) {
                1, 2 -> {
                    binding.pseudoEdit.postDelayed({
                        binding.pseudoEdit.error = getString(R.string.text_too_short)
                    }, 1200)

                }

                3 -> {
                    binding.pseudoEdit.error = null
                    binding.pseudoEdit.postDelayed({
                        binding.pseudoEdit.announceForAccessibility(getString(R.string.text_correct))
                    }, 100)
                }

                else -> {
                    binding.pseudoEdit.error = null
                }
            }
        }
    }
}
