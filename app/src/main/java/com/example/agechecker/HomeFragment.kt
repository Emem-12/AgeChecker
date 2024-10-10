package com.example.agechecker

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.agechecker.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var selectedBirthYear: Int? = null
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var listOfCategories: MutableList<Category>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstCategory= Category(R.drawable.menu,"How many days until my birthday?",android.R.color.holo_green_light)
        val secondCategory= Category(R.drawable.calender,"What day was I born?",android.R.color.holo_blue_light)
        val thirdCategory= Category(R.drawable.profile,"How old am I today?",android.R.color.holo_orange_dark)

        listOfCategories =mutableListOf(firstCategory,secondCategory,thirdCategory)
        categoryAdapter = CategoryAdapter(listOfCategories,requireContext()) {}


        binding.recyclerview.adapter = categoryAdapter
        binding.recyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)


        binding.todaysDate.setOnClickListener {
            openDatePickerDialogForToday()
        }
        binding.dateOfbirth.setOnClickListener {
            openDatePickerDialogForBirth()
        }

        binding.calculateAge.setOnClickListener {
            selectedBirthYear?.let { year ->
                calculateAge(year)
            } ?: run {
                Toast.makeText(
                    requireContext(),
                    "Please select your birth date first.",
                    Toast.LENGTH_SHORT
                ).show()

            }
        }

    }

    private fun openDatePickerDialogForToday() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            Toast.makeText(requireContext(), "Today's date has been correctly picked", Toast.LENGTH_SHORT).show()
        }, year, month, day).show()
    }

    private fun openDatePickerDialogForBirth() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

       DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                selectedBirthYear = selectedYear
                Toast.makeText(
                    requireContext(),
                    "Birth date selected: $selectedDay/${selectedMonth + 1}/$selectedYear", Toast.LENGTH_SHORT
                ).show()
            }, year, month, day
        ).show()
    }


    private fun calculateAge(selectedYear: Int) {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val age = currentYear - selectedYear


        val resultFragment = ResultFragment()
        val bundle = Bundle()
        bundle.putInt("AGE", age)
        resultFragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, resultFragment)
            .addToBackStack(null).commit()
    }
}







