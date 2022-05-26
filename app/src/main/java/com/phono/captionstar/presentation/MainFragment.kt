package com.phono.captionstar.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.phono.captionstar.R
import com.phono.captionstar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
//        (activity as AppCompatActivity).setSupportActionBar(binding.toolBar)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val configuration = AppBarConfiguration(
//            setOf(
//                R.id.homeFragment,
//                R.id.feedFragment,
//                R.id.profileFragment
//            )
//        )

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
//        (activity as AppCompatActivity).setupActionBarWithNavController(
//            navController,
//            configuration
//        )
        binding.bottomNav.setupWithNavController(navController)

        hideToolBar()
    }

    private fun hideToolBar() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id) {
                R.id.detailsFragment -> {
//                    binding.appBarLayout.visibility = View.GONE
//                    binding.toolBar.visibility = View.GONE
                    binding.bottomNav.visibility = View.GONE
                }
                R.id.feedDetailsFragment -> {
                    binding.bottomNav.visibility = View.GONE
                }
                else -> {
//                    binding.appBarLayout.visibility = View.VISIBLE
//                    binding.toolBar.visibility = View.VISIBLE
                    binding.bottomNav.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}