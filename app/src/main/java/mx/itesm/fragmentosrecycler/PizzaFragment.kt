package mx.itesm.fragmentosrecycler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

// LLAVES PARA GUARDAR DATOS PARAMETRIZADOS (QUE LLEGAN DE AFUERA)
private const val NOMBRE = "nombre"
private const val DIRECCION = "direccion"

/**
 * A simple [Fragment] subclass.
 * Use the [PizzaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PizzaFragment : Fragment() {

    private var nombre: String? = null
    private var direccion: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // si es que arguments fuera no - null
        // (osea que se asignó)
        // asignamos valores parametrizados a atributos de objeto
        arguments?.let {
            nombre = it.getString(NOMBRE)
            direccion = it.getString(DIRECCION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(
            R.layout.fragment_pizza,
            container,
            false
        )

        view.findViewById<TextView>(R.id.nombrePizzaFragment).apply {
            text = nombre
        }

        view.findViewById<TextView>(R.id.direccionPizzaFragment).apply {
            text = direccion
        }

        return view
    }

    companion object {

        // método fábrica -
        // método estático que, utilizando parámetros que llegan de fuera,
        // crea un nuevo objeto

        // jvm - java virtual machine
        // annotation
        @JvmStatic
        fun newInstance(nombre: String, direccion: String) : PizzaFragment {

            // NOTA -
            // EL RESULTADO DE ESTO ES IDÉNTICO A LO DE ABAJO!
            val resultado = PizzaFragment()
            val bundle = Bundle()
            bundle.putString(NOMBRE, nombre)
            bundle.putString(DIRECCION, direccion)
            resultado.arguments = bundle

            return resultado

            /*
            // apply - keyword de kotlin que hace que
            // código interno tenga el scope del objeto
            PizzaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }*/
        }


    }
}