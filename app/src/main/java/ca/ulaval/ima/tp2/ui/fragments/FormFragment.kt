package ca.ulaval.ima.tp2.ui.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ca.ulaval.ima.tp2.R
import ca.ulaval.ima.tp2.model.User
import java.util.*

class FormFragment : Fragment() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var birthDateEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var departmentSpinner: Spinner
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form, container, false)

        // Initialisation des vues
        firstNameEditText = view.findViewById(R.id.first_name)
        lastNameEditText = view.findViewById(R.id.last_name)
        birthDateEditText = view.findViewById(R.id.birth_date)
        genderRadioGroup = view.findViewById(R.id.gender_group)
        departmentSpinner = view.findViewById(R.id.department_spinner)
        submitButton = view.findViewById(R.id.submit_button)

        // Configuration du Spinner pour les départements
        val departments = arrayOf("GEL", "GIF", "GLO", "IFT")
        departmentSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            departments
        )

        // DatePicker pour la date de naissance
        birthDateEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    birthDateEditText.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis() // Empêche les dates futures
            datePickerDialog.show()
        }

        // Gestion du clic sur le bouton Soumettre
        submitButton.setOnClickListener {
            if (validateForm()) {
                val user = User(
                    firstNameEditText.text.toString(),
                    lastNameEditText.text.toString(),
                    birthDateEditText.text.toString(),
                    view.findViewById<RadioButton>(genderRadioGroup.checkedRadioButtonId).text.toString(),
                    departmentSpinner.selectedItem.toString()
                )

                // Navigation vers le fragment Profil
                val action = FormFragmentDirections.actionFormFragmentToProfileFragment(user)
                findNavController().navigate(action)
            }
        }

        return view
    }

    // Fonction pour valider les entrées utilisateur
    private fun validateForm(): Boolean {
        var isValid = true

        if (firstNameEditText.text.isNullOrEmpty()) {
            firstNameEditText.error = "Veuillez entrer un prénom"
            isValid = false
        }

        if (lastNameEditText.text.isNullOrEmpty()) {
            lastNameEditText.error = "Veuillez entrer un nom"
            isValid = false
        }

        if (birthDateEditText.text.isNullOrEmpty()) {
            birthDateEditText.error = "Veuillez sélectionner une date de naissance"
            isValid = false
        } else {
            val calendar = Calendar.getInstance()
            val currentDate = calendar.time
            val parts = birthDateEditText.text.toString().split("/")
            val selectedDate = Calendar.getInstance().apply {
                set(parts[2].toInt(), parts[1].toInt() - 1, parts[0].toInt())
            }
            if (selectedDate.time.after(currentDate)) {
                birthDateEditText.error = "La date de naissance ne peut pas être dans le futur"
                isValid = false
            }
        }

        return isValid
    }
}
