package com.github.anicolasp.mapr.cli

import com.github.anicolasp.mapr.cli.MapRCLI.Auth

trait Volume {
  def list(): RunnableCommand
}

object Volume {

  def apply(host: String, auth: Option[Auth]): Volume = new Volume {
    override def list(): RunnableCommand = {
      val url = host + "/rest/volume/list"

      RunnableCommand(url, auth)
    }
  }

}