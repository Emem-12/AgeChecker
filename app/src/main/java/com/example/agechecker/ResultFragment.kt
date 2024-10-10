package com.example.agechecker

    import android.os.Bundle
    import android.text.Editable
    import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
    import android.widget.EditText
    import androidx.fragment.app.Fragment
import com.example.agechecker.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private lateinit var  result: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(layoutInflater)

        val age = arguments?.getInt("AGE") ?: 0
        age.let {


            binding.result.setText(age.toString())
            return binding.root


        }

    }
}

