package com.example.retapp.chat

class MessageModel {
    private var message: String = ""
    private var messageFrom: String = ""
    private var messageId: String = ""
    private var messageTime: Long = 0
    private var messageType: String = ""

    constructor()

    constructor(
        message: String,
        messageFrom: String,
        messageId: String,
        messageTime: Long,
        messageType: String
    ) {
        this.message = message
        this.messageFrom = messageFrom
        this.messageId = messageId
        this.messageTime = messageTime
        this.messageType = messageType
    }


    fun getMessage(): String {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getMessageFrom(): String {
        return messageFrom
    }

    fun setMessageFrom(messageFrom: String) {
        this.messageFrom = messageFrom
    }

    fun getMessageId(): String {
        return messageId
    }

    fun setMessageId(messageId: String) {
        this.messageId = messageId
    }

    fun getMessageTime(): Long {
        return messageTime
    }

    fun setMessageTime(messageTime: Long) {
        this.messageTime = messageTime
    }

    fun getMessageType(): String {
        return messageType
    }

    fun setMessageType(messageType: String) {
        this.messageType = messageType
    }
}
