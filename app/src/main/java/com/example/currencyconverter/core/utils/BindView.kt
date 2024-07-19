package com.example.currencyconverter.core.utils

import android.R
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@BindingAdapter("entries")
fun setSpinnerEntries(spinner: Spinner, entries: MutableLiveData<List<String>>?) {
    entries?.value?.let {
        val adapter = ArrayAdapter(spinner.context, R.layout.simple_spinner_item, it)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}

@BindingAdapter("selectedItemPosition")
fun setSelectedItemPosition(spinner: Spinner, position: LiveData<Int>?) {
    position?.value?.let {
        spinner.setSelection(it, false)
    }
}