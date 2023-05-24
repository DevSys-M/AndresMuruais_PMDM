package com.example.retapp.findfriends

class FindFriendModel(
    private var userName: String,
    private var photoName: String,
    private var userId: String,
    private var requestSent: Boolean = false
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

    // requestSent
    fun isRequestSent(): Boolean {
        return requestSent
    }

    fun setRequestSent(sent: Boolean) {
        this.requestSent = sent
    }
}

