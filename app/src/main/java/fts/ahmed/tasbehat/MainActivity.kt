package fts.ahmed.tasbehat

import android.content.DialogInterface
import android.os.Bundle
import android.view.OrientationEventListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import fts.ahmed.tasbehat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var tasbihat: Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initCustomToolbar()
        tasbihat = resources.getStringArray(R.array.tasbihat)
        onReCreationOfActivity()
        buttonsClickListeners()
//        onOrientation()

    }

    private fun onReCreationOfActivity() {
        binding.tvTasbihat.text = tasbihat[Counter.number % tasbihat.size]
        binding.tvCounter.text = Counter.number.toString()
    }

    private fun onOrientation() {
        val listener=object : OrientationEventListener(this) {
            override fun onOrientationChanged(p0: Int) {

                binding.tvTasbihat.text = tasbihat[Counter.number%tasbihat.size]
            }
        }
        listener.enable()
    }

    private fun initCustomToolbar() {
        supportActionBar?.hide()
        binding.myToolbar.title = ""
        setSupportActionBar(binding.myToolbar)
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun buttonsClickListeners() {
        binding.btnPlus.setOnClickListener {
            binding.tvCounter.text = (++Counter.number).toString()
            binding.tvTasbihat.text = tasbihat[Counter.number % tasbihat.size]
        }
        binding.refresh.setOnClickListener {
            alertDialog()

        }

    }

    private fun alertDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
        builder.setMessage("هل تريد تصفير العداد ؟")
        builder.setCancelable(true)

        var heIsSure = false

        builder.setPositiveButton(
            "نعم",
            DialogInterface.OnClickListener { dialog, id ->
                heIsSure = true
                Toast.makeText(this, "تم تصفير العداد", Toast.LENGTH_SHORT).show()

                Counter.number = 0
                binding.tvCounter.text = "0"
                binding.tvTasbihat.text = tasbihat[0]
                dialog.cancel()
            })

        builder.setNegativeButton(
            "لا",
            DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })

        val alert: AlertDialog = builder.create()
        alert.show()

    }

}
