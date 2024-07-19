package com.example.currencyconverter.features.home.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.currencyconverter.R
import com.example.currencyconverter.core.utils.InputFilterMinMax
import com.example.currencyconverter.databinding.FragmentCurrencyConversionBinding
import com.example.currencyconverter.features.home.presentation.viewmodel.CurrencyConversionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConversionFragment: Fragment() {
    private  var _binding: FragmentCurrencyConversionBinding? = null
    private val binding get() = _binding!!
    private val currencyConversionViewModel: CurrencyConversionViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_currency_conversion, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = currencyConversionViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.spinnerFromCurrency.prompt = "From"
        binding.spinnerToCurrency.prompt = "To"

        binding.etAmount.filters = arrayOf(InputFilterMinMax(1, Int.MAX_VALUE))
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
