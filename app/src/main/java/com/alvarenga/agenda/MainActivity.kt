package com.alvarenga.agenda

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import com.alvarenga.agenda.adapters.ViewPagerAdapter
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var contact: ArrayList<Contact>? = null
    internal lateinit var tabbything: TabLayout
    internal lateinit var viewy: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        prepareSeries()
        val bungalo = Bundle()
        bungalo.putParcelableArrayList("KEY", contact)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        viewy = findViewById(R.id.Pager)
        tabbything = findViewById(R.id.mainTab)

        val adapter = ViewPagerAdapter(this, supportFragmentManager, bungalo)

        viewy.setAdapter(adapter)
        tabbything.setupWithViewPager(viewy)
    }

    fun prepareSeries() {
        val TAG = "Mensaje"
        contact = ArrayList<Contact>()
        contact!!.add(Contact("How I Met Your Mother", 12, 72936840, "Vacil",R.drawable.ic_launcher_background, false))
        contact!!.add(Contact("Black Mirror", 13, 72936840, "Ufff buen vacil", R.drawable.ic_launcher_background,false))
        contact!!.add(Contact("Mr Robot", 14, 72936840, "Buen vacil", R.drawable.ic_launcher_background, false))
        contact!!.add(Contact("Breaking Bad", 15, 72936840, "Ufff buen vacil", R.drawable.ic_launcher_background, false))

    }
}
