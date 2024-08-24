package com.paymob.movies.modules.common_views.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.paymob.movies.R
import com.paymob.movies.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private var navController: NavController? = null
    val mBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE
        )
        setContentView(mBinding.root)
        setupNavController()
    }

    override fun onPause() {
        window.addFlags(
            WindowManager.LayoutParams.FLAG_SECURE
        )
        super.onPause()
    }
    override fun onResume() {
        super.onResume()
        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        navController?.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?,
    ) {
        if (destination.id == R.id.moviesFragment) {
            mBinding.clToolbar.visibility = View.GONE
        } else {
            mBinding.clToolbar.visibility = View.VISIBLE
        }
    }

    fun setToolbarTitle(title: String?) {
        mBinding.tvTitle.text = title
    }

    fun showBackBtn(show: Boolean?) {
        if (show == true) {
            mBinding.btnBack.visibility = View.VISIBLE
        } else {
            mBinding.btnBack.visibility = View.INVISIBLE
        }
    }

    fun showToolbar(show: Boolean?) {
        if (show == true) {
            mBinding.clToolbar.visibility = View.VISIBLE
        } else {
            mBinding.clToolbar.visibility = View.INVISIBLE
        }
    }

    fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showProgress(show: Boolean?) {
        if (show == true)
            mBinding.progressBar.visibility = View.VISIBLE
        else
            mBinding.progressBar.visibility = View.GONE
    }
}
