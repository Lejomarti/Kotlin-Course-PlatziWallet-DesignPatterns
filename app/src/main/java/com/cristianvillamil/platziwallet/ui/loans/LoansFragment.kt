package com.cristianvillamil.platziwallet.ui.loans

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristianvillamil.platziwallet.R
import com.cristianvillamil.platziwallet.databinding.FragmentLoansBinding

class LoansFragment : Fragment() {

    private val adapter = LoansAdapter()
    private lateinit var binding: FragmentLoansBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_loans, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        binding.circularProgress.setProgressWithAnimation(
            70f,
            1000,
            AccelerateDecelerateInterpolator(),
            300
        )
    }

    private fun initRecyclerView() {
        val loansList = listOf(
            Loan("", 50.toDouble(), 500.toDouble(), ""),
            Loan("", 50.toDouble(), 500.toDouble(), "")
        )
        binding.loansRecyclerView.adapter = adapter
        binding.loansRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter.setData(loansList)

    }
}