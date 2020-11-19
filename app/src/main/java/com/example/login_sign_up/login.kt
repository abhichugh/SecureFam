package com.example.login_sign_up

import android.os.Bundle
import android.text.TextUtils
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class login : Fragment() {
    var fAuth: FirebaseAuth? = null
    var sb :Button? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.signupButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_login_to_sign_up)
        }
        fAuth= FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener {
            val email: String? = binding.Username.text.toString().trim()
            val pass: String? = binding.Password.text.toString().trim()
            if(TextUtils.isEmpty(email)){
                binding.Username.setError("Email field required")
            }
            else if(TextUtils.isEmpty(pass)){
                binding.Password.setError("Password field required")
            }
            else{
                binding.progressBarlogin.visibility = View.VISIBLE
                if (email != null) {
                    if (pass != null) {
                        fAuth!!.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                                Navigation.findNavController(binding.root).navigate(R.id.action_login_to_startPage)
                            } else{
                                Toast.makeText(context, "Error !!" + it.exception, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
        return binding.root
    }
}