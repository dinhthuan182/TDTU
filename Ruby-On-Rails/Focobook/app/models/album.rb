class Album < ApplicationRecord
  belongs_to :user
  has_many :likes, as: :likeable, dependent: :destroy
  # has_many :image_albums
  # accepts_nested_attributes_for :image_albums, allow_destroy: true
  mount_uploaders :images, ImagesUploader

  scope :published_album, -> {
    where(sharing_mode: true)
  }

end
