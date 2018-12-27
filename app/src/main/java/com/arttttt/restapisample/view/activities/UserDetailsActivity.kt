package com.arttttt.restapisample.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.arttttt.restapisample.R
import com.arttttt.restapisample.model.user.User
import com.arttttt.restapisample.presenter.userdetails.UserDetailsContract
import com.arttttt.restapisample.utils.ValidatorUtils
import kotlinx.android.synthetic.main.activity_user_details.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class UserDetailsActivity : AppCompatActivity(), UserDetailsContract.View {

    companion object {
        const val UserExtraKey = "USER_EXTRA"
    }

    private val presenter: UserDetailsContract.Presenter by inject { parametersOf(this) }
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        presenter.onInitialization()
    }

    override fun initialize() {
        intent.extras?.let { extra ->
            presenter.editMode = true
            addOrUpdateUserButton.setText(R.string.update_user_button_text)

            user = extra.getParcelable(UserExtraKey)
            user?.let {
                firstName.setText(it.firstName, TextView.BufferType.EDITABLE)
                lastName.setText(it.lastName, TextView.BufferType.EDITABLE)
                email.setText(it.email, TextView.BufferType.EDITABLE)
            }
        }

        firstName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { firstNameInputLayout.error = "" }
        })

        lastName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { lastNameInputLayout.error = "" }
        })

        email.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { emailInputLayout.error = "" }
        })

        addOrUpdateUserButton.setOnClickListener {
            var fieldsValid = true

            if (!ValidatorUtils.isFieldValid(firstName.text.toString())) {
                firstNameInputLayout.error = getString(R.string.empty_field_error)
                fieldsValid = false
            }
            if (!ValidatorUtils.isFieldValid(lastName.text.toString())) {
                lastNameInputLayout.error = getString(R.string.empty_field_error)
                fieldsValid = false
            }
            if (!ValidatorUtils.isEmailFieldValid(email.text.toString())) {
                emailInputLayout.error = getString(R.string.wrong_email_error)
                fieldsValid = false
            }

            if (fieldsValid) {
                if (user == null)
                    user = User(
                        firstName.text.toString(),
                        lastName.text.toString(),
                        email.text.toString(),
                        "")

                presenter.onAddOrUpdateUserClicked(user!!)
                finish()
            }
        }
    }
}
