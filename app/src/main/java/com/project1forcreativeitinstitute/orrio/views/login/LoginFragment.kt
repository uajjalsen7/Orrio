package com.project1forcreativeitinstitute.orrio.views.login

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.project1forcreativeitinstitute.orrio.R
import com.project1forcreativeitinstitute.orrio.base.BaseFragment
import com.project1forcreativeitinstitute.orrio.core.DataState
import com.project1forcreativeitinstitute.orrio.data.models.UserLogIn
import com.project1forcreativeitinstitute.orrio.databinding.FragmentLoginBinding
import com.project1forcreativeitinstitute.orrio.views.dashboard.seller.SellerDashboard
import com.project1forcreativeitinstitute.orrio.views.isEmpty
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel: LoginViewModel by viewModels()

    override fun setListener() {
        with(binding){
            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            btnLogin.setOnClickListener {
                etEmail.isEmpty()
                etPassword.isEmpty()
                if (!etEmail.isEmpty() && !etPassword.isEmpty()){
//                    Toast.makeText(context, "All input done...", Toast.LENGTH_LONG).show()
                    val user = UserLogIn(etEmail.text.toString(), etPassword.text.toString())
                    viewModel.userLogin(user)
                    loading.show()
                }
            }
        }
    }

    override fun allObserver() {
        viewModel.loginResponse.observe(viewLifecycleOwner){

            when(it) {
                is DataState.Error -> {
                    loading.dismiss()
                    Toast.makeText(context, it.message,Toast.LENGTH_SHORT).show()

                }
                is DataState.Loading -> {
                    loading.show()
                    Toast.makeText(context, "Loading....",Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    loading.dismiss()
                    Toast.makeText(context, "Login Successful:${it.data}", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.dashBoardFragment)
                    Toast.makeText(context,"User logged in : ${it.data}",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(requireContext(), SellerDashboard::class.java))
                    requireActivity().finish()
                }
            }
        }
    }


}