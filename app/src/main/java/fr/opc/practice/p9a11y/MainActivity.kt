package fr.opc.practice.p9a11y

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.goToCase1.setOnClickListener {
            val intent = Intent(this, Case1Activity::class.java)
            startActivity(intent)
        }

        binding.goToCase2.setOnClickListener {
            val intent = Intent(this, Case2Activity::class.java)
            startActivity(intent)
        }

        binding.goToCase3.setOnClickListener {
            val intent = Intent(this, Case3Activity::class.java)
            startActivity(intent)
        }
    }
}
