package mx.itesm.fragmentosrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    lateinit var fragmentitoFragment : FragmentitoFragment
    lateinit var pizzaFragment: PizzaFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // podemos crear objetos de tipo fragment
        // OJO - es indispensable que los fragmentos tengan un
        // constructor default
        fragmentitoFragment = FragmentitoFragment()
        pizzaFragment = PizzaFragment.newInstance("DOMINOS", "VALLE REAL")

        // cómo agregar fragmentos a la actividad
        // opción 1 - con interfaz (XML)
        // opción 2 - con código

        // para poder hacer una acción con el contenedor de fragmentos
        // utilizamos un objeto que se llama fragment manager
        // es necesario hacer un objeto de tipo de transacción
        val transaction = supportFragmentManager.beginTransaction()

        transaction.add(R.id.fragmentContainerView, pizzaFragment, TAG_FRAGMENTO)
        transaction.commit()
    }

    fun swapFragments(view : View?){

        // 1ero - obtene referencia al fragmento actual
        val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG_FRAGMENTO)

        if(fragmentoActual != null) {

            // asignar uno de los fragmentos que ya tenemos

            // polimorfismo - un objeto de una superclase puede representar
            // a una subclase
            var nuevoFragmento : Fragment = pizzaFragment

            if(fragmentoActual == pizzaFragment)
                nuevoFragmento = fragmentitoFragment

            supportFragmentManager.beginTransaction().apply {

                // dos opciones - la práctica y la impráctica

                // la impráctica
                /*
                remove(fragmentoActual)
                add(R.id.fragmentContainerView, nuevoFragmento, TAG_FRAGMENTO)
                */

                // la práctica
                replace(R.id.fragmentContainerView, nuevoFragmento, TAG_FRAGMENTO)

                commit()

            }
        }
    }

    fun invocarMetodoEnFragmento(view : View?){

        val fragmentoActual = supportFragmentManager.findFragmentByTag(TAG_FRAGMENTO)

        if(fragmentoActual == fragmentitoFragment){
            fragmentitoFragment.test(this)
        } else {
            Toast.makeText(
                this,
                "CAMBIA FRAGMENTO PORFA",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {

        private const val TAG_FRAGMENTO = "fragmentito"
    }
}