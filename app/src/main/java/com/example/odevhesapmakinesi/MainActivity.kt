package com.example.odevhesapmakinesi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.odevhesapmakinesi.databinding.ActivityMainBinding
import com.google.android.material.internal.ViewUtils
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var islem = ""
    var durum = true
    var sonuc = 0.0
    var islemDurum = ""
    val islemArray = ArrayList<Button>();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        islemArray.add(binding.buttonTopla)
        islemArray.add(binding.buttonCikar)
        islemArray.add(binding.buttonCarp)
        islemArray.add(binding.buttonBolme)

        binding.button1.setOnClickListener {
            calculatorBusiness(binding.button1)
        }
        binding.button2.setOnClickListener {
            calculatorBusiness(binding.button2)
        }
        binding.button3.setOnClickListener {
            calculatorBusiness(binding.button3)
        }
        binding.button4.setOnClickListener {
            calculatorBusiness(binding.button4)
        }
        binding.button5.setOnClickListener {
            calculatorBusiness(binding.button5)
        }
        binding.button6.setOnClickListener {
            calculatorBusiness(binding.button6)
        }
        binding.button7.setOnClickListener {
            calculatorBusiness(binding.button7)
        }
        binding.button8.setOnClickListener {
            calculatorBusiness(binding.button8)
        }
        binding.button9.setOnClickListener {
            calculatorBusiness(binding.button9)
        }
        binding.button0.setOnClickListener {
            calculatorBusiness(binding.button0)
        }
        binding.buttonDegistir.setOnClickListener {
            islem = "-$islem"
            binding.textViewIslem.text = islem
        }
        binding.buttonTopla.setOnClickListener {
            if (binding.textViewIslem.text.isNotEmpty()){
                for (i in islemArray){
                    i.isClickable = false
                }
                calculatorBusiness(binding.buttonTopla)
                islemDurum = binding.buttonTopla.text.toString()
                binding.buttonSonuc.isClickable = true
                binding.buttonNokta.isClickable = true
            }
        }
        binding.buttonCikar.setOnClickListener {
            if (binding.textViewIslem.text.isNotEmpty()){
                for (i in islemArray){
                    i.isClickable = false
                }
                calculatorBusiness(binding.buttonCikar)
                islemDurum = binding.buttonCikar.text.toString()
                binding.buttonSonuc.isClickable = true
                binding.buttonNokta.isClickable = true
            }
        }
        binding.buttonCarp.setOnClickListener {
            if (binding.textViewIslem.text.isNotEmpty()){
                for (i in islemArray){
                    i.isClickable = false
                }
                calculatorBusiness(binding.buttonCarp)
                islemDurum = binding.buttonCarp.text.toString()
                binding.buttonSonuc.isClickable = true
                binding.buttonNokta.isClickable = true
            }
        }
        binding.buttonBolme.setOnClickListener {
            if (binding.textViewIslem.text.isNotEmpty()){
                for (i in islemArray){
                    i.isClickable = false
                }
                calculatorBusiness(binding.buttonBolme)
                islemDurum = binding.buttonBolme.text.toString()
                binding.buttonSonuc.isClickable = true
                binding.buttonNokta.isClickable = true

            }

        }
        binding.buttonNokta.setOnClickListener{
            if (binding.textViewIslem.text.isNotEmpty()){
                calculatorBusiness(binding.buttonNokta)
                binding.buttonSonuc.isClickable = true
                it.isClickable = false
            }
        }
        binding.buttonC.setOnClickListener {
            calculaterCleaner()
        }
        binding.buttonSonuc.setOnClickListener {
            if (binding.textViewIslem.text.isNotEmpty()){
                sonuc =0.0
                val sayilar = islem.split(islemDurum)
                when(islemDurum){
                    "+" -> for (sayi in sayilar){
                        sonuc += sayi.toDouble()
                    }
                    "-" ->sonuc = sayilar[0].toDouble() - sayilar[1].toDouble()
                    "x" -> sonuc = sayilar[0].toDouble() * sayilar[1].toDouble()
                    "÷" -> if (sayilar[1].toDouble() != 0.0){
                        sonuc = sayilar[0].toDouble() / sayilar[1].toDouble()
                    }else{
                        Toast.makeText(this, "0'a bölünemez!",Toast.LENGTH_SHORT).show()
                        calculaterCleaner()
                    }
                    else -> Toast.makeText(this, "deneme", Toast.LENGTH_SHORT).show()
                }
                binding.buttonSonuc.isClickable = false
                for (i in islemArray){
                    i.isClickable = true
                }
                binding.textViewSonuc.text = String.format("%.1f", sonuc)
                islem = ""
                durum = false
            }
        }

        setContentView(binding.root)
    }
    private fun calculatorBusiness(button: Button){
        if (durum){
            islem += button.text.toString()
            binding.textViewIslem.text = islem
        } else if (islemArray[0].text == button.text
            || islemArray[1].text == button.text
            || islemArray[2].text == button.text
            || islemArray[3].text == button.text ){
            val duzenlenmisSonuc = String.format("%.1f", sonuc)
            islem += duzenlenmisSonuc  +button.text.toString()
            binding.textViewIslem.text = islem
            durum = true
            binding.buttonSonuc.isClickable = true
            binding.buttonNokta.isClickable = true
        }else{
            calculaterCleaner()
            calculatorBusiness(button)
        }
    }
    private fun calculaterCleaner(){
        for (i in islemArray){
            i.isClickable = true
        }
        islem = ""
        sonuc = 0.0
        binding.textViewSonuc.text = "0"
        binding.textViewIslem.text = islem
        durum = true
    }
}