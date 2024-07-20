package com.example.currencyconverter.features.history.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.currencyconverter.core.utils.getCurrentDate
import com.example.currencyconverter.core.utils.getDateFourDaysBefore
import com.example.currencyconverter.databinding.FragmentHistoryBinding
import com.example.currencyconverter.features.history.domain.entity.param.HistoricalDataParam
import com.example.currencyconverter.features.history.presentation.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment: Fragment() {
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val args: HistoryFragmentArgs by navArgs()
    private val viewModel: HistoryViewModel by viewModels()
    val historyAdapter = HistoricalDataAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerViewHistory.adapter = historyAdapter
        val fromCurrency = args.fromCurrency
        val toCurrency = args.toCurrency
        viewModel.fetchHistoricalData(
            historicalDataParam = HistoricalDataParam(
                start_date = getDateFourDaysBefore(),
                end_date = getCurrentDate(),
                base = fromCurrency,
                symbols = toCurrency
            )
        )

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}