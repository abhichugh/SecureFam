package com.example.login_sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.login_sign_up.databinding.FragmentStartPageBinding
import com.google.firebase.auth.FirebaseAuth

class StartPage : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentStartPageBinding.inflate(inflater, container, false)
        binding.logoutbutton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Navigation.findNavController(binding.root).navigate(R.id.login)
        }
        return binding.root
    }
}