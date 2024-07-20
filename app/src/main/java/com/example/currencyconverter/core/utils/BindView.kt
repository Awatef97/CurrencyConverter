package com.example.currencyconverter.core.utils

import android.R
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
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
@BindingAdapter("onItemSelected")
fun setOnItemSelectedListener(spinner: Spinner, liveData: MutableLiveData<Int>?) {
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            liveData?.value = position
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}
@BindingAdapter("app:errorMsg")
fun setErrorMsg(view: TextView, errorMsg: String?) {
    view.visibility = if (errorMsg.isNullOrEmpty()) View.GONE else {
        view.text = errorMsg
        View.VISIBLE
    }
}