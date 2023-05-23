package com.wakeup.lecture.domain.dto

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String lecId;


}
