package com.github.anicolasp.mapr.cli.volume

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import com.github.anicolasp.mapr.cli.entries.Entry
import com.github.anicolasp.mapr.cli.runnable.{RunnableCommand, RunnableQuery}

trait VolumeEntry {
  def list(): RunnableQuery

  def create(name: String, args: (String, String)*): RunnableCommand

  def mount(name: String, path: String): RunnableCommand

  def unmount(name: String, force: Boolean): RunnableCommand

  def info(name: String): RunnableQuery

  def snapshot(): VolumeSnapshotEntry

  def modify(name: String, args: (String, String)*): RunnableCommand

  def remove(name: String, force: Boolean): RunnableCommand

  def rename(name: String, newName: String): RunnableCommand

  def showMounts(name: String): RunnableQuery

}

object VolumeEntry {

  def apply(host: String, auth: Option[Auth]): VolumeEntry = new VolumeE(host, auth)

  class VolumeE(host: String, auth: Option[Auth]) extends Entry(s"${host}/rest/volume") with VolumeEntry {

    override def list(): RunnableQuery = RunnableQuery(getUrl("list"), auth)

    override def create(name: String, args: (String, String)*): RunnableCommand =
      RunnableCommand(getUrl("create"), auth, ("name", name) :: args.toList: _*)

    override def mount(name: String, path: String): RunnableCommand =
      RunnableCommand(getUrl("mount"), auth, ("name", name), ("path", path))

    override def unmount(name: String, force: Boolean): RunnableCommand = {
      val params = List(("name", name), ("force", if (force) "1" else "0"))

      RunnableCommand(getUrl("unmount"), auth, params: _*)
    }

    override def info(name: String): RunnableQuery =
      RunnableQuery(getUrl("info"), auth, ("name", name))

    override def snapshot(): VolumeSnapshotEntry = VolumeSnapshotEntry(host, auth)

    override def modify(name: String, args: (String, String)*): RunnableCommand =
      RunnableCommand(getUrl("modify"), auth, ("name", name) :: args.toList: _*)

    override def remove(name: String, force: Boolean): RunnableCommand =
      RunnableCommand(getUrl("remove"), auth, ("name", name), ("force", if (force) "1" else "0"))

    override def rename(name: String, newName: String): RunnableCommand =
      RunnableCommand(getUrl("rename"), auth, ("name", name), ("newname", newName))

    override def showMounts(name: String): RunnableQuery =
      RunnableQuery(getUrl("showmounts"), auth, ("name", name))
  }

}

