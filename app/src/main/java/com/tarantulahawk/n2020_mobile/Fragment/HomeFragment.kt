package com.tarantulahawk.n2020_mobile.Fragment

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tarantulahawk.n2020_mobile.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    val enderecoApp:String = "com.reddit.frontpage"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()
        ivGame.setOnClickListener {
            startApplication(enderecoApp)
        }
    }



    fun startApplication(packageName:String) {
        try
        {
            val intent = Intent("android.intent.action.MAIN")
            intent.addCategory("android.intent.category.LAUNCHER")
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            val resolveInfoList = getActivity()?.getPackageManager()?.queryIntentActivities(intent, 0)
            if (resolveInfoList != null) {
                for (info in resolveInfoList)
                    if (info.activityInfo.packageName.equals(packageName)) {
                        launchComponent(info.activityInfo.packageName, info.activityInfo.name)
                        return
                    }
            }
            // No match, so application is not installed
            showInMarket(packageName)
        }
        catch (e:Exception) {
            showInMarket(packageName)
        }
    }
    private fun launchComponent(packageName:String, name:String) {
        val intent = Intent("android.intent.action.MAIN")
        intent.addCategory("android.intent.category.LAUNCHER")
        intent.setComponent(ComponentName(packageName, name))
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    private fun showInMarket(packageName:String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName))
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

}