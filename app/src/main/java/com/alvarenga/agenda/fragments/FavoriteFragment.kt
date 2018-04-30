package com.alvarenga.agenda.fragments

import android.annotation.SuppressLint
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


class FavoriteFragment: Fragment(){
    private var rv: RecyclerView? = null
    private var adapter: ContactAdapter? = null
    private var lmanager: LinearLayoutManager? = null
    private var contact: ArrayList<Contact>? = null
    private val contF: ContactFragment? = null

    companion object {
        var favcontact: ArrayList<Contact>? = null
        @SuppressLint("StaticFieldLeak")
        var favF: FavoriteFragment? = null
            private set

        fun newInstance(contactss: ArrayList<Contact>): FavoriteFragment {
            val fragmenty = FavoriteFragment()
            val args = Bundle()
            args.putParcelableArrayList("KEY2", contactss)
            fragmenty.arguments = args
            return fragmenty
        }

        fun getInstance():FavoriteFragment{
            return favF!!
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            contact = arguments!!.getParcelableArrayList<Contact>("KEY2")
        }
        favF = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favcontact = ArrayList<Contact>()

        rv = getView()!!.findViewById(R.id.favFrag)
        rv!!.setHasFixedSize(true)

        lmanager = LinearLayoutManager(this.context!!)
        rv!!.layoutManager = lmanager

        favcontact = prepareFavContact(contact)
        adapter = ContactAdapter(favcontact!!, this.context!!, true)

        rv!!.adapter = adapter
    }

    override fun onResume() {
        adapter!!.notifyDataSetChanged()
        Log.d("LifeCycle", "On Resume")
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    fun prepareFavContact(contactsss: ArrayList<Contact>?): ArrayList<Contact> {
        val favcontact = ArrayList<Contact>()
        for (i in contactsss!!.indices) {
            if (contact!![i].isFav) {
                favcontact.add(Contact(contact!![i].name, contact!![i].ID, contact!![i].number, contact!![i].dir, contact!![i].img, contact!![i].isFav))
            }
        }
        return favcontact
    }

    fun updateFavSeries(contactssss: ArrayList<Contact>) {
        val favcontact = ArrayList<Contact>()
        for (i in contactssss.indices) {
            if (contactssss[i].isFav) {
                favcontact.add(Contact(contactssss[i].name, contactssss[i].ID, contactssss[i].number, contactssss[i].dir, contactssss[i].img, contactssss[i].isFav))
                adapter!!.notifyDataSetChanged()
            }
        }
    }

    fun update(serieees: ArrayList<Contact>) {
        Log.d("LifeCycle", "On Update")
        favcontact = prepareFavContact(serieees)
        adapter = ContactAdapter(favcontact!!, this.context!!, true)
        rv!!.adapter = adapter
    }

}



