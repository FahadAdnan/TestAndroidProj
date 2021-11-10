package com.example.testapplication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testapplication.databinding.HomeFragmentBinding

class HomeFragment : Fragment(R.layout.home_fragment) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)


        binding.goToOtherFragment.setOnClickListener {
            val secondFragment: Fragment = SecondFragment.newInstance(
                SecondFragment.secondFragmentBundle(
                    name = binding.editTextName.text.toString(),
                    age = binding.editTextAge.text.toString().toIntOrNull() ?: 0,
                    favColor = binding.editTextFavColor.text.toString()
                )
            )
            replaceFragment(secondFragment)
        }
        return binding.root
    }

    private fun replaceFragment(fragmentId: Fragment){
        fragmentManager?.let{
            it.beginTransaction().apply {
                replace(R.id.main_fragment, fragmentId)
                addToBackStack("Home Fragment")
                commit()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}