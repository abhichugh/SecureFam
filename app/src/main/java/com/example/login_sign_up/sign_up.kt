package com.example.login_sign_up

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.Navigation
import com.example.login_sign_up.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.reflect.Array.get
import java.nio.file.Paths.get
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class sign_up : Fragment() {
    var fAuth :FirebaseAuth? = null
    var pbar : ProgressBar? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentSignUpBinding.inflate(inflater,container,false)
        val cal: Calendar = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.selectdate.text=sdf.format(cal.time)
        }

        binding.selectdate.setOnClickListener {
            context?.let { it1 ->
                DatePickerDialog(it1, dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        }

        if(fAuth?.currentUser != null){
            Navigation.findNavController(binding.root).navigate(R.id.action_sign_up_to_startPage)
        }
        fAuth = FirebaseAuth.getInstance()
        binding.signupbutton2.setOnClickListener() {
            var fview = it
            val email: String? = binding.putemail.text.toString().trim()
            val pass: String? = binding.pass.text.toString().trim()
            val cpass: String? = binding.confirmpass.text.toString().trim()

            if(TextUtils.isEmpty(email)){
                binding.putemail.setError("Email field required")
            }
            else if(TextUtils.isEmpty(pass)){
                binding.pass.setError("Password field required")
            }
            else if(TextUtils.isEmpty(cpass)){
                binding.confirmpass.setError("Field required")
            }
            else if (pass != null) {
                if(pass.length < 7){
                    binding.pass.setError("Password length must be more then 6 characters")
                }
                else if(pass != cpass){
                    binding.confirmpass.setError("Passwords not match")
                }
                else{
                    binding.progressBarsignup.visibility = View.VISIBLE
                    if (email != null) {
                        if (pass != null) {
                            fAuth!!.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                                if(it.isSuccessful){
                                    Toast.makeText(context, "User Creation Successful", Toast.LENGTH_SHORT).show()
                                    Navigation.findNavController(fview).navigate(R.id.action_sign_up_to_startPage)
                                } else{
                                    Toast.makeText(context, "Error !!" + it.exception, Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
        }
        return binding.root
    }
}