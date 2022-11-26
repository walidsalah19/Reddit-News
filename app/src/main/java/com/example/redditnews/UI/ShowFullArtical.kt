package com.example.redditnews.UI


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.redditnews.databinding.FragmentKotlinNewsBinding

class ShowFullArtical : Fragment() {
    private lateinit var binding:FragmentKotlinNewsBinding
    private val args : ShowFullArticalArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentKotlinNewsBinding.inflate(inflater, container, false)
        binding.articalTitle.text=args.text
        binding.toolbar2.title=args.title
        val layoutParams = LinearLayout.LayoutParams(0, 0)
        if(args.url.equals("null"))
        {
            binding.articalImageview.layoutParams = layoutParams
        }
        else {
            Glide.with(requireContext()).load(args.url)
                .into(binding.articalImageview)
        }
        return binding.getRoot()
    }
}