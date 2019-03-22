package com.massita.faire.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.massita.faire.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_brand.*

class BrandFragment : Fragment() {
    private val args: BrandFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_brand, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val brand = args.brand

        brandTitle.text = brand?.name
        brandDescription.text = brand?.description

        brand?.squaredImage?.let {
            Picasso.get().load(it.url)
                .into(brandImage)
        }
    }


}
