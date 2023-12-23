package com.techjd.moviedetails.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.techjd.moviedetails.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var mainActivityMainBinding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    mainActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(mainActivityMainBinding.root)

    val navHostFragment =
      supportFragmentManager.findFragmentById(
        mainActivityMainBinding.navHostFragment.id
      ) as NavHostFragment

  }
}