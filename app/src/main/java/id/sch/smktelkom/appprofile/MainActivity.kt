package id.sch.smktelkom.appprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
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

        edtName = findViewById(R.id.edtName)
        spinnerGender = findViewById(R.id.spinnerGender)
        edtEmail = findViewById(R.id.edtEmail)
        edtTelp = findViewById(R.id.edtTelp)
        edtAlamat = findViewById(R.id.edtAddress)
        btnSave.setOnClickListener { validasiInput() }

    }

    private fun validasiInput() {
        val namaInput = edtName.text.toString()
        val emailInput = edtEmail.text.toString()
        val telpInput = edtTelp.text.toString()
        val alamatInput = edtAddress.text.toString()
        val genderInput = spinnerGender.selectedItem.toString()
        when {
            namaInput.isEmpty() -> edtName.error = "Nama tidak boleh kosong"
            genderInput.equals("Pilih kelamin") -> tampilToast("Kelamin harus dipilih")
            emailInput.isEmpty() -> edtEmail.error = "Email tidak bolehkosong"
            telpInput.isEmpty() -> edtTelp.error = "Telp tidak boleh kosong"
            alamatInput.isEmpty() -> edtAddress.error = "Alamat tidak bolehkosong"
            else -> {
                tampilToast("Navigasi ke halaman profil diri")
                navigasiKeProfilDiri()

            }
        }

        fun setDataSpinnerGender() {
            val adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender_list, android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )
            spinnerGender.adapter = adapter

        }

    }

    private fun navigasiKeProfilDiri() {
        val intent = Intent(this, profile::class.java)

        val bundle = Bundle()
        bundle.putString("nama", namaInput)
        bundle.putString("gender", genderInput)
        bundle.putString("email", emailInput)
        bundle.putString("telp", telpInput)
        bundle.putString("alamat", alamatInput)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
