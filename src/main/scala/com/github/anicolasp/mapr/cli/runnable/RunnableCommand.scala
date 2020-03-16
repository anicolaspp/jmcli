package com.github.anicolasp.mapr.cli.runnable

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import requests.{RequestAuth, Response}

trait RunnableCommand {
  def run(): Response
}

object RunnableCommand {
  def apply(url: String, auth: Option[Auth], params: (String, String)*): RunnableCommand = () => {
    val (user, pass) = auth.map(x => (x.user, x.pass)).getOrElse(("", ""))

    requests
      .post(url,
        RequestAuth.implicitBasic(user, pass),
        params,
        verifySslCerts = false
      )
  }
}