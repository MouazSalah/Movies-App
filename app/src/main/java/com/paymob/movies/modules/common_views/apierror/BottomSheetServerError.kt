package com.paymob.movies.modules.common_views.apierror

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.paymob.movies.modules.common_views.base.BaseBottomSheet
import com.paymob.movies.databinding.BottomSheetServerErrorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetServerError : BaseBottomSheet<BottomSheetServerErrorBinding>()
{
    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?, b: Boolean) = BottomSheetServerErrorBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.isCancelable = true
    }

    override fun onFragmentReady() {

        mBinding.apply {

            btnOk.setOnClickListener {
                closeBottomSheet()
            }
        }
    }
}