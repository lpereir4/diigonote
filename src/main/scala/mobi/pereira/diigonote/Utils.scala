package mobi.pereira.diigonote

object Utils {

  import java.util.Properties
  import java.io.FileInputStream

  private val base64Encoder = new sun.misc.BASE64Encoder();

  private val configFilename = ".diigonoteConfig"
  private val loginKey = "login"
  private val passwordKey = "password"

  def encodeInBase64(data: String) = base64Encoder.encode(data.getBytes())

  def loadUserFromConfig: User = {
    try {
      val in = new FileInputStream(configFilename)
      val p = new Properties()
      p.load(in)
      val login = p.getProperty(loginKey)
      val password = p.getProperty(passwordKey)
      in.close()
      User(login, password)
    } catch {
      case e => println("Warning: I failed to read configuration file.")
	User("", "")
    }
  }
}

/*
  def main2(args: Array[String]) {
    rez.get match {
      case array: JSONArray => println(array.list.size)
	array.list.foreach(a => showJSONObject(a.asInstanceOf[JSONObject]))
      case single: JSONObject => println(single)
      case _ => println("failure")
    }
  }

  def showJSONObject(jsobject: JSONObject): Unit = {
    jsobject.obj.get("url").get match {
      case None => Unit
      case url: Any  => println(new Bookmark(url.asInstanceOf[String]))
    }
    jsobject.obj.keys.toList.foreach(println)
  }

  def JSONObjectToBookmark(jsObject: JSONObject): Option[Bookmark] = {
    jsObject.obj.get("url").get match {
      case None => None
      case url: Any  => Some(new Bookmark(url.asInstanceOf[String]))
    }
  }
*/
