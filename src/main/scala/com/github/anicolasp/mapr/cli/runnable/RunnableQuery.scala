package com.github.anicolasp.mapr.cli.runnable

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import requests.{RequestAuth, Response}

trait RunnableQuery {
  def run(): Response
}

object RunnableQuery {
  def apply(url: String, auth: Option[Auth], params: Iterable[(String, String)] = Iterable.empty): RunnableQuery = () => {
    val (user, pass) = auth.map(x => (x.user, x.pass)).getOrElse(("", ""))

    requests
      .get(url,
        RequestAuth.implicitBasic(user, pass),
        params,
        verifySslCerts = false
      )
  }
}