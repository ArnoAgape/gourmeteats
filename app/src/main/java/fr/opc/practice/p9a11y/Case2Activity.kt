package fr.opc.practice.p9a11y

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.addAccessibilityAction
import androidx.core.view.ViewCompat.setStateDescription
import fr.opc.practice.p9a11y.databinding.ActivityCase2Binding
import kotlin.jvm.java

class Case2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase2Binding
    private var isFavourite = false
    private var hasUserInteracted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setAccessibility()

        setFavouriteButtonIcon(false)
        binding.favouriteButton.setOnClickListener {
            isFavourite = !isFavourite
            setFavouriteButtonIcon(isFavourite)
        }

        binding.addRecipeToBasket.setOnClickListener {
            Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT)
                .show()
        }

        binding.recipeCard.setOnClickListener {
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }

        val title = binding.productTitle.text
        val favText = getString(R.string.cd_ajouter_aux_favoris) // ex. "Bouton favoris"
        val panierText = getString(R.string.ajouter_recette) // ex. "Bouton ajouter au panier"

        val fullDescription = "Recette : $title. $favText. $panierText."
        binding.recipeCard.contentDescription = fullDescription


    }

    private fun setFavouriteButtonIcon(isFavourite: Boolean) {
        if (isFavourite) {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_on)
        } else {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_off)
        }
        // 🔊 Annonce pour TalkBack
        if (hasUserInteracted) {
            val announcement = if (isFavourite) {
                getString(R.string.recette_ajout_favoris)
            } else {
                getString(R.string.recette_suppression_favoris)
            }
            setStateDescription(binding.favouriteButton, announcement)
        } else {
            setStateDescription(binding.favouriteButton, null)
            hasUserInteracted = true
        }
    }

    private fun setAccessibility() {
        // Action : Voir la recette
        addAccessibilityAction(
            binding.recipeCard,
            getString(R.string.recette_cookies_chocolat)
        ) { _, _ ->
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
            true
        }

        // Action : Ajouter aux favoris
        addAccessibilityAction(
            binding.recipeCard,
            getString(R.string.cd_ajouter_aux_favoris)
        ) { _, _ ->
            isFavourite = !isFavourite
            setFavouriteButtonIcon(isFavourite)
            true
        }

        // Action : Ajouter au panier
        addAccessibilityAction(
            binding.recipeCard,
            getString(R.string.recette_ajout_au_panier)
        ) { _, _ ->
            Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT).show()
            true
        }
    }

}
