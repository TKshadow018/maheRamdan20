package a3labgo.tusar.maheramadan20
import a3labgo.tusar.maheramadan20.databinding.ActivitySortoBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest

class SortoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySortoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }
}