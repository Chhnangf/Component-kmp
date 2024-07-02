package org.example.project.data.file

import io.github.vinceglb.filekit.core.PlatformFile


data class FileData (
    val files: List<ByteArray>,
    val title: String?,
    val description: String
)