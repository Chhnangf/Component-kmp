package org.example.project.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.cache.Database

class PublishViewModel(private val id: Uuid = uuid4()) : ViewModel(){

////    val databaseDriver = SqlDelightDatabaseDriver(AppDatabase.Schema, context)
////    val imageDatabase = ImageDatabase(databaseDriver)
//
//    // 使用mutableStateOf来封装一个可变的ByteArray列表
//    var selectedImageByteArray by mutableStateOf(mutableListOf<ByteArray>()) // 可直接读写
//
//    // 添加一个方法用于向列表中添加新的ByteArray
//    fun addImageByteArray(imageByteArray: List<ByteArray>) {
//        // 直接修改可变状态列表
//        selectedImageByteArray += imageByteArray
//        println("调用addImageByteArray: ${selectedImageByteArray}")
//    }
//
//    // 如果需要，也可以提供一个方法获取当前的列表（虽然直接暴露State也是可行的）
//    fun getImageByteArrays(): List<ByteArray> {
//        return selectedImageByteArray
//        println("调用getImageByteArrays")
//    }

    // 可以在这里暴露一个insertImage的函数来供Compose函数调用
//    fun insertImage(byteArray: ByteArray) = viewModelScope.launch {
//        database.insertImage(byteArray)
//    }
}
