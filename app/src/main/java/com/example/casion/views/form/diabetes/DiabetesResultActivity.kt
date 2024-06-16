package com.example.casion.views.form.diabetes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.casion.R
import com.example.casion.data.remote.response.Data
import com.example.casion.databinding.ActivityDiabetesResultBinding
import com.example.casion.util.showToast
import com.example.casion.views.main.MainActivity
import com.example.casion.views.mapview.MapsActivity

class DiabetesResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiabetesResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiabetesResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data: Data? = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(PREDICTION_RESULT, Data::class.java)
        } else {
            intent.getParcelableExtra(PREDICTION_RESULT)
        }

        if (data != null) {
            setResult(data)
        }

        clickEvents()
    }

    private fun setResult(data: Data) {
        val diabetesType = Integer.parseInt(data.result)
        when (diabetesType) {
            0 -> {
                binding.gauge.setImageResource(R.drawable.gauge_normal)
            }
            1 -> {
                binding.gauge.setImageResource(R.drawable.gauge_prediabetes)
            }
            2 -> {
                binding.gauge.setImageResource(R.drawable.gauge_diabetes)
            }
            else -> {
                showToast(this, "Hasil prediksi tidak valid")
            }
        }

        binding.persentasePeluang.text = data.confidenceScore
        binding.description.text = data.description
        binding.sugesstion.text = data.suggestion
    }

    private fun clickEvents() {
        binding.PuskesmasButton.setOnClickListener {
            startActivity(Intent(this, MapsActivity::class.java))
        }

        binding.BacktoHomeButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
        }
    }

    companion object {
        const val PREDICTION_RESULT = "prediction_result"
    }
}