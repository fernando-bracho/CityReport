package com.example.cityreport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cityreport.databinding.FragmentListaIncidenciasBinding

/**
 * Pantalla "Home": muestra la lista de incidencias reportadas.
 * Desde aquí se puede:
 *  - Tocar el botón "+" para ir a Crear Incidencia.
 *  - Tocar una incidencia de la lista para ver su Detalle.
 */
class ListaIncidenciasFragment : Fragment() {

    // Configuración de View Binding recomendada para Fragments
    private var _binding: FragmentListaIncidenciasBinding? = null
    private val binding get() = _binding!!

    // ViewModel compartido con CrearIncidenciaFragment y DetalleIncidenciaFragment
    private val viewModel: IncidenciasViewModel by activityViewModels()

    private lateinit var adaptador: IncidenciaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaIncidenciasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Configurar el adaptador con un click listener que navega al Detalle
        adaptador = IncidenciaAdapter(emptyList()) { incidenciaSeleccionada ->
            findNavController().navigate(
                R.id.action_listaIncidenciasFragment_to_detalleIncidenciaFragment,
                bundleOf("incidenciaId" to incidenciaSeleccionada.id)
            )
        }

        binding.rvIncidencias.layoutManager = LinearLayoutManager(requireContext())
        binding.rvIncidencias.adapter = adaptador

        // 2. Observar el ViewModel: cada vez que cambie la lista (por ejemplo al
        // crear una incidencia nueva), se refresca automáticamente el RecyclerView
        viewModel.incidencias.observe(viewLifecycleOwner) { lista ->
            adaptador.actualizarLista(lista)
        }

        // 3. Botón flotante para ir a la pantalla de Crear Incidencia
        binding.fabAgregar.setOnClickListener {
            findNavController().navigate(R.id.action_listaIncidenciasFragment_to_crearIncidenciaFragment)
        }
    }

    // Es importante limpiar el binding cuando el fragmento se destruye
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}