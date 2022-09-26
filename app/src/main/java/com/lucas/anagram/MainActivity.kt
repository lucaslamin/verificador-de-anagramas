package com.lucas.anagram

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lucas.anagram.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupView()
        registerObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun setupView() {
        with(binding) {
            btnCompare.setOnClickListener {
                hideKeyboard()
                viewModel.checkIfIsAnagram(etFirstString.text.toString(), etSecondString.text.toString())
            }
        }
    }
    private fun registerObservers() {
        viewModel.resultLiveData.observe(this) { isAnagram ->
            if (isAnagram) showSuccessToast() else showErrorToast()
        }
    }

    private fun showSuccessToast() {
        Toast.makeText(
            this,
            "Sucesso! As palavras inseridas são anagramas!",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showErrorToast() {
        Toast.makeText(
            this,
            "Erro! As palavras inseridas não são anagramas",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun hideKeyboard() {
        val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // check if no view has focus:
        val view = (this as Activity).currentFocus ?: return

        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
 }