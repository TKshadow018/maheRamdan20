package a3labgo.tusar.maheramadan20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import a3labgo.tusar.maheramadan20.databinding.ActivityKaronBinding
import com.google.android.gms.ads.AdRequest

class KaronActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityKaronBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }
}