package com.github.anicolasp.mapr.cli

object MapRCLI {

  def withHost(host: String): Client = MapRCliClient(host, None)


  case class Auth(user: String, pass: String)

  case class MapRCliClient(host: String, auth: Option[Auth]) extends Client {
    override def withAuth(user: String, password: String): Client = MapRCliClient(host, Some(Auth(user, password)))

    override def volume(): Volume = Volume(host, auth)
  }

}
