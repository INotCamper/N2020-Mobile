package com.tarantulahawk.n2020_mobile.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tarantulahawk.n2020_mobile.R
import kotlinx.android.synthetic.main.fragment_card.*
import kotlinx.android.synthetic.main.fragment_noticias.*

class CardFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    override fun onResume() {
        super.onResume()
        this.ivCard.setImageResource(R.drawable.default_card_image)
    }
}