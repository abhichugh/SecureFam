package com.example.login_sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.login_sign_up.R.id.signup_button
import com.example.login_sign_up.databinding.FragmentLoginBinding

class login : Fragment() {
    var sb :Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.signupButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_login_to_sign_up)
        }
        return binding.root
    }
}