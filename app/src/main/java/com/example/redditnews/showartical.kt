package com.example.redditnews


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.redditnews.databinding.FragmentKotlinNewsBinding

class showartical : Fragment() {
    private lateinit var binding:FragmentKotlinNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKotlinNewsBinding.inflate(inflater, container, false)
        return binding.getRoot()
    }
}