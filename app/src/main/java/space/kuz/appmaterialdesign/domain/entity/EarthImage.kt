package space.kuz.appmaterialdesign.domain.entity

sealed class EarthImage {
    data class SuccessEarth(val serverResponseData: NASAImageEarth) : EarthImage()

    data class Error(val error: Throwable) : EarthImage()

    data class Loading(val progress: Int?) : EarthImage()
}