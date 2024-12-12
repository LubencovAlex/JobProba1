package com.example.jobproba1.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jobproba1.databinding.FragmentAccurateBinding

class AccurateFragment : Fragment() {

    lateinit var binding: FragmentAccurateBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAccurateBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageButton4.setOnClickListener {

        }

        binding.tvTitle.text = arguments?.getString("title")
        binding.tvSalaryFull.text = arguments?.getString("salaryFull")
        binding.tvExperiencePreviewText.text = arguments?.getString("experiencePreviewText")
        val mass = arguments?.getStringArrayList("schedules").toString()
        val string = mass.substring(1, mass.length-1)
        binding.tvSchedules.text = string
        binding.tvCompany.text = arguments?.getString("company")
        val town = arguments?.getString("addressTown")
        val street = arguments?.getString("addressStreet")
        val house = arguments?.getString("addressHouse")
        binding.tvAddress.text = "$town, $street, $house"
        val applied = arguments?.getInt("appliedNumber").toString()
        binding.tvApplied.text = "$applied человек уже откликнулись"
        val looking = arguments?.getInt("lookingNumber").toString()
        binding.tvLooking.text = "$looking человек сейчас смотрят"
        binding.tvDescription.text = arguments?.getString("description")
        binding.tvResponsibilities.text = arguments?.getString("responsibilities")
        val questions = arguments?.getStringArrayList("questions")
        if (questions != null) {
            when(questions.size){
                1 -> {
                    binding.tvQuestion1.text = questions[0].toString()
                    binding.tvQuestion2.visibility = View.GONE
                    binding.tvQuestion3.visibility = View.GONE
                    binding.tvQuestion4.visibility = View.GONE
                    binding.tvQuestion5.visibility = View.GONE
                    }
                2 -> {
                    binding.tvQuestion1.text = questions[0].toString()
                    binding.tvQuestion2.text = questions[1].toString()
                    binding.tvQuestion3.visibility = View.GONE
                    binding.tvQuestion4.visibility = View.GONE
                    binding.tvQuestion5.visibility = View.GONE
                    }
                3 -> {
                    binding.tvQuestion1.text = questions[0].toString()
                    binding.tvQuestion2.text = questions[1].toString()
                    binding.tvQuestion3.text = questions[2].toString()
                    binding.tvQuestion4.visibility = View.GONE
                    binding.tvQuestion5.visibility = View.GONE
                    }
                4 -> {
                    binding.tvQuestion1.text = questions[0].toString()
                    binding.tvQuestion2.text = questions[1].toString()
                    binding.tvQuestion3.text = questions[2].toString()
                    binding.tvQuestion4.text = questions[3].toString()
                    binding.tvQuestion5.visibility = View.GONE
                    }
                else -> {
                    binding.tvQuestion1.text = questions[0].toString()
                    binding.tvQuestion2.text = questions[1].toString()
                    binding.tvQuestion3.text = questions[2].toString()
                    binding.tvQuestion4.text = questions[3].toString()
                    binding.tvQuestion5.text = questions[4].toString()
                    }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AccurateFragment()
    }
}