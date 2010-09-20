package mobi.pereira.diigonote

sealed case class Session(private val user: User) {

  import scala.util.parsing.json.{JSON, JSONArray, JSONObject}

  // Modularise this
  private def JSONObjectToBookmark(jsObject: JSONObject): Option[Bookmark] = {
    jsObject.obj.get("url").get match {
      case None => None
      case url: Any  => Some(new Bookmark(url.asInstanceOf[String]))
    }
  }

  def listBookmarks: Seq[Bookmark] = {
    ApiRequest().send(user) match {
      case None => println("Connection refused.")
	println("Please check your login/password.")
	List()
      case Some(text) => JSON.parseRaw(text).get match {
	case array: JSONArray =>
	  array.list.flatMap(a =>
	    JSONObjectToBookmark(a.asInstanceOf[JSONObject]))
	case single: JSONObject => JSONObjectToBookmark(single) match {
	  case None => List()
	  case Some(a) => List(a)
	}
	case a => println("Malformed response."); List()
      }
    }    
  }
}
