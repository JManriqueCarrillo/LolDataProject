package com.jmanrique.loldataproject.app.ui.common.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.jmanrique.loldataproject.R
import com.jmanrique.loldataproject.utils.extensions.gone
import com.jmanrique.loldataproject.utils.extensions.visible

class LoadingFragment : Fragment() {

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.rootView = inflater.inflate(R.layout.fragment_loading, container, false)
        return this.rootView
    }

    fun showLoadingView() {
        this.rootView.visible(true)
    }

    fun hideLoadingView() {
//        this.rootView.gone(true)
    }

}