package com.github.anicolasp.mapr.cli

import java.net.URLEncoder

import com.github.anicolasp.mapr.cli.MapRCLI.Auth

trait Volume {
  def list(): RunnableQuery

  def create(name: String, args: Iterable[(String, String)] = Iterable.empty): RunnableCommand
}

object Volume {

  def apply(host: String, auth: Option[Auth]): Volume = new Volume {
    override def list(): RunnableQuery = {
      val url = host + "/rest/volume/list"

      RunnableQuery(url, auth)
    }

    override def create(name: String, args: Iterable[(String, String)] = Iterable.empty): RunnableCommand = {
      val url = host + "/rest/volume/create"

      val params = List(("name", name)) ++ args

      RunnableCommand(url, auth, params)
    }
  }

}