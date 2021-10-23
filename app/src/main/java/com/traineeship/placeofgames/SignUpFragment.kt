package com.traineeship.placeofgames

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.placeofgames.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.traineeship.placeofgames.data.user.User
import com.traineeship.placeofgames.utils.TokenUtil
import com.traineeship.placeofgames.viewmodels.AuthViewModel

class SignUpFragment : Fragment() {

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var btnBack: AppCompatImageButton
    private lateinit var etName: TextInputEditText
    private lateinit var etLogin: TextInputEditText
    private lateinit var etPass: TextInputEditText
    private lateinit var etRepeatPass: TextInputEditText
    private lateinit var btnRegister: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        initViews(view)

        btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        btnRegister.setOnClickListener {
            val name = etName.text.toString()
            val login = etLogin.text.toString()
            val password = etPass.text.toString()
            val repeatPass = etRepeatPass.text.toString()

            val user = checkCreateUser(name, login, password, repeatPass)

            if (user != null) {
                authViewModel.getTokenSignUp().observe(viewLifecycleOwner) {
                    val tokenUtil = TokenUtil(requireContext())
                    tokenUtil.token = it.value
                }
                authViewModel.registerUser(user)
            }

        }

        return view
    }

    private fun checkCreateUser(
        name: String,
        login: String,
        password: String,
        repeatPass: String
    ): User? {
        return when {
            name.isEmpty() -> {
                Toast.makeText(context, "Имя не может быть пустым!", Toast.LENGTH_LONG).show()
                null
            }
            login.isEmpty() -> {
                Toast.makeText(context, "Логин не может быть пустым!", Toast.LENGTH_LONG).show()
                null
            }
            password != repeatPass -> {
                Toast.makeText(context, "Пароли не совпадают!", Toast.LENGTH_LONG).show()
                null
            }
            else -> {
                User(login, password, name)
            }
        }
    }

    private fun initViews(view: View) {
        btnBack = view.findViewById(R.id.btn_back)
        etName = view.findViewById(R.id.et_name)
        etLogin = view.findViewById(R.id.et_login)
        etPass = view.findViewById(R.id.et_password)
        etRepeatPass = view.findViewById(R.id.et_repeat_pass)
        btnRegister = view.findViewById(R.id.btn_register)
    }
}