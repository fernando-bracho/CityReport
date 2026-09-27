package com.example.cityreport

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cityreport.databinding.FragmentBienvenidaBinding

/**
 * Pantalla principal de la app (antes "Hello CityReport").
 * Da la bienvenida al usuario y permite pasar a la lista de incidencias.
 */
class BienvenidaFragment : Fragment() {

    private var _binding: FragmentBienvenidaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBienvenidaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mensaje de depuración heredado de Hello CityReport
        Log.d("DEBUG_CITYREPORT", "¡La aplicación Hello CityReport se ha iniciado correctamente!")

        // Navega hacia la lista de incidencias al presionar el botón
        binding.btnVerIncidencias.setOnClickListener {
            findNavController().navigate(
                R.id.action_bienvenidaFragment_to_listaIncidenciasFragment
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
