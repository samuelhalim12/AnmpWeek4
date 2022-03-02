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
import com.ubaya.advweek4.viewmodel.DetailViewModel
import com.ubaya.advweek4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*

class StudentDetailFragment : Fragment() {
    private lateinit var  viewModel: DetailViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())
    private lateinit var arrStudent : Student
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.studentLiveData.observe(viewLifecycleOwner) {
//            studentListAdapter.updateStudentList(it)
            arrStudent = Student(
                viewModel.studentLiveData.value?.id,viewModel.studentLiveData.value?.name,
                viewModel.studentLiveData.value?.dob,viewModel.studentLiveData.value?.phone,
            viewModel.studentLiveData.value?.photoURL)
            editID.setText(arrStudent.id)
            editName.setText(arrStudent.name)
            editDOB.setText(arrStudent.dob)
            editPhone.setText(arrStudent.phone)
        }
    }
}