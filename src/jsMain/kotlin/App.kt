import js.objects.jso
import kotlin.js.Promise

fun main() {
    // commonMain type
    val hello = Hello()

    // jsMain type
    val print = Print()

    // Use them together
    print.console(hello.sayHello())

    // Call out to a node lib
    demoCallingANodeLib()
}

@JsModule("axios")
external fun <T> axios(config: AxiosConfigSettings): Promise<AxiosResponse<T>>

external interface AxiosConfigSettings {
    var url: String
    var method: String
    var baseUrl: String
    var timeout: Number
    var data: dynamic
    var transferRequest: dynamic
    var transferResponse: dynamic
    var headers: dynamic
    var params: dynamic
    var withCredentials: Boolean
    var adapter: dynamic
    var auth: dynamic
    var responseType: String
    var xsrfCookieName: String
    var xsrfHeaderName: String
    var onUploadProgress: dynamic
    var onDownloadProgress: dynamic
    var maxContentLength: Number
    var validateStatus: (Number) -> Boolean
    var maxRedirects: Number
    var httpAgent: dynamic
    var httpsAgent: dynamic
    var proxy: dynamic
    var cancelToken: dynamic
}

external interface AxiosResponse<T> {
    val data: T
    val status: Number
    val statusText: String
    val headers: dynamic
    val config: AxiosConfigSettings
}

data class ZipResult(
    val country: String,
    val state: String,
    val city: String,
)

external interface ZipData {
    val country: String
    val state: String
    val city: String
}

private fun demoCallingANodeLib() {
    val zipCode = "87701"
    val config: AxiosConfigSettings = jso {
        url = "https://ziptasticapi.com/$zipCode"
        timeout = 3000
    }

    var zipResult: ZipResult
    var errorMessage: String

    axios<ZipData>(config).then { response ->
        zipResult = ZipResult(response.data.country, response.data.state, response.data.city)
        errorMessage = ""
        console.log("ZipResult for $zipCode was : $zipResult")
    }.catch { error ->
        zipResult = ZipResult("", "", "")
        errorMessage = error.message ?: ""
        console.log(errorMessage)
    }
}
