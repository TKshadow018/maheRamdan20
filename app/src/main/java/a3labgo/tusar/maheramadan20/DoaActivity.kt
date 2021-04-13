package a3labgo.tusar.maheramadan20

import a3labgo.tusar.maheramadan20.databinding.ActivityDoaBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest

class DoaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDoaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }
}