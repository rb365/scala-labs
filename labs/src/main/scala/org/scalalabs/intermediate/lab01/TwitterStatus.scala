package org.scalalabs.intermediate.lab01

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.util.Locale

class TwitterStatus {
  var id: Long = -1
  var inReplyToStatusId: Any = None
  var inReplyToUserId: Any = None
  var truncated: Boolean = false
  var favorited: Boolean = false
  var text: String = ""
  var createdAt: DateTime = null
  var user: TwitterUser = null

  def this(n : scala.xml.Node) = {
    this
    id = (n\"id")(0).text.toLong
    var tmp = (n\"in_reply_to_status_id")(0).text
    inReplyToStatusId = if (tmp.isEmpty) None else tmp.toLong
    tmp = (n\"in_reply_to_user_id")(0).text
    inReplyToUserId = if (tmp.isEmpty) None else tmp.toLong
    truncated = (n\"truncated")(0).text.toBoolean
    favorited = (n\"favorited")(0).text.toBoolean
    text = (n\"text")(0).text
    createdAt = TwitterStatus.twitterDateTimeFormat.parseDateTime((n\"created_at")(0).text)
    user = new TwitterUser((n\\"user")(0))
  }

}

object TwitterStatus {
  val twitterDateTimeFormat = DateTimeFormat.forPattern("EE MMM dd HH:mm:ss Z yyyy").withLocale(Locale.US)

}