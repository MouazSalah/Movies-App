package com.paymob.movies.extesnion

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.paymob.movies.core.HashMapParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber

inline fun <reified T : AppCompatActivity> Fragment.castToActivity(
    callback: (T?) -> Unit,
): T? {
    return if (requireActivity() is T) {
        callback(requireActivity() as T)
        requireActivity() as T
    } else {
        callback(null)
        null
    }
}

fun CoroutineScope.delayWithAction(
    delayMillis: Long,
    action: () -> Unit
) {
    launch {
        delay(delayMillis)
        action()
    }
}

//check if network is connected
fun isNetworkAvailable(context: Context?): Boolean {
    if (context == null) return false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
    }
    return false
}

fun Any?.showLogMessage(tag: String? = null) = Timber.tag("Banque_Misr $tag").e(this.toString())

fun HashMapParams.toHashMapParams(): HashMap<String, String?>? {
    if (this == null) return null
    val params by lazy<HashMap<String, String?>> { HashMap() }
    try {
        JSONObject(Gson().toJson(dataClass())).let {
            if (it.names() != null)
                for (i in 0 until it.names()!!.length()) {
                    params[it.names()!!.getString(i)] =
                        it[it.names()!!.getString(i)].toString() + ""
                }
        }
    } catch (e: Exception) {
        e.message.showLogMessage("Hashmap")
        print(e)
    }
    return if (params.isEmpty()) null else params
}