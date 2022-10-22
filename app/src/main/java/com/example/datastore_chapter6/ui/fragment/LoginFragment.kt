package com.example.datastore_chapter6.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.datastore_chapter6.R
import com.example.datastore_chapter6.data.DataUserManager
import com.example.datastore_chapter6.databinding.FragmentLoginBinding
import com.example.datastore_chapter6.ui.UserViewModel
import com.example.datastore_chapter6.ui.ViewModelFactory

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private lateinit var pref: DataUserManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var username = ""
        var password = ""

        binding.login.setOnClickListener{
            viewModel.getDataUsername().observe(viewLifecycleOwner){
                username = it.toString()
                println(username)
            }
            viewModel.getDataPassword().observe(viewLifecycleOwner){
                password = it.toString()
                println(password)
            }
            val _username = binding.username.text.toString()
            val _password = binding.password.text.toString()

            if(username == _username && password == _password){
                viewModel.setIsLogin(true)
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else {
                Toast.makeText(
                    requireContext(),
                    "The username or password is incorrect!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getIsLogin().observe(viewLifecycleOwner){
            Handler(Looper.myLooper()!!).postDelayed({
                if(it == true)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            },1000)
        }
    }
}