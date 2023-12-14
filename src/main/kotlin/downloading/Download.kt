package downloading

sealed class Download{
    class Error:Download()
    class Success:Download()
    class Loading:Download()
}

fun selectDownload(download: Download){
    when(download){
        is Download.Error->{
            println("Error")
        }
        is Download.Success->{
            println("Error")
        }
        is Download.Loading->{
            println("Error")
        }
    }
}

fun main() {
    val down = Download.Error()
    selectDownload(down)
}
