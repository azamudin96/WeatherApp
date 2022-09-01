package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapter.WeatherAdapter
import com.example.weatherapp.databinding.FragmentListBinding
import com.example.weatherapp.models.Resource
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.utils.DateUtils
import com.example.weatherapp.utils.WeatherUtil
import com.example.weatherapp.viewmodels.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.DecimalFormat

class ListFragment : Fragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val weatherAdapter = WeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.adapter = weatherAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(activity);

        mainViewModel.cityName.observe(viewLifecycleOwner) {
            // updating data in displayMsg
            if (it.isNotEmpty()){
                mainViewModel.fetchForecastWeather(it,5)
            }
        }

        lifecycleScope.launch {
            mainViewModel.currentWeatherState.collect {
                handleCurrentWeatherDataState(it)
            }
        }
    }

    private fun handleCurrentWeatherDataState(state: Resource<WeatherResponse>) {
        when (state.status) {
            Resource.Status.LOADING -> {
                binding.progressCircular.visibility = View.VISIBLE
                binding.recyclerview.visibility = View.GONE
                binding.layoutEmpty.visibility = View.GONE
            }
            Resource.Status.SUCCESS -> {
                binding.progressCircular.visibility = View.GONE
                binding.recyclerview.visibility = View.VISIBLE
                binding.layoutEmpty.visibility = View.GONE
                loadWeatherData(state.data)
            }
            Resource.Status.ERROR -> {
                binding.progressCircular.visibility = View.GONE
                binding.recyclerview.visibility = View.GONE
                binding.layoutEmpty.visibility = View.VISIBLE
                Toast.makeText(requireContext(), "Error: ${state.message}", Toast.LENGTH_LONG)
                    .show()
            }
            Resource.Status.EMPTY -> {
                binding.progressCircular.visibility = View.GONE
                binding.recyclerview.visibility = View.GONE
                binding.layoutEmpty.visibility = View.VISIBLE
            }
        }
    }

    private fun loadWeatherData(data: WeatherResponse?) {
        data?.let {
            weatherAdapter.data = data
            weatherAdapter.arrayData.addAll(data.forecast!!.forecastday)
            weatherAdapter.notifyDataSetChanged()
        }
    }

}