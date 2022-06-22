package com.cyriltheandroid.leboncv.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cyriltheandroid.leboncv.data.model.SkillType
import com.cyriltheandroid.leboncv.data.mapper.SkillsMapper
import com.cyriltheandroid.leboncv.data.model.Skill
import com.cyriltheandroid.leboncv.data.repository.SkillsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SkillsViewModel @Inject constructor(
    private val skillsRepository: SkillsRepository,
    private val skillsMapper: SkillsMapper
) : ViewModel() {

    private val allSkillsLiveData = MutableLiveData<List<List<Skill>>>()
    val allSkills: LiveData<List<List<Skill>>> = allSkillsLiveData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val hardSkills = async {
                skillsRepository.getSkillsFromType(SkillType.HARD_SKILL).let {
                    skillsMapper.map(it)
                }
            }
            val softSkills = async {
                skillsRepository.getSkillsFromType(SkillType.SOFT_SKILL).let {
                    skillsMapper.map(it)
                }
            }
            allSkillsLiveData.postValue(listOf(hardSkills.await(), softSkills.await()))
        }
    }
}