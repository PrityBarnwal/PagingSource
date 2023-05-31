package com.applaunch.paginationdemo.activities

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.applaunch.paginationdemo.databinding.ActivityEncryptBinding
import com.applaunch.paginationdemo.encrypt.Encrypt
import dagger.hilt.android.AndroidEntryPoint
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException


@AndroidEntryPoint
class EncryptActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEncryptBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEncryptBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnEncrypt.setOnClickListener {
                try {
                    val encryptedText: String =
                        Encrypt.encrypt(etEncrypt.text.toString())
                    etDecrypt.setText(encryptedText)
                } catch (e: InvalidAlgorithmParameterException) {
                    e.printStackTrace()
                } catch (e: NoSuchPaddingException) {
                    e.printStackTrace()
                } catch (e: IllegalBlockSizeException) {
                    e.printStackTrace()
                } catch (e: NoSuchAlgorithmException) {
                    e.printStackTrace()
                } catch (e: BadPaddingException) {
                    e.printStackTrace()
                } catch (e: InvalidKeyException) {
                    e.printStackTrace()
                }
            }

            btnDecrypt.setOnClickListener {
                try {
                    val decryptedText: String =
                        Encrypt.decrypt(etDecrypt.text.toString())
                    etDecrypt.setText(decryptedText)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}