interface ImagePicker {
    suspend fun fetchImages(): List<String>
}