package com.github.anicolasp.mapr.cli

trait Client {

  def withAuth(user: String, password: String): Client

  def volume(): Volume
}
