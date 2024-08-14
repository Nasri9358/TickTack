package com.bignerdranch.android.ticktack.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bignerdranch.android.ticktack.R
import com.bignerdranch.android.ticktack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var mainFragment = TaskFragment()
    private var favouriteTasksFragment = FavouriteTasksFragment()
    private var infoFragment = InfoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        setFragment(mainFragment)

        binding.navBottomMenu.selectedItemId = R.id.nav_tasks

        binding.navBottomMenu.setOnItemSelectedListener {
            binding.tvActivityTitle.text = it.title

            when(it.itemId) {
                R.id.nav_favourive -> {
                    setFragment(favouriteTasksFragment)
                    true
                }

                R.id.nav_tasks -> {
                    setFragment(mainFragment)
                    true
                }

                R.id.nav_settings -> {
                    setFragment(infoFragment)
                    true
                }

                else -> {
                    false
                }
            }
        }

    }

    private fun setFragment(fragment: Fragment) {
        binding.navBottomMenu.selectedItemId = fragment.id
        supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, fragment).commit()
    }
}