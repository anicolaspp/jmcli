package com.github.anicolasp.mapr.cli

trait RunnableCommand {
  def run(): String
}

object RunnableCommand {
  def apply(url: String, auth: Option[Auth]): RunnableCommand = new RunnableCommand {
    override def run(): String = {
      val (user, pass) = auth.map(x => (x.user, x.pass)).getOrElse(("", ""))

      requests
        .get(url,
          RequestAuth.implicitBasic(user, pass),
          verifySslCerts = false)
        .text()
    }
  }
}