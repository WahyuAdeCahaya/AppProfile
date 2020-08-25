package id.sch.smktelkom.appprofile

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var edtName: EditText? = null
    var spinnerGender: Spinner? = null
    var edtEmail: EditText? = null
    var edtTelp: EditText? = null
    var edtAlamat: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtName = findViewById(R.id.edtNama)
        spinnerGender = findViewById(R.id.spinnerGenderr)
        edtEmail = findViewById(R.id.edtEmaill)
        edtTelp = findViewById(R.id.edtTelpon)
        edtAlamat = findViewById(R.id.edtAddress)
        btnSave.setOnClickListener { validasiInput() }

        setDataSpinnerGender()

    }

    private fun setDataSpinnerGender() {
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.gender_list,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGenderr.adapter = adapter
    }

    private fun validasiInput() {
        val namaInput = edtNama.text.toString()
        val emailInput = edtEmaill.text.toString()
        val telpInput = edtTelpon.text.toString()
        val alamatInput = edtAddress.text.toString()
        val genderInput = spinnerGenderr.selectedItem.toString()

        when {
            namaInput.isEmpty() -> edtNama.error = "Nama tidak boleh kosong"
            genderInput.equals("Pilih kelamin") -> Toast.makeText(
                this,
                "Kelamin harus dipilih",
                Toast.LENGTH_SHORT
            )
            emailInput.isEmpty() -> edtEmaill.error = "Email tidak boleh kosong"
            telpInput.isEmpty() -> edtTelpon.error = "Telp tidak boleh kosong"
            alamatInput.isEmpty() -> edtAddress.error = "Alamat tidak boleh kosong"
            else -> {
                Toast.makeText(this, "Navigasi ke halaman profil diri", Toast.LENGTH_SHORT).show()
                navigasiKeProfilDiri()

            }
        }

    }

    private fun navigasiKeProfilDiri() {
        val intent = Intent(this, profile::class.java)
        val bundle = Bundle()

        val namaInput = edtNama.text.toString()
        val emailInput = edtEmaill.text.toString()
        val telpInput = edtTelpon.text.toString()
        val alamatInput = edtAddress.text.toString()
        val genderInput = spinnerGenderr.selectedItem.toString()


        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", telpInput)
        bundle.putString("alamat", alamatInput)

        intent.putExtras(bundle)
        startActivity(intent)
    }


}


