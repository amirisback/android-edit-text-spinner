package com.frogobox.research.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.PopupWindow
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.frogobox.research.R
import com.frogobox.research.core.BaseBindActivity
import com.frogobox.research.databinding.ActivityMainBinding
import com.frogobox.research.databinding.LayoutPopupSpinnerBinding
import com.frogobox.research.ext.UnspecifiedTypeItem
import com.frogobox.research.ext.performUpdates
import com.frogobox.research.model.SpinnerItemType
import com.frogobox.research.ui.main.adapter.SpinnerMainListItemContent
import com.mikepenz.fastadapter.adapters.FastItemAdapter


class MainActivity : BaseBindActivity<ActivityMainBinding>() {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    private val adapter: FastItemAdapter<UnspecifiedTypeItem> = FastItemAdapter()

    private val viewModel: MainViewModel by viewModels()

    override fun initBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            // Call View Model Here
            Log.d(TAG, "View Model : ${viewModel::class.java.simpleName}")
            viewModel.getSpinnerData()
        }

    }

    override fun initView() {
        super.initView()
        binding.apply {

            etSearch.addTextChangedListener(object: TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                    btn.isEnabled = (etSearch.text.toString().isNotEmpty())
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    btn.isEnabled = (etSearch.text.toString().isNotEmpty())
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    btn.isEnabled = (etSearch.text.toString().isNotEmpty())
                }
            })

            btn.setOnClickListener {
                Log.d(TAG, "Button Clicked : ${etSearch.text.toString()}")
            }
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.apply {

            dataSpinnerString.observe(this@MainActivity) {
                Log.d(TAG, "dataSpinnerString : $it")
                binding.apply {
                    //setting the adapter data into the AutoCompleteTextView
                    etSearch.threshold = 1 //will start working from first character
                    etSearch.setAdapter(ArrayAdapter(this@MainActivity, R.layout.item_spinner, R.id.tv_text, it))
                }
            }

            dataSpinner.observe(this@MainActivity) {
                Log.d(TAG, "Data Spinner : $it")
                populateRvItem(it)

            }
        }
    }

    private fun populateRvItem(data: List<SpinnerItemType>) {
        val items = mutableListOf<UnspecifiedTypeItem>()
        data.forEach {
            when (it) {

                is SpinnerItemType.Loading -> {}

                is SpinnerItemType.Error -> {}

                is SpinnerItemType.Empty -> {}

                is SpinnerItemType.Content -> {
                    val item = SpinnerMainListItemContent(it) {

                    }
                    items.add(item)
                }

            }
        }
        adapter.performUpdates(items)
    }

}