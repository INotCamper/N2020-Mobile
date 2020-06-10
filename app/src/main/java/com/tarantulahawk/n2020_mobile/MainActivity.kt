package com.tarantulahawk.n2020_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.tarantulahawk.n2020_mobile.Fragment.ConfigFragment
import com.tarantulahawk.n2020_mobile.Fragment.HomeFragment
import com.tarantulahawk.n2020_mobile.Fragment.NoticiasFragment
import com.tarantulahawk.n2020_mobile.Fragment.PerfilFragment
import kotlinx.android.synthetic.main.activity_main.*

public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragment()
        val notFragment = NoticiasFragment()
        val perfFragment = PerfilFragment()
        val confFragment = ConfigFragment()
        changeCurrentFragment(homeFragment)

        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.bt_home->changeCurrentFragment(homeFragment)
                R.id.bt_news->changeCurrentFragment(notFragment)
                R.id.bt_account->changeCurrentFragment(perfFragment)
                R.id.bt_config->changeCurrentFragment(confFragment)
            }
            true
        }
    }

    private fun changeCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
}