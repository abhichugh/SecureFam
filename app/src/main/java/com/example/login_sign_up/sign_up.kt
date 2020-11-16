package com.example.login_sign_up

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.login_sign_up.databinding.FragmentSignUpBinding
import java.lang.reflect.Array.get
import java.nio.file.Paths.get
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class sign_up : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentSignUpBinding.inflate(inflater,container,false)
        val cal: Calendar = Calendar.getInstance()
        val year : Int = Calendar.YEAR
        val month : Int = Calendar.MONTH
        val day :Int = Calendar.DAY_OF_MONTH

        binding.selectdate.setOnClickListener {
            var dpd = context?.let { it1 -> DatePickerDialog(it1,null ,year,month,day) }
        }
        return binding.root
    }
}