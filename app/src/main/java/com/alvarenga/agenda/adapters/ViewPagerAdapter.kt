package com.alvarenga.agenda.adapters

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alvarenga.agenda.Contact
import com.alvarenga.agenda.R
import com.alvarenga.agenda.fragments.ContactFragment
import com.alvarenga.agenda.fragments.FavoriteFragment

class ViewPagerAdapter(private val Contexte: Context, fm: FragmentManager, private val bungalo: Bundle) : FragmentPagerAdapter(fm) {
    private val contact: ContactFragment
    private val favContact: FavoriteFragment

    init {
        this.contact = ContactFragment.newInstance(bungalo.getParcelableArrayList<Contact>("KEY"))
        this.favContact = FavoriteFragment.newInstance(bungalo.getParcelableArrayList<Contact>("KEY"))
    }

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            contact
        } else if (position == 1) {
            favContact
        } else {
            contact
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return Contexte.getString(R.string.tab1_text)
            1 -> return Contexte.getString(R.string.tab2_text)
            else -> return null
        }
    }
}