package mobi.pereira.diigonote

object Diigonote {

  import scala.collection.mutable.HashMap

  private val sessions = HashMap[User, Session]()

  def getSession(user: User): Session = {
    sessions.get(user) match {
      case Some(session) => session
      case None => val session = Session(user)
	sessions.put(user, session)
	session
    }
  }

  def getSession(login: String, password: String): Session = getSession(User(login, password))
}
