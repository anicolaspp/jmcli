package com.github.anicolasp.mapr.cli.client

import com.github.anicolasp.mapr.cli.stream.StreamEntry
import com.github.anicolasp.mapr.cli.table.TableEntry
import com.github.anicolasp.mapr.cli.volume.VolumeEntry

trait Client {

  def withAuth(user: String, password: String): Client

  def volume(): VolumeEntry

  def stream(): StreamEntry

  def table(): TableEntry
}
