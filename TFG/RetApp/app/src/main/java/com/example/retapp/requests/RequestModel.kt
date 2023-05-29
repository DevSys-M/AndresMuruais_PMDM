package com.example.retapp.requests

class RequestModel(
    private var userName: String,
    private var photoName: String,
    private var userId: String,

) {
    // Nombre usuario
    fun getUserName(): String {
        return userName
    }

    fun setUserName(name: String) {
        this.userName = name
    }

    // photoName
    fun getPhotoName(): String {
        return photoName
    }

    fun setPhotoName(name: String) {
        this.photoName = name
    }

    // userId
    fun getUserId(): String {
        return userId
    }

    fun setUserId(id: String) {
        this.userId = id
    }

}

