package com.example.weather.weather.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.weather.R
import com.example.weather.databinding.FragmentContentBinding
import com.example.weather.model.presentation.Weather
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentFragment : Fragment(R.layout.fragment_content) {

    private lateinit var binding: FragmentContentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentContentBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        val args = arguments?.getParcelable<Weather>(KEY)!!
        binding.cityTitle.text = args.city
        binding.tempView.text = args.temp
        binding.weatherImage.setImageResource(args.image)
        binding.description.text = args.description
        binding.humidityView.text = args.humidity
        binding.windSpeedView.text = args.windSpeed
        binding.pressureView.text = args.pressure

        return binding.root
    }

    companion object {
        const val KEY = "key"
    }
}
