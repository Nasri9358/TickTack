package com.bignerdranch.android.ticktack.presentation.view

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bignerdranch.android.ticktack.R
import com.bignerdranch.android.ticktack.databinding.ActivityMainBinding
import com.bignerdranch.android.ticktack.databinding.DialogCreateItemBinding
import com.bignerdranch.android.ticktack.presentation.view.taskGroupView.CreateTaskGroupActivity
import com.bignerdranch.android.ticktack.presentation.view.taskView.CreateTaskActivity


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

        /*binding.btnCreateItem.setOnClickListener {
            showCreateItemDialog()
        }*/
    }

    private fun setFragment(fragment: Fragment) {
        binding.navBottomMenu.selectedItemId = fragment.id
        supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, fragment).commit()
    }

    /*private fun showCreateItemDialog() {
       val dialogBinding = DialogCreateItemBinding.inflate(layoutInflater)

       createItemDialog.setContentView(dialogBinding.root)
       createItemDialog.setCancelable(true)
       createItemDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

       dialogBinding.btnCreateTask.setOnClickListener {
           startActivity(Intent(this, CreateTaskActivity::class.java))
           createItemDialog.dismiss()
       }

       dialogBinding.btnCreateTaskGroup.setOnClickListener {
           startActivity(Intent(this, CreateTaskGroupActivity::class.java))
           createItemDialog.dismiss()
       }

       createItemDialog.show()
   } */
}