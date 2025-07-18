package fr.opc.practice.p9a11y

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import fr.opc.practice.p9a11y.databinding.ActivityCase3Binding

class Case3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase3Binding
    private val handler = Handler(Looper.getMainLooper())
    private val debounceRunnable = Runnable {
        validatePseudo(binding.pseudoEdit.text.toString().trim())
    }

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

            // Annule le Runnable précédent (si l'utilisateur tape vite)
            handler.removeCallbacksAndMessages(debounceRunnable)

            // Relance un nouveau Runnable avec délai
            handler.postDelayed(debounceRunnable, 1000)
        }
    }
    private fun validatePseudo(text: String) {

        binding.pseudoEdit.backgroundTintList = if (text.length > 2) {
            ColorStateList.valueOf(resources.getColor(R.color.green400, theme))
        } else {
            ColorStateList.valueOf(resources.getColor(R.color.red400, theme))
        }

        when (text.length) {
            1, 2 -> binding.pseudoEdit.error = getString(R.string.text_too_short)

            3 -> {
                binding.pseudoEdit.error = null
                binding.pseudoEdit.announceForAccessibility(getString(R.string.text_correct))
            }

            else -> binding.pseudoEdit.error = null
        }
    }
}
