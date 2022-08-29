package mx.itesm.fragmentosrecycler

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast


class FragmentitoFragment : Fragment() {

    // puedo borrarlo si no haga nada en especial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragmentito, container, false)
    }

    fun test(context : Context) {
        Toast.makeText(
            context,
            "HOLA DESDE EL FRAGMENTO",
            Toast.LENGTH_SHORT
        ).show()
    }
}