package org.scalalabs.intermediate.lab02

class TwitterUsers (list : List[TwitterUser]) {
  def thatArePopular() = TwitterUsers.thatArePopular(list)
  def thatArePopularByScreenName(): List[String] = TwitterUsers.thatArePopularByScreenName(list)
  def thatArePopularByScreenNameSortedbyPopularity(): List[String] = TwitterUsers.thatArePopularByScreenNameSortedbyPopularity(list)
  def thatArePopularByScreenNameAndPopularitySortedbyPopularity = TwitterUsers.thatArePopularByScreenNameAndPopularitySortedbyPopularity(list)
  def thatAreAlsoIn(otherUsers: List[TwitterUser]) = TwitterUsers.thatAreInBothLists(list, otherUsers)
}

object TwitterUsers {
   def thatArePopular(friends : List[TwitterUser]) : List[TwitterUser] =  {
     friends.filter(_.followersCount >= 2000)
   }

  def thatArePopularByScreenName(friends : List[TwitterUser]) : List[String] =  {
    thatArePopular(friends).map(_.screen_name)
  }

  def thatArePopularByScreenNameSortedbyPopularity(friends : List[TwitterUser]) : List[String] =  {
    // alternatively thatArePopularByScreenName(friends.sortBy(_.followersCount))
    thatArePopular(friends).sortBy(_.followersCount).reverse.map(_.screen_name)
  }

  def thatArePopularByScreenNameAndPopularitySortedbyPopularity(friends : List[TwitterUser]) : List[(String, Int)] =  {
    thatArePopular(friends).sortBy(_.followersCount).reverse.map(friend => (friend.screen_name, friend.followersCount))
  }

  def thatAreInBothLists(friends : List[TwitterUser], followers : List[TwitterUser]) : List[TwitterUser] =  {
    val st = friends.map(_.screen_name).toSet.intersect(followers.map(_.screen_name).toSet)
    friends.filter(friend => st.contains(friend.screen_name))
  }

  implicit def toTwtr(app : List[TwitterUser]) : TwitterUsers = new TwitterUsers(app)
}

