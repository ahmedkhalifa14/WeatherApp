package com.example.weatherapplication.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication.R
import com.example.weatherapplication.data.local.DataStoreManager
import com.example.weatherapplication.databinding.FragmentSelectCityBinding
import com.example.weatherapplication.utils.Constants.capitalCities
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SelectCityFragment : Fragment() {

    @Inject
    lateinit var dataStoreManager: DataStoreManager
    private var _binding: FragmentSelectCityBinding? = null
    private val binding get() = _binding
    var selectedLocation: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelectCityBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSpinnerAdapter()
        spinnerSelectedItem()
        handleSelectBtn()
    }

    private fun handleSelectBtn() {
        binding!!.btnSelect.setOnClickListener {
            saveUserLocation()
            findNavController().navigate(R.id.action_selectCityFragment_to_homeFragment)
        }
    }
    private fun saveUserLocation() {
        lifecycleScope.launch {
            dataStoreManager.setUserLocation(selectedLocation!!)
        }
    }

    private fun spinnerSelectedItem() {
        binding?.locationSpinner?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedLocation = capitalCities[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    selectedLocation = capitalCities[parent?.adapter?.getItem(0) as Int]
                }
            }
    }

    private fun setUpSpinnerAdapter() {
        val adapter = object : ArrayAdapter<String>(
            requireContext(),
            R.layout.spinner_item,
            R.id.location,
            capitalCities
        ) {
            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                return super.getDropDownView(position, convertView, parent)
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return super.getView(position, convertView, parent)
            }
        }
        binding?.locationSpinner?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}