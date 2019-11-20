class User < ApplicationRecord
  has_many :photos
  has_many :albums, dependent: :destroy
  has_many :likes, dependent: :destroy

  #for button follow in profile page
  has_many :active_relationships, class_name:  "Relationship",
                                foreign_key: "follower_id",
                                dependent:   :destroy
  has_many :passive_relationships, class_name:  "Relationship",
                                 foreign_key: "followed_id",
                                 dependent:   :destroy
  has_many :followings, through: :active_relationships, source: :followed
  has_many :followers, through: :passive_relationships, source: :follower

  has_many :photos, through: :likes, source: :likeable, source_type: 'Photo'
  has_many :albums, through: :likes, source: :likeable, source_type: 'Album'

  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable, :trackable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :validatable,
         :trackable

  has_attached_file :avatar, :styles => { :medium => "300x300>", :thumb => "100x100#" }, :default_url => "/assets/logoLogin.png"
  validates_attachment_content_type :avatar, :content_type => /\Aimage\/.*\Z/

  def full_name
    [first_name, last_name].select(&:present?).join(' ').titleize
  end

  # Follows a user.
  def follow(other_user)
    followings << other_user
  end

  # Unfollows a user.
  def unfollow(other_user)
    followings.delete(other_user)
  end

  # Returns true if the current user is following the other user.
  def following?(other_user)
    followings.include?(other_user)
  end
end
