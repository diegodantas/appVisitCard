package com.ddn.visitcard.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ddn.visitcard.App
import com.ddn.visitcard.R
import com.ddn.visitcard.data.BusinessCard
import com.ddn.visitcard.databinding.ActivityAddVisitCardBinding

class AddVisitCardActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddVisitCardBinding.inflate(layoutInflater) }

    private  val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
        val items = resources.getStringArray(R.array.itens)
        val adapter = ArrayAdapter(applicationContext,R.layout.list_item, items)
        binding.autoCompleteTextView.setAdapter(adapter)
    }

    private fun insertListeners() {
        binding.btnClose.setOnClickListener {
            finish()
        }
        binding.btnConfirma.setOnClickListener {
            val businessCard = BusinessCard(
                nome = binding.tilNome.editText?.text.toString(),
                empresa = binding.tilEmpresa.editText?.text.toString(),
                fone = binding.tilFone.editText?.text.toString(),
                email = binding.tilEmail.editText?.text.toString(),
                //fundoPersonalisado = binding.tilCor.editText?.text.toString()
                fundoPersonalisado = binding.autoCompleteTextView.text.toString()
            )
            mainViewModel.insert(businessCard)
            Toast.makeText(
                this,
                R.string.label_show_success,
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }
}