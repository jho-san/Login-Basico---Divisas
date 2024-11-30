package com.example.practica_login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.practica_login.R
import com.example.practica_login.ui.HomeFragment

class LoginFragment : Fragment() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvForgotPassword: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        etUsername = view.findViewById(R.id.etUsername)
        etPassword = view.findViewById(R.id.etPassword)
        btnLogin = view.findViewById(R.id.btnLogin)
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword)

        tvForgotPassword.setOnClickListener {
            Toast.makeText(requireContext(), "Funcionalidad de recuperación de contraseña no implementada", Toast.LENGTH_SHORT).show()
        }

        btnLogin.setOnClickListener {
            login()
        }

        return view
    }

    private fun login() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString().trim()

        when {
            username.isEmpty() -> {
                Toast.makeText(requireContext(), "El Usuario es requerido", Toast.LENGTH_SHORT).show()
            }
            password.isEmpty() -> {
                Toast.makeText(requireContext(), "La Contraseña es requerida", Toast.LENGTH_SHORT).show()
            }
            username == "admin" && password == "admin" -> {
                Toast.makeText(requireContext(), "Ingreso Correctamente", Toast.LENGTH_SHORT).show()

                // Crear un Bundle para pasar el nombre de usuario al HomeFragment
                val bundle = Bundle()
                bundle.putString("username", username)

                // Crear una instancia del HomeFragment y pasarle el Bundle
                val homeFragment = HomeFragment()
                homeFragment.arguments = bundle

                // Navegar al HomeFragment
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, homeFragment)
                    .addToBackStack(null)
                    .commit()
            }
            else -> {
                Toast.makeText(requireContext(), "Credenciales no Válidas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
