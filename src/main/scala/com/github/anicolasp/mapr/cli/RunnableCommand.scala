package com.github.anicolasp.mapr.cli

import com.github.anicolasp.mapr.cli.MapRCLI.Auth
import requests.{RequestAuth, Response}

trait RunnableCommand {
  def run(): Response
}

object RunnableCommand {
  def apply(url: String, auth: Option[Auth], params: Iterable[(String, String)] = Iterable.empty): RunnableCommand = () => {
    val (user, pass) = auth.map(x => (x.user, x.pass)).getOrElse(("", ""))

    requests
      .post(url,
        RequestAuth.implicitBasic(user, pass),
        params,
        verifySslCerts = false
      )
  }
}