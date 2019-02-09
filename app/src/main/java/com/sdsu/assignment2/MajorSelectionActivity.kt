package com.sdsu.assignment2

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

class MajorSelectionActivity : AppCompatActivity() {

    private var fm: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_major_selection)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)

        fm = supportFragmentManager
        val ft = fm!!.beginTransaction()
        ft.add(R.id.fl_container, MajorFragment(), MajorFragment::class.java.canonicalName)
        ft.addToBackStack(MajorFragment::class.java.canonicalName)
        ft.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {
            finish()
        }
    }
}
