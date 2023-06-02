package com.bitcodetech.bookmanagermachinetest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bitcodetech.bookmanagermachinetest.databinding.BookFragmentBinding
import com.google.gson.Gson
import java.lang.reflect.Method

class BookFragment:Fragment() {

    private lateinit var binding:BookFragmentBinding
    private lateinit var books:ArrayList<Book>
    private lateinit var requestQueue: RequestQueue
    private lateinit var booksAdapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.book_fragment,null)
        binding=BookFragmentBinding.bind(view)

        initViewsAndAdapter()
        initRequestQueue()
        getBooks()
        setupListeners()

        return view
    }

    private fun setupListeners(){
        booksAdapter.onBookClickListener=object :BooksAdapter.OnBookClickListener{
            override fun onImageClicked(booksAdapter: BooksAdapter, book: Book, position: Int) {
                addBookDetailsFragment(book)
            }
        }
    }

    private fun addBookDetailsFragment(book: Book){
        val bookDetailsFragment=BookDetailsFragment()

        val input=Bundle()
        input.putSerializable("book",book)
        bookDetailsFragment.arguments=input

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer,bookDetailsFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun getBooks(){
        val request=JsonObjectRequest(
            com.android.volley.Request.Method.GET,
            "https://api.itbook.store/1.0/new",
            null,
            { jsonObject->
                val gson=Gson()

                val booksArray=gson.fromJson<Array<Book>>(
                    jsonObject.getJSONArray("books").toString(),
                    Array<Book>::class.java
                )

                books.addAll(booksArray)
                booksAdapter.notifyDataSetChanged()
            },
            {error->
                Log.e("tag","error")
            }
        )

        requestQueue.add(request)
    }

    private fun initRequestQueue(){
        requestQueue=Volley.newRequestQueue(activity)
    }

    private fun initViewsAndAdapter(){
        books= ArrayList()

        binding.recyclerBooks.layoutManager=LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )

        booksAdapter=BooksAdapter(books)
        binding.recyclerBooks.adapter=booksAdapter
    }


}