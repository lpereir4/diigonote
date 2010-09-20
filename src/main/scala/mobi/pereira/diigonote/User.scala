package mobi.pereira.diigonote

sealed case class User(val login: String, private val password: String) {

  val basicAuthToken = login+":"+password

  def getEncodedBasicAuthToken = Utils.encodeInBase64(basicAuthToken)
}
