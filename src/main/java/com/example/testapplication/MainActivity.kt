package com.example.testapplication

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.example.testapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        val fragmentHome = HomeFragment()
        val fragmentSecond = SecondFragment()

        supportFragmentManager.beginTransaction().apply { replaceFragment(fragmentHome)}
        binding.btnFragment1.setOnClickListener { replaceFragment(fragmentHome); }
        binding.btnFragment2.setOnClickListener { replaceFragment(fragmentSecond); }
        setContentView(binding.root)
    }

    private fun replaceFragment(fragmentId: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment, fragmentId)
            commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null;
    }
}