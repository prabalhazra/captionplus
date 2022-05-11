package com.phono.captionstar.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phono.captionstar.databinding.FragmentOnBoardingBinding
import com.phono.captionstar.presentation.onboarding.screens.FirstScreenFragment
import com.phono.captionstar.presentation.onboarding.screens.SecondScreenFragment
import com.phono.captionstar.presentation.onboarding.screens.ThirdScreenFragment

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoardingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf(
            FirstScreenFragment(),
            SecondScreenFragment(),
            ThirdScreenFragment()
        )

        val adapter = OnboardingViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.onboadingViewPager.adapter = adapter
        binding.dotsIndicator.attachTo(binding.onboadingViewPager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}