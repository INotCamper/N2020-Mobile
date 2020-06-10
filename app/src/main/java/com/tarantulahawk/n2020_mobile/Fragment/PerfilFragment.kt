package com.tarantulahawk.n2020_mobile.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tarantulahawk.n2020_mobile.R
import kotlinx.android.synthetic.main.fragment_perfil.*

class PerfilFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }

    override fun onResume() {
        super.onResume()

        loadSavedData()

        etName.setOnFocusChangeListener { v, hasFocus -> saveThisData("Profile", "name", etName.text.toString())}
        etDate.setOnFocusChangeListener { v, hasFocus -> saveThisData("Profile", "date", etDate.text.toString())}
        etEmail.setOnFocusChangeListener { v, hasFocus -> saveThisData("Profile", "email", etEmail.text.toString())}
        etAddress.setOnFocusChangeListener { v, hasFocus -> saveThisData("Profile", "address", etAddress.text.toString())}
    }

    private fun loadSavedData(){
        if(getThisData("Profile", "name") != "") etName.setText(getThisData("Profile", "name")) else etName.setHint(R.string.profile_name_hint)
        if(getThisData("Profile", "date") != "") etDate.setText(getThisData("Profile", "date")) else etDate.setHint(R.string.date_of_birth_hint)
        if(getThisData("Profile", "email") != "") etEmail.setText(getThisData("Profile", "email")) else etEmail.setHint(R.string.email_hint)
        if(getThisData("Profile", "address") != "") etAddress.setText(getThisData("Profile", "address")) else etAddress.setHint(R.string.address_hint)
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
        return sharedPref?.getString(key, "")
    }
}