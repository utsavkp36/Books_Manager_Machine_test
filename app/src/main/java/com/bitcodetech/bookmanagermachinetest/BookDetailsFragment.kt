package com.bitcodetech.bookmanagermachinetest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitcodetech.bookmanagermachinetest.databinding.BookDetailsFragmentBinding
import com.squareup.picasso.Picasso

class BookDetailsFragment:Fragment() {

    private lateinit var binding:BookDetailsFragmentBinding
    private lateinit var book:Book

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view=inflater.inflate(R.layout.book_details_fragment,null)
        binding= BookDetailsFragmentBinding.bind(view)

        initData()

        return view
    }

    private fun initData(){
        if(arguments!=null && requireArguments().containsKey("book")){
            book=requireArguments().getSerializable("book")as Book

            binding.book=book

            Picasso.get()
                .load(book.image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(binding.imgBook)
        }
    }
}