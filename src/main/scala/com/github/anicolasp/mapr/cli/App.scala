package com.github.anicolasp.mapr.cli

import com.github.anicolasp.mapr.cli.client.MapRCLI
import com.github.anicolasp.mapr.cli.client.MapRCLI.Auth
import requests.RequestAuth

object App {
  def main(args: Array[String]): Unit = {


    val client = MapRCLI
      .withHost("https://172.20.60.107:8443")
      .withAuth("user", "mypass")




  }

}


