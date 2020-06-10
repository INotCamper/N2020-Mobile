package com.tarantulahawk.n2020_mobile.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.tarantulahawk.n2020_mobile.R
import kotlinx.android.synthetic.main.fragment_config.*

class ConfigFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_config, container, false)
    }

    override fun onResume() {
        super.onResume()

        loadSavedData()

        swNot.setOnClickListener { saveThisData("Config", "notf", swNot.isChecked.toString()) }
        swRebLog.setOnClickListener { saveThisData("Config", "rebme", swRebLog.isChecked.toString()) }
        sbVolume.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                // cada vez que o valor muda
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // começou a tocar
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // terminou de tocar
                saveThisData("Config", "vol", sbVolume.progress.toString())
            }
        })
        etPassword.setOnFocusChangeListener { v, hasFocus -> saveThisData("Config", "pass", etPassword.text.toString()) }
    }

    private fun loadSavedData(){
        swNot.isChecked = (getThisData("Config", "notf"))!!.toBoolean()
        swRebLog.isChecked = (getThisData("Config", "rebme"))!!.toBoolean()
        sbVolume.progress = (getThisData("Config", "vol"))!!.toInt()
        if(getThisData("Config", "pass") != "") etPassword.setText(getThisData("Config", "pass")) else etPassword.setHint(R.string.password_hint)
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
        return sharedPref?.getString(key, if(key == "notf") "true" else if(key == "rebme") "false" else if(key =="vol") "100" else "")
    }
}