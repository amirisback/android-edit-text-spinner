package com.frogobox.research.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.frogobox.research.model.SpinnerItemType

/**
 * Created by Faisal Amir on 24/10/22
 * -----------------------------------------
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) Frogobox ID / amirisback
 * All rights reserved
 */

class MainViewModel : ViewModel() {

    private var _dataSpinner = MutableLiveData<List<SpinnerItemType>>()
    var dataSpinner: LiveData<List<SpinnerItemType>> = _dataSpinner

    private var _dataSpinnerString = MutableLiveData<List<String>>()
    var dataSpinnerString: LiveData<List<String>> = _dataSpinnerString


    fun getSpinnerData() {
        val data = mutableListOf<String>()
        for (i in 1..10) {
            data.add("Data $i")
        }
        _dataSpinnerString.postValue(data)
        populateSuccess(data)
    }

    private fun populateSuccess(data: List<String>) {
        val listExploreType = mutableListOf<SpinnerItemType>()
        data.forEach {
            listExploreType.add(SpinnerItemType.Content(it))
        }
        _dataSpinner.postValue(listExploreType)
    }

}