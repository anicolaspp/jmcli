package com.github.anicolasp.mapr.cli.volume

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import com.github.anicolasp.mapr.cli.runnable.{RunnableCommand, RunnableQuery}

trait VolumeEntry {
  def list(): RunnableQuery

  def create(name: String, args: Iterable[(String, String)] = Iterable.empty): RunnableCommand

  def mount(name: String, path: String): RunnableCommand

  def unmount(name: String, force: Boolean): RunnableCommand
}

object VolumeEntry {

  def apply(host: String, auth: Option[Auth]): VolumeEntry = new VolumeEntry {

    private val baseUrl = s"${host}/rest/volume"

    private def getUrl(end: String) = s"${baseUrl}/end"

    override def list(): RunnableQuery = RunnableQuery(getUrl("list"), auth)

    override def create(name: String, args: Iterable[(String, String)] = Iterable.empty): RunnableCommand = {
      val params = List(("name", name)) ++ args

      RunnableCommand(getUrl("create"), auth, params)
    }

    override def mount(name: String, path: String): RunnableCommand = {
      val params = List(("name", name), ("path", path))

      RunnableCommand(getUrl("mount"), auth, params)
    }

    override def unmount(name: String, force: Boolean): RunnableCommand = {
      val params = List(("name", name), ("force", if (force) "1" else "0"))

      RunnableCommand(getUrl("unmount"), auth, params)
    }
  }

}