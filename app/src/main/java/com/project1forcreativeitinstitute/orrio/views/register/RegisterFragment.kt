package com.project1forcreativeitinstitute.orrio.views.register


import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.project1forcreativeitinstitute.orrio.R
import com.project1forcreativeitinstitute.orrio.base.BaseFragment
import com.project1forcreativeitinstitute.orrio.core.DataState
import com.project1forcreativeitinstitute.orrio.data.models.UserRegistration
import com.project1forcreativeitinstitute.orrio.databinding.FragmentRegisterBinding
import com.project1forcreativeitinstitute.orrio.isEmpty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegistrationViewModel by viewModels ()

    override fun setListener() {

        with(binding) {

            btnRegister.setOnClickListener {
                etName.isEmpty()
                etEmail.isEmpty()
                etPassword.isEmpty()
                if (!etName.isEmpty() && !etEmail.isEmpty() && !etPassword.isEmpty()) {
                    Toast.makeText(context, "All Input done....", Toast.LENGTH_LONG).show()

                    val user = UserRegistration(
                  etName.text.toString(),
                  etEmail.text.toString(),
                  etPassword.text.toString(),
                  "Seller",
                  "")
                viewModel.userRegistration(user)

                }

            }
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
    }

    override fun allObserver() {
        registrationResponse()
    }





    private fun registrationResponse() {

        viewModel.registrationResponse.observe(viewLifecycleOwner){
            when(it) {
                is DataState.Error ->{
                    loading.dismiss()
                    Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading->{
                    loading.show()
                    Toast.makeText(context,"Loading....", Toast.LENGTH_SHORT).show()
                }
                is DataState.Success -> {
                    loading.dismiss()
                    Toast.makeText(context,"created User : ${it.data}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}