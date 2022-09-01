package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentTomorrowBinding
import com.example.weatherapp.models.Resource
import com.example.weatherapp.models.WeatherResponse
import com.example.weatherapp.utils.DateUtils
import com.example.weatherapp.utils.WeatherUtil
import com.example.weatherapp.viewmodels.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.DecimalFormat

class TomorrowFragment : Fragment() {

    private val mainViewModel: MainViewModel by sharedViewModel()

    private var _binding: FragmentTomorrowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTomorrowBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.cityName.observe(viewLifecycleOwner) {
            // updating data in displayMsg
            if (it.isNotEmpty()){
                mainViewModel.fetchCurrentWeather(it)
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
                binding.layoutMain.visibility = View.GONE
                binding.layoutEmpty.visibility = View.GONE

            }
            Resource.Status.SUCCESS -> {
                binding.progressCircular.visibility = View.GONE
                binding.layoutMain.visibility = View.VISIBLE
                binding.layoutEmpty.visibility = View.GONE
                loadWeatherData(state.data)
            }
            Resource.Status.ERROR -> {
                binding.progressCircular.visibility = View.GONE
                binding.layoutMain.visibility = View.GONE
                binding.layoutEmpty.visibility = View.VISIBLE
                Toast.makeText(requireContext(), "Error: ${state.message}", Toast.LENGTH_LONG)
                    .show()
            }
            Resource.Status.EMPTY -> {
                binding.progressCircular.visibility = View.GONE
                binding.layoutMain.visibility = View.GONE
                binding.layoutEmpty.visibility = View.VISIBLE
            }
        }
    }

    private fun loadWeatherData(data: WeatherResponse?) {
        data?.let {
            binding.txtCity.text = data.location?.name
            binding.txtTemp.text = DecimalFormat("#").format(data.current?.tempC)
            binding.txtTempSymbol.text = "\u2103"
            binding.txtFeels.text = getString(R.string.feels_like).format("${DecimalFormat("#").format(data.current?.feelslikeC)}\u00B0")
            binding.txtCloud.text = "${data.current?.condition?.text}"

            val dateTime = DateUtils.formateDate(
                "${data.location?.localtime}",
                "yyyy-MM-dd HH:mm",
                "MMM dd , hh:mm aa"
            )
            binding.txtDateTime.text = dateTime

            val code = data.current?.condition?.code
            binding.imgWeather.setImageResource(WeatherUtil.getWeatherIcon(code!!))
        }
    }
}