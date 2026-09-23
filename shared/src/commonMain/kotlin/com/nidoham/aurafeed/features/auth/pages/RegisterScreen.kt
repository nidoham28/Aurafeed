package com.nidoham.aurafeed.features.auth.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nidoham.aurafeed.features.auth.domain.Gender
import com.nidoham.aurafeed.features.auth.state.validateDateOfBirth
import com.nidoham.aurafeed.features.auth.state.validateEmail
import com.nidoham.aurafeed.features.auth.state.validatePassword
import com.nidoham.aurafeed.features.auth.state.validateUsername

@Composable
fun RegisterScreen(
    onRegisterClick: (
        username: String,
        email: String,
        password: String,
        dateOfBirth: String,
        gender: Gender
    ) -> Unit,
    onLoginClick: () -> Unit,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf<Gender?>(null) }

    var attempted by remember { mutableStateOf(false) }

    val usernameError = validateUsername(username)
    val emailError = validateEmail(email)
    val passwordError = validatePassword(password)
    val dateOfBirthError = validateDateOfBirth(dateOfBirth)
    val genderError = if (selectedGender == null) "Select gender" else null

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Create account",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Join AuraFeed with email and password",
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            singleLine = true,
            isError = attempted && usernameError != null,
            supportingText = if (attempted && usernameError != null) {
                { Text(usernameError) }
            } else {
                null
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            isError = attempted && emailError != null,
            supportingText = if (attempted && emailError != null) {
                { Text(emailError) }
            } else {
                null
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            isError = attempted && passwordError != null,
            supportingText = if (attempted && passwordError != null) {
                { Text(passwordError) }
            } else {
                null
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = { Text("Date of birth") },
            placeholder = { Text("YYYY-MM-DD") },
            singleLine = true,
            isError = attempted && dateOfBirthError != null,
            supportingText = if (attempted && dateOfBirthError != null) {
                { Text(dateOfBirthError) }
            } else {
                null
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )

        GenderSelector(
            selectedGender = selectedGender,
            attempted = attempted,
            genderError = genderError,
            onSelected = { selectedGender = it }
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }

        Button(
            onClick = {
                attempted = true

                val currentGender = selectedGender

                if (
                    usernameError == null &&
                    emailError == null &&
                    passwordError == null &&
                    dateOfBirthError == null &&
                    currentGender != null &&
                    !isLoading
                ) {
                    onRegisterClick(
                        username,
                        email,
                        password,
                        dateOfBirth,
                        currentGender
                    )
                }
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isLoading) "Creating account..." else "Register")
        }

        TextButton(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Already have an account? Login")
        }
    }
}

@Composable
private fun GenderSelector(
    selectedGender: Gender?,
    attempted: Boolean,
    genderError: String?,
    onSelected: (Gender) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Gender",
            style = MaterialTheme.typography.labelLarge
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Gender.entries.forEach { gender ->
                Row {
                    RadioButton(
                        selected = selectedGender == gender,
                        onClick = { onSelected(gender) }
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(gender.label)
                }
            }
        }

        if (attempted && genderError != null) {
            Text(
                text = genderError,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onRegisterClick = { _, _, _, _, _ -> },
        onLoginClick = {}
    )
}