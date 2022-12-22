package com.jmanrique.loldataproject.app.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.jmanrique.loldataproject.app.ui.common.fragments.LoadingFragment

abstract class BaseFragment<DB>: LoadingFragment() where DB: ViewDataBinding {

    lateinit var binding: DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateBinding(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        bindViewToModel()
        val fragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransaction.add(LoadingFragment(), "Loading")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        return binding.root
    }

    abstract fun inflateBinding(layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean): DB

    open fun bindViewToModel() {
        /**
         * Bind view components to model provider (ViewModel itself / LiveData) via binding (view is not created)
         */
    }

    open fun showLoading(msg: String?){
        //TODO Adapt loading view to show a message
        showLoadingView()
    }

    open fun hideLoading(){
        hideLoadingView()
    }



}