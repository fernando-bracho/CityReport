package com.example.cityreport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cityreport.databinding.FragmentCrearIncidenciaBinding

/**
 * Pantalla para reportar una nueva incidencia.
 * Al guardar, la incidencia se agrega al ViewModel compartido (estado inicial
 * "Pendiente" y fecha de hoy) y se regresa automáticamente a Home, donde
 * aparecerá al principio de la lista.
 */
class CrearIncidenciaFragment : Fragment() {

    private var _binding: FragmentCrearIncidenciaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: IncidenciasViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCrearIncidenciaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVolverCrear.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnGuardar.setOnClickListener {
            val titulo = binding.etTitulo.text?.toString()?.trim().orEmpty()
            val descripcion = binding.etDescripcion.text?.toString()?.trim().orEmpty()

            if (titulo.isEmpty() || descripcion.isEmpty()) {
                // Validación simple: ambos campos son obligatorios
                binding.tvErrorValidacion.visibility = View.VISIBLE
                return@setOnClickListener
            }

            viewModel.agregarIncidencia(titulo, descripcion)

            // Regresa a Home; la lista ya se actualiza sola vía LiveData
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}