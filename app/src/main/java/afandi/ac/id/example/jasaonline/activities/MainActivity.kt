package afandi.ac.id.example.jasaonline.activities

import afandi.ac.id.example.jasaonline.R
import afandi.ac.id.example.jasaonline.fragments.BerandaFragment
import afandi.ac.id.example.jasaonline.fragments.JasaFragment
import afandi.ac.id.example.jasaonline.fragments.ProfileFragment
import afandi.ac.id.example.jasaonline.helpers.Config
import afandi.ac.id.example.jasaonline.helpers.SessionHandler
import afandi.ac.id.example.jasaonline.models.User
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var berandaFragment: BerandaFragment
    lateinit var jasaFragment: JasaFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var session : SessionHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        val fragmentId = intent.getIntExtra(
            Config.EXTRA_FRAGMENT_ID,
            R.id.nav_beranda)
        openFragment(fragmentId)
        session = SessionHandler(applicationContext)
        val user: User? = session.getUser()
        if(user != null) {
            val headerView: View = navView.getHeaderView(0)
            val tvNama: TextView =
                headerView.findViewById(R.id.tvNamaHeader)
            tvNama.text = user.nama
            val tvEmail: TextView =
                headerView.findViewById(R.id.tvEmailHeader)
            tvEmail.text = user.email
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        openFragment(item.itemId)
        return true
    }
    private fun openFragment(fragment_id: Int){
        when (fragment_id) {
            R.id.nav_beranda -> {
                berandaFragment =
                    BerandaFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, berandaFragment)

                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_jasa_pengguna -> {
                jasaFragment =
                    JasaFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, jasaFragment)

                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_profile -> {
                profileFragment =
                    ProfileFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, profileFragment)

                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_logout -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Keluar Akun")
                builder.setMessage("Apakah anda yakin keluar dari akun saat ini?")
                builder.setIcon(R.drawable.ic_baseline_exit_to_app_black_24)
                builder.setPositiveButton("Ya") { dialog, _ ->
                    dialog.dismiss()
                    session.removeUser()
                    val intent = Intent(applicationContext,
                        LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                builder.setNegativeButton("Tidak"){dialog, _ ->
                    dialog.dismiss()
                }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
    }
}
