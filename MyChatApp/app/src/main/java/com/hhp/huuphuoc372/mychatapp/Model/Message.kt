package com.hhp.huuphuoc372.mychatapp.Model

import java.time.LocalDate

data class Message(val content: String, val senderId: String, val receiverId: String, val time: LocalDate) {
}