package com.tarantulahawk.n2020_mobile.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tarantulahawk.n2020_mobile.R
import kotlinx.android.synthetic.main.fragment_card.*

class CardFragment : Fragment() {

    var i:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saveThisData("new", "quant", i.toString())
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
        i = getThisData("new", "quant")!!.toInt()
        when (i){
            0->{
                this.ivCard.setImageResource(R.drawable.complete_logo)
                this.tvCard.text = getText(R.string.news_complete)
            }
            1->{
                this.ivCard.setImageResource(R.drawable.gelman)
                this.tvCard.text = getText(R.string.news_gel)
            }
            2->{
                this.ivCard.setImageResource(R.drawable.sabaogirl)
                this.tvCard.text = getText(R.string.news_sab)
            }
            else -> {
                this.ivCard.setImageResource(R.drawable.logo)
                this.tvCard.text = getText(R.string.news_logo)
            }
        }
        i++
        saveThisData("new", "quant", i.toString())
    }

    private fun saveThisData(archName:String, key:String, dado:String){
        if (activity != null) {
            val sharedPrefEditor = this.activity?.getSharedPreferences(archName, Context.MODE_PRIVATE)?.edit()
            sharedPrefEditor?.putString(key, dado)
            sharedPrefEditor?.apply()
        }
    }

    private fun getThisData(archName:String, key:String): String? {
        val sharedPref = this.activity?.getSharedPreferences(archName, Context.MODE_PRIVATE)
        return sharedPref?.getString(key, "0")
    }
}