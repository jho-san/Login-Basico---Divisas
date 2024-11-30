package com.example.practica_login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.practica_login.R

class HomeFragment : Fragment() {

    private lateinit var tvWelcome: TextView
    private lateinit var btnLogout: Button
    private lateinit var editTextAmount: EditText
    private lateinit var spinnerCurrencies: Spinner
    private lateinit var buttonConvert: Button
    private lateinit var textViewResult: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del HomeFragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Referenciar los elementos de la UI
        tvWelcome = view.findViewById(R.id.tvWelcome)
        btnLogout = view.findViewById(R.id.btnLogout)
        editTextAmount = view.findViewById(R.id.editTextAmount)
        spinnerCurrencies = view.findViewById(R.id.spinnerCurrencies)
        buttonConvert = view.findViewById(R.id.buttonConvert)
        textViewResult = view.findViewById(R.id.TVResult)

        // Obtener el nombre del usuario desde los argumentos
        val username = arguments?.getString("username") ?: "Usuario"

        // Mostrar el mensaje de bienvenida con el nombre de usuario
        tvWelcome.text = "Bienvenido"

        // Configurar la funcionalidad del botón "Cerrar Sesión"
        btnLogout.setOnClickListener {
            // Navegar de vuelta al LoginFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, LoginFragment())
                .commit()
        }

        // Configuración del Spinner con las divisas
        val currencies = resources.getStringArray(R.array.currencies_array)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCurrencies.adapter = adapter

        // Tipos de cambio simulados
        val exchangeRates = mapOf(
            "Dólares" to 0.27,
            "Euros" to 0.24,
            "Pesos Mexicanos" to 5.29
        )

        buttonConvert.setOnClickListener {
            val amountInSoles = editTextAmount.text.toString().toDoubleOrNull()

            if (amountInSoles == null || amountInSoles <= 0) {
                Toast.makeText(requireContext(), "Ingresar un monto válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedCurrency = spinnerCurrencies.selectedItem.toString()
            val exchangeRate = exchangeRates[selectedCurrency] ?: 0.0
            val convertedAmount = amountInSoles * exchangeRate

            textViewResult.text = "Resultado: %.2f %s".format(convertedAmount, selectedCurrency)
        }

        return view
    }
}
