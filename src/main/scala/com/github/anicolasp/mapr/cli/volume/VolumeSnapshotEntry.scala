package com.github.anicolasp.mapr.cli.volume

import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import com.github.anicolasp.mapr.cli.entries.Entry
import com.github.anicolasp.mapr.cli.runnable.{RunnableCommand, RunnableQuery}

trait VolumeSnapshotEntry {
  def list(args: (String, String)*): RunnableQuery

  def create(volume: String, snapshotname: String): RunnableCommand

  def remove(volume: String, snapshotname: String): RunnableCommand
}

object VolumeSnapshotEntry {

  def apply(host: String, auth: Option[Auth]): VolumeSnapshotEntry = new VolumeSnapshotE(host, auth)

  class VolumeSnapshotE(host: String, auth: Option[Auth]) extends Entry(s"${host}/rest/volume/snapshot") with VolumeSnapshotEntry {
    override def list(args: (String, String)*): RunnableQuery =
      RunnableQuery(getUrl("list"), auth, args: _*)

    override def create(volume: String, snapshotname: String): RunnableCommand =
      RunnableCommand(getUrl("create"), auth, ("volume", volume), ("snapshotname", snapshotname))

    override def remove(volume: String, snapshotname: String): RunnableCommand =
      RunnableCommand(getUrl("remove"), auth, ("volume", volume), ("snapshotname", snapshotname))
  }

}