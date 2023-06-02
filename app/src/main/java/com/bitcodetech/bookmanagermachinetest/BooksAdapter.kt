package com.bitcodetech.bookmanagermachinetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.bookmanagermachinetest.databinding.BookViewBinding
import com.squareup.picasso.Picasso

class BooksAdapter(private val books:ArrayList<Book>):RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    interface OnBookClickListener{
        fun onImageClicked(booksAdapter:BooksAdapter,book: Book,position: Int)
    }
    var onBookClickListener:OnBookClickListener?=null

    inner class BookViewHolder(view:View):RecyclerView.ViewHolder(view){
        val binding:BookViewBinding

        init {
            binding=BookViewBinding.bind(view)

            binding.imgBook.setOnClickListener {
                onBookClickListener?.onImageClicked(
                    this@BooksAdapter,
                    books[adapterPosition],
                    adapterPosition
                )
            }
        }
    }

    override fun getItemCount()=books.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.book_view,null))
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book=books[position]
        holder.binding.book=book

        Picasso.get()
            .load(book.image)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(holder.binding.imgBook)
    }
}