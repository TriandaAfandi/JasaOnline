package afandi.ac.id.example.jasaonline.activities


import afandi.ac.id.example.jasaonline.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import afandi.ac.id.example.jasaonline.helpers.Config

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, Config.SPLASH_TIME_OUT)
    }
}