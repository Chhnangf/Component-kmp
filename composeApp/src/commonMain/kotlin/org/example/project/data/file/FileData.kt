package org.example.project.data.file

import io.github.vinceglb.filekit.core.PlatformFile


data class FileData (
    val files: ByteArray,
    val title: String?,
    val description: String
)