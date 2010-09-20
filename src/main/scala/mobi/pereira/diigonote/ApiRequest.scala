package mobi.pereira.diigonote

sealed case class ApiRequest() {

  import java.net.{URL, HttpURLConnection}
  import java.io.{BufferedReader, InputStreamReader, IOException}

  // private val url = new URL("http://api2.diigo.com/bookmarks?users="+user.login+"&rows="+rows)
  private val url = new URL("http://api2.diigo.com/bookmarks")

  def send(user: User): Option[String] = {
    try {
      val connection: HttpURLConnection =
	url.openConnection().asInstanceOf[HttpURLConnection]
      connection.setRequestProperty("Authorization",
				    "Basic "+user.getEncodedBasicAuthToken)
      val inputStream = connection.getInputStream()
      val reader = new BufferedReader(new InputStreamReader(inputStream));
      val text = for(char <- reader.readLine()) yield char
      inputStream.close()
      connection.disconnect()
      Some(text)
    } catch {
      case _ => None
    }
  }
}
