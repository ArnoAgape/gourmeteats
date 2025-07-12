package fr.opc.practice.p9a11y

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipe = assets.open("cookies_au_chocolat.txt")
            .bufferedReader()
            .use { it.readText() }
        binding.recipeCookies.text = recipe
    }
}
