package com.github.anicolasp.mapr.cli.client

import com.github.anicolasp.mapr.cli.stream.StreamEntry
import com.github.anicolasp.mapr.cli.table.TableEntry
import com.github.anicolasp.mapr.cli.volume.VolumeEntry

object MapRCLI {

  def withHost(host: String): Client = MapRCliClient(host, None)


  case class Auth(user: String, pass: String)

  case class MapRCliClient(host: String, auth: Option[Auth]) extends Client {
    override def withAuth(user: String, password: String): Client = MapRCliClient(host, Some(Auth(user, password)))

    override def volume(): VolumeEntry = VolumeEntry(host, auth)

    override def stream(): StreamEntry = ???

    override def table(): TableEntry = ???
  }

}
