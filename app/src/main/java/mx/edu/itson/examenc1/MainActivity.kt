package mx.edu.itson.examenc1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etCantidad: EditText
    private lateinit var etProducto: EditText
    private lateinit var etPrecio: EditText
    private lateinit var btnAgregar: Button
    private lateinit var btnNuevo: Button
    private lateinit var tvCantidad1: TextView
    private lateinit var tvProducto1: TextView
    private lateinit var tvPrecio1: TextView
    private lateinit var tvSubtotal1: TextView
    private lateinit var tvSubtotal: TextView
    private lateinit var tvIVA: TextView
    private lateinit var tvTotal: TextView

    private var currentRow = 0 //para limitar los productos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCantidad = findViewById(R.id.etCantidad)
        etProducto = findViewById(R.id.etProducto)
        etPrecio = findViewById(R.id.etPrecio)
        btnAgregar = findViewById(R.id.btnAgregar)
        tvCantidad1 = findViewById(R.id.tvCantidad1)
        tvProducto1 = findViewById(R.id.tvProducto1)
        tvPrecio1 = findViewById(R.id.tvPrecio1)
        tvSubtotal1 = findViewById(R.id.tvSubtotal1)
        tvSubtotal = findViewById(R.id.tvSubtotal)
        tvIVA = findViewById(R.id.tvIVA)
        tvTotal = findViewById(R.id.tvTotal)

        btnAgregar.setOnClickListener {
            agregarProducto()
        }


    }

    private fun agregarProducto() {
        if (currentRow < 3) {
            val cantidad = etCantidad.text.toString().toDoubleOrNull() ?: 0.0
            val producto = etProducto.text.toString()
            val precio = etPrecio.text.toString().toDoubleOrNull() ?: 0.0
            val subtotalProducto = cantidad * precio

            when (currentRow) {
                0 -> {
                    tvCantidad1.text = cantidad.toString()
                    tvProducto1.text = producto
                    tvPrecio1.text = precio.toString()
                    tvSubtotal1.text = subtotalProducto.toString()
                    findViewById<TableRow>(R.id.fila1).visibility = View.VISIBLE
                }
                1 -> {
                    findViewById<TextView>(R.id.tvCantidad2).text = cantidad.toString()
                    findViewById<TextView>(R.id.tvProducto2).text = producto
                    findViewById<TextView>(R.id.tvPrecio2).text = precio.toString()
                    findViewById<TextView>(R.id.tvSubtotal2).text = subtotalProducto.toString()
                    findViewById<TableRow>(R.id.fila2).visibility = View.VISIBLE
                }
                2 -> {
                    findViewById<TextView>(R.id.tvCantidad3).text = cantidad.toString()
                    findViewById<TextView>(R.id.tvProducto3).text = producto
                    findViewById<TextView>(R.id.tvPrecio3).text = precio.toString()
                    findViewById<TextView>(R.id.tvSubtotal3).text = subtotalProducto.toString()
                    findViewById<TableRow>(R.id.fila3).visibility = View.VISIBLE
                }
            }

            currentRow++
            updateTotals()
            limpiarCampos()
        } else {
            Toast.makeText(this, "Solo se permiten 3 productos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateTotals() {
        val subtotal1 = tvSubtotal1.text.toString().toDoubleOrNull() ?: 0.0
        val subtotal2 = findViewById<TextView>(R.id.tvSubtotal2).text.toString().toDoubleOrNull() ?: 0.0
        val subtotal3 = findViewById<TextView>(R.id.tvSubtotal3).text.toString().toDoubleOrNull() ?: 0.0

        val subtotal = subtotal1 + subtotal2 + subtotal3
        val iva = subtotal * 0.16
        val total = subtotal + iva

        tvSubtotal.text = "Subtotal: $subtotal"
        tvIVA.text = "IVA (16%): $iva"
        tvTotal.text = "Total: $total"
    }

    private fun limpiarCampos() {
        etCantidad.text.clear()
        etProducto.text.clear()
        etPrecio.text.clear()
    }
}

