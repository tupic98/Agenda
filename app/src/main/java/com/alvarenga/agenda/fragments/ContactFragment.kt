package com.alvarenga.agenda.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alvarenga.agenda.Contact
import com.alvarenga.agenda.R
import com.alvarenga.agenda.adapters.ContactAdapter
import java.util.ArrayList

class ContactFragment : Fragment() {
    lateinit var rv: RecyclerView
    var adapter: ContactAdapter? = null
    lateinit var contact: ArrayList<Contact>
    var lmanager: LinearLayoutManager? = null
    private val favF: FavoriteFragment? = null


    companion object { //Companion object simulates static method
        @SuppressLint("StaticFieldLeak")
        var contF: ContactFragment? = null
            private set

        fun newInstance(contactsss: ArrayList<Contact>): ContactFragment {
            val fragmenty = ContactFragment()
            val args = Bundle()
            args.putParcelableArrayList("KEY1", contactsss)
            fragmenty.arguments = args
            return fragmenty
        }

        fun getInstance(): ContactFragment{
            return contF!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            contact = arguments!!.getParcelableArrayList<Contact>("KEY1")
        }
        contF = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = getView()!!.findViewById(R.id.contactsFrag)
        rv.setHasFixedSize(true)

        lmanager = LinearLayoutManager(this.context!!)
        rv.layoutManager = lmanager

        adapter = ContactAdapter(contact, this.context!!, false)
        rv.adapter = adapter
    }

    fun update(contactss: ArrayList<Contact>, position: Int, fav: Boolean) {
        Log.d("LifeCycle", "On Update Fragment 1")
        for (i in contact!!.indices) {
            if (contact!![i].name.equals(contactss[position].name)) {
                contact!![i].isFav = fav
                Log.d("LifeCycle Update", contact!![i].name)
            }
        }
        adapter = ContactAdapter(contact, this.context!!, false)
        rv.adapter = adapter
    }

}
