package mobi.pereira.diigonote

import swing._

import java.awt.Dimension

object SwingClient extends SimpleSwingApplication {

  val user = Utils.loadUserFromConfig

  def top: MainFrame = new MainFrame {
    title = "Hello, World!"

    preferredSize = new Dimension(300, 600)

    contents = new GridPanel(35, 1) {
      Diigonote.getSession(user).listBookmarks.map(a => new Label(a.url)).foreach(contents += _)
    }
  }
}
