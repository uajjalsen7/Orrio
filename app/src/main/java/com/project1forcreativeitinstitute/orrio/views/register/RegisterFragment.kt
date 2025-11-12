package com.project1forcreativeitinstitute.orrio.views.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.project1forcreativeitinstitute.orrio.R
import com.project1forcreativeitinstitute.orrio.databinding.FragmentLoginBinding
import com.project1forcreativeitinstitute.orrio.databinding.FragmentRegisterBinding
import com.project1forcreativeitinstitute.orrio.isEmpty

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        setListener()
        return binding.root
    }
    private fun setListener() {
        with(binding) {
            btnLogin.setOnClickListener {
                etName.isEmpty()
                etEmail.isEmpty()
                etPassword.isEmpty()
                if (!etName.isEmpty() && !etEmail.isEmpty() && !etPassword.isEmpty()) {
                    Toast.makeText(context, "All Input done....", Toast.LENGTH_LONG).show()
                }

            }
        }
    }
}