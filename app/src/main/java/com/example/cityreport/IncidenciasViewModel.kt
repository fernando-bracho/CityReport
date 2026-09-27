package com.example.cityreport

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * ViewModel compartido entre HomeFragment (lista), CrearIncidenciaFragment
 * y DetalleIncidenciaFragment. Al estar scopeado a la Activity (activityViewModels),
 * las tres pantallas ven siempre los mismos datos.
 */
class IncidenciasViewModel : ViewModel() {

    private val _incidencias = MutableLiveData<List<Incidencia>>(listaInicial())
    val incidencias: LiveData<List<Incidencia>> get() = _incidencias

    private fun listaInicial(): List<Incidencia> = listOf(
        Incidencia("1", "Semáforo descompuesto", "Falla en semáforo cerca de Metrobús Línea 2", "20/09/2026", "Pendiente"),
        Incidencia("2", "Bache profundo", "Bache en el carril derecho de Av. Té", "19/09/2026", "En proceso"),
        Incidencia("3", "Luminaria fundida", "Poste sin luz en la esquina", "18/09/2026", "Resuelto")
    )

    fun obtenerPorId(id: String): Incidencia? {
        return _incidencias.value?.find { it.id == id }
    }

    /**
     * Crea una nueva incidencia con estado "Pendiente" y fecha de hoy,
     * y la agrega al inicio de la lista (para que se vea de inmediato en Home).
     */
    fun agregarIncidencia(titulo: String, descripcion: String) {
        val nuevaId = System.currentTimeMillis().toString()
        val fechaHoy = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val nueva = Incidencia(
            id = nuevaId,
            titulo = titulo,
            descripcion = descripcion,
            fecha = fechaHoy,
            estado = "Pendiente"
        )
        val listaActual = _incidencias.value.orEmpty()
        _incidencias.value = listOf(nueva) + listaActual
    }
}