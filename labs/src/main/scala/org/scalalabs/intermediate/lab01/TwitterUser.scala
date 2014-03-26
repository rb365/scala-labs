package org.scalalabs.intermediate.lab01


class TwitterUser {
  var id: Long = -1
  var name: String = ""
  var screen_name: String = ""
  var description: String = ""
  var location: String = ""
  var url: String = ""
  var profileImageUrl: String = ""
  var statusesCount: Long = -1
  var friendsCount: Long = -1
  var followersCount: Long = -1

  def this(n : scala.xml.Node) = {
    this
    id = (n\"id")(0).text.toLong
    name = (n\"name")(0).text
    screen_name = (n\"screen_name")(0).text
    description = (n\"description")(0).text
    location = (n\"location")(0).text
    url = (n\"url")(0).text
    profileImageUrl = (n\"profile_image_url")(0).text
    statusesCount = (n\"statuses_count")(0).text.toLong
    friendsCount = (n\"friends_count")(0).text.toLong
    followersCount = (n\"followers_count")(0).text.toLong
  }
}