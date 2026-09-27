package com.example.cityreport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cityreport.databinding.FragmentDetalleIncidenciaBinding

/**
 * Pantalla de detalle: recibe el id de la incidencia seleccionada en Home
 * (vía argumento de navegación "incidenciaId"), la busca en el ViewModel
 * compartido y muestra toda su información.
 */
class DetalleIncidenciaFragment : Fragment() {

    private var _binding: FragmentDetalleIncidenciaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: IncidenciasViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleIncidenciaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnVolverDetalle.setOnClickListener {
            findNavController().popBackStack()
        }

        val incidenciaId = arguments?.getString("incidenciaId")
        val incidencia = incidenciaId?.let { viewModel.obtenerPorId(it) }

        if (incidencia == null) {
            // Caso borde: no se encontró la incidencia (no debería pasar en flujo normal)
            binding.tvDetalleNoEncontrado.visibility = View.VISIBLE
            binding.tvDetalleTitulo.visibility = View.GONE
            binding.tvDetalleEstado.visibility = View.GONE
            binding.tvDetalleFecha.visibility = View.GONE
            binding.tvDetalleDescripcionLabel.visibility = View.GONE
            binding.tvDetalleDescripcion.visibility = View.GONE
            return
        }

        binding.tvDetalleTitulo.text = incidencia.titulo
        binding.tvDetalleEstado.text = incidencia.estado.uppercase()
        binding.tvDetalleFecha.text = incidencia.fecha
        binding.tvDetalleDescripcion.text = incidencia.descripcion
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}