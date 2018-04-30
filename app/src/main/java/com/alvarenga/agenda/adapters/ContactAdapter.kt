package com.alvarenga.agenda.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.alvarenga.agenda.Contact
import com.alvarenga.agenda.R
import com.alvarenga.agenda.fragments.ContactFragment
import com.alvarenga.agenda.fragments.FavoriteFragment
import java.util.ArrayList

class ContactAdapter(private var Contact: ArrayList<Contact>, private var context: Context, private var favorIs: Boolean?) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private lateinit var contact:Contact


    companion object {
        @SuppressLint("StaticFieldLeak")
        private var contF: ContactFragment? = null
        @SuppressLint("StaticFieldLeak")
        private var favF: FavoriteFragment? = null

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_cardview, parent, false)
        return ContactViewHolder(v, this.favorIs!!)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        contF = ContactFragment.getInstance()
        favF = FavoriteFragment.getInstance()

        holder.name.setText(Contact[position].name)
        holder.img.setImageResource(Contact[position].img)
        holder.butt.setImageResource(if (Contact[position].isFav) R.drawable.star_full else R.drawable.star_empty)
        holder.butt.setOnClickListener { v ->
            Toast.makeText(v.context, Contact[position].name, Toast.LENGTH_SHORT).show()

            if ((!favorIs!!)) {
                if (!holder.fav) {
                    Contact[position].isFav = true
                    holder.butt.setImageResource(R.drawable.star_full)
                    holder.fav = true
                } else {
                    Contact[position].isFav = false
                    holder.butt.setImageResource(R.drawable.star_empty)
                    holder.fav = false
                }

            } else {
                if (!holder.fav) {
                    Contact[position].isFav = true
                    holder.butt.setImageResource(R.drawable.star_full)
                    holder.fav = true
                } else {
                    Contact[position].isFav = false
                    holder.butt.setImageResource(R.drawable.star_empty)
                    holder.fav = false
                }
                contF!!.update(Contact, position, holder.fav)
            }
            favF!!.update(Contact)
        }
    }

    override fun getItemCount(): Int {
        return Contact.size
    }

    class ContactViewHolder(itemView: View, internal var fav: Boolean) : RecyclerView.ViewHolder(itemView) {
        internal var card: CardView
        internal var name: TextView
        internal var img: ImageView
        internal var butt: ImageView

        init {
            card = itemView.findViewById(R.id.cardView)
            name = itemView.findViewById(R.id.name)
            img = itemView.findViewById(R.id.img)
            butt = itemView.findViewById(R.id.butt)
        }

    }



}