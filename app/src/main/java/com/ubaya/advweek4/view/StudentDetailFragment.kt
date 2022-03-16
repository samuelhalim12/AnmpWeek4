package com.ubaya.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.advweek4.R
import com.ubaya.advweek4.model.Student
import com.ubaya.advweek4.util.loadImage
import com.ubaya.advweek4.viewmodel.DetailViewModel
import com.ubaya.advweek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.*
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentDetailFragment : Fragment() {
    private lateinit var  viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var studentId : String? = "test"
        arguments?.let {
             studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID
        }
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch(studentId)

        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.studentLiveData.observe(viewLifecycleOwner) {
            val student = viewModel.studentLiveData.value
            student?.let{
                imageStudentDetail.loadImage(it.photoURL, progressLoadDetailStudent)
                editID.setText(it.id)
                editName.setText(it.name)
                editPhone.setText(it.phone)
                editDOB.setText(it.dob)
            }
        }
    }
}