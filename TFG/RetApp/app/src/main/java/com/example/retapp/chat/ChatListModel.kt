package com.example.retapp.chat

class ChatListModel(
    private var userId: String,
    private var userName: String,
    private var photoName: String,
    private var unreadCount: String,
    private var lastMessage: String,
    private var lastMessageTime: String
) {
    // Getters
    fun getUserId(): String {
        return userId
    }

    fun getUserName(): String {
        return userName
    }

    fun getPhotoName(): String {
        return photoName
    }

    fun getUnreadCount(): String {
        return unreadCount
    }

    fun getLastMessage(): String {
        return lastMessage
    }

    fun getLastMessageTime(): String {
        return lastMessageTime
    }

    // Setters
    fun setUserId(userId: String) {
        this.userId = userId
    }

    fun setUserName(userName: String) {
        this.userName = userName
    }

    fun setPhotoName(photoName: String) {
        this.photoName = photoName
    }

    fun setUnreadCount(unreadCount: String) {
        this.unreadCount = unreadCount
    }

    fun setLastMessage(lastMessage: String) {
        this.lastMessage = lastMessage
    }

    fun setLastMessageTime(lastMessageTime: String) {
        this.lastMessageTime = lastMessageTime
    }
}
